package com.lichao.mmall.common;

import java.io.Serializable;


/**
 * Created by Administrator on 2017/7/14.
 */
public  class    ServerResponse<T> implements Serializable {

    private String msg;
    private int status;
    private T data;

    //定义四个私有的构造方法
    private ServerResponse (int status){this.status=status;}

    private ServerResponse(int status,String msg){
        this.status=status;
        this.msg=msg;
    }

    private ServerResponse(int status,T data){
        this.status=status;
        this.data=data;
    }

    private ServerResponse(int status,String msg,T data){
        this.status=status;
        this.msg=msg;
        this.data=data;
    }

    public boolean isSuccess(){
        return this.status==ResponseCode.SUCCESS.getCode();
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public static  <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg){

        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    //返回一个正确的数据,创建一个成功的服务器相应，并把data放进去
    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    //为了解决泛型和String类型冲突
    public static <T> ServerResponse<T> createBySuccess(String msg,T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }
    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }
    public static <T> ServerResponse<T> createByErrorCodeMessage(String errorMessage,int errorCode){
        return new ServerResponse<T>(errorCode,errorMessage);
    }

}
