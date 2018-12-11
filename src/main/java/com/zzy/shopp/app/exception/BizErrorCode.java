package com.zzy.shopp.app.exception;

public enum BizErrorCode implements ErrorCode {
    OBJECT_IS_NULL("对象为空。", 3000),
    ID_NULL("id为空。", 3001),
    DATA_INVALID("数据无效", 3002),
    FILE_UPLOAD_FILD("文件上传失败", 5001);


    private int code;
    private String errorMsg;

    BizErrorCode(String errorMsg, Integer code) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

}
