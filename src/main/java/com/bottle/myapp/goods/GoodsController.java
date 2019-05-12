package com.bottle.myapp.goods;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dalaoyang
 * @Description
 * @project springboot_learn
 * @package com.dalaoyang.controller
 * @email yangyang@dalaoyang.cn
 * @date 2018/5/4
 */
@Slf4j
@RestController
public class GoodsController {

    @Autowired
    private GoodsRepository goodsRepository;

    // http://localhost:8888/save
    @GetMapping("save")
    public String save() {
        GoodsInfo goodsInfo = new GoodsInfo(1L,
                "商品" + System.currentTimeMillis(), "这是一个测试商品");
        goodsRepository.save(goodsInfo);
        return "success";
    }

    // http://localhost:8888/delete?id=1
    @GetMapping("delete")
    public String delete(long id) {
        goodsRepository.deleteById(id);
        return "success";
    }

    // http://localhost:8888/update?id=1&name=修改&description=修改
    @GetMapping("update")
    public String update(long id, String name, String description) {
        GoodsInfo goodsInfo = new GoodsInfo(id,
                name, description);
        goodsRepository.save(goodsInfo);
        return "success";
    }

    // http://localhost:8888/getOne?id=1
    @GetMapping("getOne")
    public GoodsInfo getOne(long id) {
        GoodsInfo goodsInfo = goodsRepository.findById(id).get();
        return goodsInfo;
    }


    // http://localhost:8888/getGoodsList?query=商品
    // http://localhost:8888/getGoodsList?query=商品&pageNumber=1
    // 根据关键字"商品"去查询列表，name或者description包含的都查询
    @GetMapping("getGoodsList")
    public Page<GoodsInfo> getList(Integer pageNumber, String query) {
        if (pageNumber == null) pageNumber = 0;
        // es搜索默认第一页页码是0
        //每页数量
        Integer PAGE_SIZE = 2;
        SearchQuery searchQuery = getEntitySearchQuery(pageNumber, PAGE_SIZE, query);
        log.info(searchQuery.getQuery().toString());
        // log.info(searchQuery.getFilter().toString());
        Page<GoodsInfo> goodsPage = goodsRepository.search(searchQuery);
        return goodsPage;
    }

    private SearchQuery getEntitySearchQuery(int pageNumber, int pageSize, String searchContent) {
        final FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = {
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.termQuery("id", 1),
                        ScoreFunctionBuilders.weightFactorFunction(100)),
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchPhraseQuery("name", searchContent),
                        ScoreFunctionBuilders.weightFactorFunction(100)),
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchPhraseQuery("description", searchContent),
                        ScoreFunctionBuilders.weightFactorFunction(100))
        };
        final QueryBuilder queryBuilder = QueryBuilders
                .functionScoreQuery(filterFunctionBuilders)
                .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                .setMinScore(filterFunctionBuilders.length * 100);
        return new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                // .withQuery(QueryBuilders.matchAllQuery())
                // .withFilter(QueryBuilders.matchPhraseQuery("name", searchContent))
                // .withFilter(QueryBuilders.matchPhraseQuery("description", "商品"))
                // 设置分页
                .withPageable(PageRequest.of(pageNumber, pageSize))
                .build();
    }

}