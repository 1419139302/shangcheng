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
@ApiModel(description = "用户")
@Table(name = "s_user")
public class User {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
     private Long id;

     /**
     * 用户名
     */
     @ApiModelProperty(value = "用户名")
    private String name;

    /**
     * 密碼
     */
    @ApiModelProperty(value = "密碼")
    private String password;

    /**
     * 盐值
     */
    @ApiModelProperty(value = "盐值")
    private String salt;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 删除时间
     */
    @ApiModelProperty(value = "删除时间")
    @Column(name = "delete_time")
    private Date deleteTime;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    @Column(name = "is_del")
    private Boolean isDel;


}