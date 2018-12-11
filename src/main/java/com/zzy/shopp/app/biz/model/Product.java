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
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "商品")
@Table(name = "s_product")
public class Product {
    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private Long id;

    /**
     * 商品名
     */
    @ApiModelProperty(value = "商品名")
    private String name;

    /**
     * 商品标题
     */
    @Column(name = "sub_title")
    @ApiModelProperty(value = "商品标题")
    private String subTitle;

    /**
     * 原价
     */
    @Column(name = "original_price")
    @ApiModelProperty(value = "原价")
    private Double originalPrice;

    /**
     * 优惠价
     */
    @Column(name = "promote_pice")
    @ApiModelProperty(value = "优惠价")
    private Double promotePice;

    /**
     * 库存
     */
    @ApiModelProperty(value = "库存")
    private Integer stock;

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

    @ApiModelProperty(value = "分类id")
    private Long cid;

    @ApiModelProperty(value = "分类对象")
    private Category category;

    @ApiModelProperty(value = "大图")
    private List<Productimage> productSingles;
    @ApiModelProperty(value = "详情图")
    private List<Productimage> productDetails;

}