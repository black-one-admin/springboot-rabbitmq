package com.xkcoding.mq.rabbitmq.response;

public enum Status {

    Success("200","成功"),Fail("500","失败");

    private Status(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    private String code;

    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
