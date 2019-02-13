package com.example.monthtwoproject.login.presenter;

import com.example.monthtwoproject.api.Api;
import com.example.monthtwoproject.login.model.ILoginModel;
import com.example.monthtwoproject.login.model.LoginModel;
import com.example.monthtwoproject.login.view.MainActivity;

/**
 * @Auther: 李
 * @Date: 2019/2/13 09:21:44
 * @Description:
 */
public class LoginPresenter implements ILoginPresenter{
    MainActivity mainActivity;
    LoginModel loginModel;
    private void destroy(){
        if (mainActivity != null){
            mainActivity = null;
        }
    }
    @Override
    public void getData() {
        loginModel.getData(Api.LOGIN, mainActivity.getClass(), new ILoginModel.ILoginCallBack() {
            @Override
            public void onSuccess(Object o) {
                mainActivity.getData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
