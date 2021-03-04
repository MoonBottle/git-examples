package com.bottle.examples.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * indexName索引名称 可以理解为数据库名 必须为小写
 * 不然会报org.elasticsearch.indices.InvalidIndexNameException异常
 * type类型 可以理解为表名
 *
 * @author dalaoyang
 * @Description
 * @project springboot_learn
 * @package com.dalaoyang.entity
 * @email yangyang@dalaoyang.leetcode.editor.cn
 * @date 2018/5/4
 */
@Document(indexName = "product_web", type = "sku")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInfo implements Serializable {

    @Id
    private Long id;

    private String name;

    private String description;

}
