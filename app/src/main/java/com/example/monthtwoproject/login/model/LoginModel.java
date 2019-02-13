package com.example.monthtwoproject.login.model;

import com.example.monthtwoproject.network.OkHttp3;

/**
 * @Auther: 李
 * @Date: 2019/2/13 09:21:18
 * @Description:
 */
public class LoginModel implements ILoginModel{
    @Override
    public void getData(String url, Class clazz, final ILoginCallBack iLoginCallBack) {
        OkHttp3.getInstance().doGet(url, clazz, new OkHttp3.NetCallBack() {
            @Override
            public void onSuccess(Object o) {
                iLoginCallBack.onSuccess(o);
            }

            @Override
            public void onFailed(Exception e) {
                iLoginCallBack.onFailed(e);
            }
        });
    }
}
