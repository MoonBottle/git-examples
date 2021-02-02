package com.bottle.myapp.test01;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyappApplicationTests {

    @Test
    public void contextLoads() throws IOException {

        // final CreateSolutionDTO createSolutionDTO = new CreateSolutionDTO();
        //
        // createSolutionDTO.setSolutionId(1L);
        // createSolutionDTO.setDrSolutionId(11L);
        // createSolutionDTO.setName("测试dr方案");
        //
        // final DrRoom drRoom = new DrRoom();
        // drRoom.setId(11L);
        // drRoom.setBetaRoomId(1L);
        // drRoom.setName("dr空间");
        //
        // final DrHardSku drHardSku = new DrHardSku();
        // drHardSku.setName("dr硬装sku");
        //
        // final DrSoftSku drSoftSku = new DrSoftSku();
        // drSoftSku.setName("dr软装sku");
        //
        // drRoom.setHardSkuList(Collections.singletonList(drHardSku));
        // drRoom.setSoftSkuList(Collections.singletonList(drSoftSku));
        //
        // createSolutionDTO.setRoomList(Collections.singletonList(drRoom));
        //
        // ObjectMapper mapper = new ObjectMapper();
        //
        // final String jsonString = JSON.toJSONString(createSolutionDTO);
        // System.out.println(jsonString);
        //
        // final String jsonValue2 = mapper.writeValueAsString(createSolutionDTO);
        // System.out.println(jsonValue2);
        //
        // final ISolution solutionDTO = JSON.parseObject(jsonString, CreateSolutionDTO.class);
        //
        // System.out.println(solutionDTO);
        //
        // final CreateSolutionDTO createSolutionDTO1 = mapper.readValue(jsonValue2, CreateSolutionDTO.class);
        // System.out.println(createSolutionDTO1);
        //
        // final List<? extends IRoom> roomList = solutionDTO.getRoomList();
        //
        // System.out.println(roomList);
        //
        // handlerRoomList(roomList);

        // final Son son = new Son();
        // son.test();

        // final Map<Long, IStore> storeMap = test13();

        // code 逻辑


        // List<Long> list = new ArrayList<>();
        // list.add(1L);
        // list.add(2L);
        // list.add(3L);
        // list.add(4L);
        // list.add(5L);
        // list.add(6L);
        // list.add(7L);
        // list.add(8L);
        //
        // final List<Long> skuIdList = Arrays.asList(2L, 4L);
        //
        //
        // list.sort(
        //         Comparator.comparingLong((it) -> -skuIdList.indexOf(it))
        // );
        //
        // System.out.println(list);

        // System.out.println(new BigDecimal("-1").compareTo(BigDecimal.ZERO));


        final List<Integer> integers = Arrays.asList(1, 2, 3);
        IntStream.range(0, integers.size())
                .forEach(idx -> System.out.println(idx + ":" + integers.get(idx)));

        return;
    }

    private Map<Long, IStore> test13() {
        final CustomerLevel customerLevel = new CustomerLevel();
        customerLevel.setCustomerLevelId(11L);
        customerLevel.setCustomerLevelName("黑铁");
        customerLevel.setCustomerLevelDiscount(BigDecimal.TEN);
        final StoreLevel storeLevel = new StoreLevel();
        storeLevel.setStoreLevelId("操作太骚看不懂");
        storeLevel.setStoreId(22L);
        storeLevel.setLevelName("黄金");
        storeLevel.setDiscountRate(BigDecimal.ONE);

        Map<Long, IStore> storeMap = new HashMap<>();
        storeMap.put(customerLevel.getStoreId(), customerLevel);
        storeMap.put(storeLevel.getStoreId(), storeLevel);
        return storeMap;
    }

    @Data
    class Father {

        void test() {
            saySomething();
        }

        void saySomething() {
            System.out.println("我是你儿子");
        }

    }

    @Data
    class Son extends Father {

        @Override
        void saySomething() {
            System.out.println("你是我爸爸");
        }

    }

    public void handlerRoomList(List<? extends IRoom> roomList) {
        System.out.println(JSON.toJSONString(roomList));
    }

    @Data
    private static class CreateSolutionDTO implements ISolution {

        @JsonIgnore
        private Long solutionId;

        private Long drSolutionId;

        private String name;

        private List<DrRoom> roomList;
    }

    @Data
    private static class BetaCreateSolutionDTO implements ISolution {

        private Long solutionId;

        private Long drSolutionId;

        private String name;

        private List<DrRoom> roomList;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class DrRoom implements IRoom {

        /**
         * dr 空间id
         */
        private Long id;

        private Long betaRoomId;

        private String name;

        private List<DrHardSku> hardSkuList;

        private List<DrSoftSku> softSkuList;

        @Override
        @JsonIgnore
        public Long getRoomId() {
            return getBetaRoomId();
        }

        @Override
        @JsonIgnore
        public Long getDrRoomId() {
            return getId();
        }
    }

    @Data
    private static class BetaRoom implements IRoom {

        private Long id;

        private String name;

        private List<DrHardSku> hardSkuList;

        private List<DrSoftSku> softSkuList;

        @Override
        public Long getRoomId() {
            return getId();
        }

        @Override
        public Long getDrRoomId() {
            return null;
        }
    }

    @Data
    private static class DrHardSku implements IHardSku {
        private String name;

    }

    @Data
    private static class DrSoftSku implements ISoftSku {
        private String name;

    }

    interface ISolution {

        Long getSolutionId();

        Long getDrSolutionId();

        String getName();

        List<? extends IRoom> getRoomList();

    }

    interface IRoom {

        Long getRoomId();

        Long getDrRoomId();

        String getName();

        List<? extends IHardSku> getHardSkuList();

        List<? extends ISoftSku> getSoftSkuList();

    }

    interface IHardSku {

        String getName();
    }


    interface ISoftSku {

        String getName();
    }


}

