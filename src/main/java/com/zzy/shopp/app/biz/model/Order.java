package com.zzy.shopp.app.biz.model;

import com.zzy.shopp.app.biz.shoppenum.PayEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "订单")
@Table(name = "s_order")
public class Order {
    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    private Long id;

    /**
     * 订单号
     */
    @Column(name = "order_code")
    @ApiModelProperty(value = "订单号")
    private String orderCode;

    /**
     * 收货地址
     */
    @ApiModelProperty(value = "收货地址")
    private String address;

    /**
     * 邮编
     */
    @ApiModelProperty(value = "邮编")
    private String post;

    /**
     * 收货人信息
     */
    @ApiModelProperty(value = "收货人信息")
    private String receiver;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    /**
     * 用户备注信息
     */
    @Column(name = "user_message")
    @ApiModelProperty(value = "用户备注信息")
    private String userMessage;

    /**
     * 创建日期
     */
    @Column(name = "create_date")
    @ApiModelProperty(value = "创建日期")
    private Date createDate;

    /**
     * 支付日期
     */
    @Column(name = "pay_date")
    @ApiModelProperty(value = "支付日期")
    private Date payDate;

    /**
     * 发货日期
     */
    @Column(name = "delivery_date")
    @ApiModelProperty(value = "发货日期")
    private Date deliveryDate;

    /**
     * 收货日期
     */
    @Column(name = "confirm_date")
    @ApiModelProperty(value = "收货日期")
    private Date confirmDate;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private String status;

    /**
     * 用户关联id
     */
    @ApiModelProperty(value = "用户关联id")
    private Long uid;

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

    @ApiModelProperty(value = "订单项集合")
    private List<Orderitem> orderItems;

    @ApiModelProperty(value = "用户对象")
    private User user;


    @Transient
    private Double total;
    @Transient
    private int totalNumber;
    @Transient
    private String statusDesc;

    public String getStatusDesc() {
        if (null != statusDesc)
            return statusDesc;
        String desc = "未知";
        switch (status) {
            case "waitPay":
                desc = PayEnum.WAITPAY.getChinaName();
                break;
            case "waitDelivery":
                desc = PayEnum.WAITDELIVERY.getChinaName();
                break;
            case "waitConfirm":
                desc = PayEnum.WAITCONFIRM.getChinaName();
                break;
            case "waitReview":
                desc = PayEnum.WAITREVIEW.getChinaName();
                break;
            case "finish":
                desc = PayEnum.FINISH.getChinaName();
                break;
            case "delete":
                desc = PayEnum.DELETE.getChinaName();
                break;
            default:
                desc = "未知";
        }
        statusDesc = desc;
        return statusDesc;
    }

}