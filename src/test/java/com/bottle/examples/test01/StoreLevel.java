package com.bottle.examples.test01;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>商户客户等级表实体类</p>
 *
 * @author yang
 * @date 2019-02-27 19:51:30
 */
@Data
public class StoreLevel implements Serializable, IStore {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String storeLevelId;

    /**
     * 店铺编号
     */
    private Long storeId;

    /**
     * 等级名称
     */
    private String levelName;

    /**
     * 折扣率
     */
    private BigDecimal discountRate;

    /**
     * 客户升级所需累积支付金额
     */
    private BigDecimal amountConditions;

    /**
     * 客户升级所需累积支付订单笔数
     */
    private Integer orderConditions;

    /**
     * 删除标记 0:未删除 1:已删除
     */
    private Integer delFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createPerson;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    private String updatePerson;

    /**
     * 删除时间
     */
    private LocalDateTime deleteTime;

    /**
     * 删除人
     */
    private String deletePerson;

    @Override
    public String getLevelId() {
        return this.storeLevelId;
    }
}