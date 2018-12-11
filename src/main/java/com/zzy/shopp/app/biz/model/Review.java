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
@ApiModel(description = "评价")
@Table(name = "s_review")
public class Review {
    /**
     * 评价id
     */
    @ApiModelProperty(value = "评价id")
    private Long id;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 用户关联id
     */
    @ApiModelProperty(value = "用户关联id")
    private Long uid;

    /**
     * 产品关联id
     */
    @ApiModelProperty(value = "产品关联id")
    private Long pid;

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

}