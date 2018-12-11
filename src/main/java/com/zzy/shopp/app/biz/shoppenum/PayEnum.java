package com.zzy.shopp.app.biz.shoppenum;

public enum PayEnum {


    WAITPAY("等待支付", "waitPay"),

    WAITDELIVERY("等待发货", "waitDelivery"),

    WAITCONFIRM("等待确认", "waitConfirm"),

    WAITREVIEW("等待评价", "waitReview"),

    FINISH("完成", "finish"),

    DELETE("删除", "delete");

    private String chinaName;
    private String englishName;

    PayEnum(String chinaName, String englishName) {
        this.chinaName = chinaName;
        this.englishName = englishName;
    }

    public String getChinaName() {
        return chinaName;
    }

    public String getEnglishName() {
        return englishName;
    }
}
