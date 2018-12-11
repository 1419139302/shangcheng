package com.zzy.shopp.app.biz.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "订单项")
@Table(name = "s_orderitem")
public class Orderitem {
    /**
     * 订单项id
     */
    @ApiModelProperty(value = "订单项id")
    private Long id;

    /**
     * 商品关联id
     */
    @ApiModelProperty(value = "商品关联id")
    private Long pid;

    /**
     * 订单关联id
     */
    @ApiModelProperty(value = "订单关联id")
    private Long oid;

    /**
     * 用户关联id
     */
    @ApiModelProperty(value = "用户关联id")
    private Long uid;

    /**
     * 购买数量
     */
    @ApiModelProperty(value = "购买数量")
    private Integer number;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 删除时间
     */
    @Column(name = "delete_time")
    @ApiModelProperty(value = "删除时间")
    private Date deleteTime;

    /**
     * 是否删除
     */
    @Column(name = "is_del")
    @ApiModelProperty(value = "是否删除")
    private Boolean isDel;

    @ApiModelProperty(value = "商品对象")
    private Product product;

    @ApiModelProperty(value = "用户对象")
    private User user;
}