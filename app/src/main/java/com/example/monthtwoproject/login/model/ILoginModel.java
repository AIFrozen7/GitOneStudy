package com.example.monthtwoproject.login.model;

/**
 * @Auther: 李
 * @Date: 2019/2/13 09:21:05
 * @Description:
 */
public interface ILoginModel {
    void getData(String url,Class clazz,ILoginCallBack iLoginCallBack);
    interface ILoginCallBack{
        void onSuccess(Object o);
        void onFailed(Exception e);
    }
}
