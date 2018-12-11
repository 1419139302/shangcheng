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
 * 表名：order
 * <p>
 * 描述：订单表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "订单表Vo")
@Table(name = "order")
public class OrderVo implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id")
    @NotNull(message = "订单id不能为空", groups = {UpdateProject.class})
    private Long id;

    @ApiModelProperty(value = "订单号")
    private String orderCode;

    @ApiModelProperty(value = "收货地址")
    private String address;

    @ApiModelProperty(value = "邮编")
    private String post;

    @ApiModelProperty(value = "收货人信息")
    private String receiver;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "用户备注信息")
    private String userMessage;

    @ApiModelProperty(value = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @ApiModelProperty(value = "支付日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date payDate;

    @ApiModelProperty(value = "发货日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDate;

    @ApiModelProperty(value = "收货日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date confirmDate;

    @ApiModelProperty(value = "订单状态")
    private String status;

    @ApiModelProperty(value = "用户关联id")
    private Long uid;

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
