package com.ocean.vo;

/**
 * 请求结果返回对象
 *
 * @param <T>
 */
public class ResultBean<T> {
    public static int SUCCESS = 200; //请求已成功
    public static int BAD_REQUEST = 400; //请求无效，参数个数或类型不正确
    public static int FORBIDDEN = 403; //服务器已经理解请求，但是拒绝执行它
    public static int NOT_FOUND = 404; //请求失败，请求所希望得到的资源未被在服务器上发现
    public static int  NOT_ALLOWED= 405; //请求行中指定的请求方法不能被用于请求相应的资源(请求方式post、get)
    public static int ERROR = 500;  //服务器错误

    private int code = 200;
    private String message;
    private T data;
    private String token;

    public ResultBean() {}

    public ResultBean(T data) {
        this.data = data;
    }

    public ResultBean(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
