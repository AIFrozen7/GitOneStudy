package com.example.monthtwoproject.network;

import android.os.Environment;
import android.os.Handler;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Auther: 李
 * @Date: 2019/2/13 09:27:34
 * @Description:
 */
public class OkHttp3 {
    private static OkHttpClient httpClient;
    private static volatile OkHttp3 instance;
    private Handler handler = new Handler();
    //拦截器
    private Interceptor getAppInterceptor(){
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //拦截前
                Request request = chain.request();
                //拦截后
                Response response = chain.proceed(request);
                return response;
            }
        };
        return interceptor;
    }
    //添加拦截器
    private OkHttp3(){
        File file = new File(Environment.getDataDirectory(),"cachell");
        httpClient = new OkHttpClient.Builder()
                .connectTimeout(3000, TimeUnit.SECONDS)//设置读取超时
                .readTimeout(3000,TimeUnit.SECONDS)//设置读取超时
                .cache(new Cache(file,10*1024))
                .addInterceptor(getAppInterceptor())
                .build();
    }

    //单例模式
    public static OkHttp3 getInstance(){
        if (instance==null){
            synchronized (OkHttp3.class){
                if (instance==null){
                    instance = new OkHttp3();
                }
            }
        }
        return instance;
    }

    public interface NetCallBack{
        void onSuccess(Object o);
        void onFailed(Exception e);
    }

    public void doGet(String url, final Class clazz, final NetCallBack netCallBack){
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(data, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onSuccess(o);
                    }
                });
            }
        });
    }
    public void doPost(String url, Map<String,String>map, final NetCallBack netCallBack){
        FormBody.Builder builder = new FormBody.Builder();
        for (String key:map.keySet()){
            map.put(key,map.get(key));
        }
        Request request = new Request.Builder()
                .post(builder.build())
                .url(url)
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onSuccess(data);
                    }
                });
            }
        });
    }
}
