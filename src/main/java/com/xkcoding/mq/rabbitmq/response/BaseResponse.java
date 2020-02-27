package com.xkcoding.mq.rabbitmq.response;

/**
 * @author swh
 * @create: 2020-02-27 13:28
 */
public class BaseResponse<T> {
    private long code;
    private String message;
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public BaseResponse( T data) {
        this.data = data;
    }



    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
