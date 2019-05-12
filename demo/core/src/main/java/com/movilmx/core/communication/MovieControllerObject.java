package com.movilmx.core.communication;

public class MovieControllerObject {
    private int       code;
    private String    msg;
    private Object    data;
    private Exception exception;

    public MovieControllerObject(){ }

    public MovieControllerObject(Exception exception){
        this.exception = exception;
    }

    public MovieControllerObject(int code, String msg, Object data){
        this.code = code;
        this.msg  = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public MovieControllerObject setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public MovieControllerObject setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public MovieControllerObject setData(Object data) {
        this.data = data;
        return this;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "MovieControllerObject{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
