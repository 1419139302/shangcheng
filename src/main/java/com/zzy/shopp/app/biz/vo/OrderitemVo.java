package com.zzy.shopp.app.biz.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzy.shopp.app.validGroup.UpdateProject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体bean
 *
 * @author Heaton
 * <p>
 * 表名：orderitem
 * <p>
 * 描述：订单项表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "订单项表Vo")
@Table(name = "orderitem")
public class OrderitemVo implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单项id")
    @NotNull(message = "订单id不能为空", groups = {UpdateProject.class})
    private Long id;

    @ApiModelProperty(value = "商品关联id")
    private Long pid;

    @ApiModelProperty(value = "订单关联id")
    private Long oid;

    @ApiModelProperty(value = "用户关联id")
    private Long uid;

    @ApiModelProperty(value = "购买数量")
    private Integer number;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    @ApiModelProperty(value = "删除时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deleteTime;

    @ApiModelProperty(value = "是否删除")
    private Integer idel;
}
