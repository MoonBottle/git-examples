package com.bottle.myapp;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 客户等级
 * Created by CHENLI on 2017/4/13.
 */
@Data
public class CustomerLevel implements Serializable, IStore {

    /**
     * 客户等级ID
     */
    private Long customerLevelId;

    /**
     * 客户等级名称
     */
    private String customerLevelName;

    /**
     * 客户等级折扣
     */
    private BigDecimal customerLevelDiscount;

    /**
     * 所需成长值
     */
    private Long growthValue;

    /**
     * 等级徽章图
     */
    private String rankBadgeImg;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createPerson;

    /**
     * 删除人
     */
    private String deletePerson;

    @Override
    public String getLevelId() {
        return this.customerLevelId.toString();
    }

    @Override
    public Long getStoreId() {
        return null;
    }

    @Override
    public String getLevelName() {
        return this.customerLevelName;
    }

    @Override
    public BigDecimal getDiscountRate() {
        return this.customerLevelDiscount;
    }
}
