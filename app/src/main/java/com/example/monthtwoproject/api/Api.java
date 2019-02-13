package com.example.monthtwoproject.api;

/**
 * @Auther: 李
 * @Date: 2019/2/13 09:23:00
 * @Description:
 */
public class Api {
    public static final String BASE_URL="http://172.17.8.100";
    //商品列表
    public static String SHOPLIST=BASE_URL+"/small/commodity/v1/commodityList";
    //登录
    public static String LOGIN=BASE_URL+"/small/user/v1/login";
    //轮播
    public static final String BUNNER = BASE_URL+"/small/commodity/v1/bannerShow";
    //注册
    public static final String REGIST = BASE_URL+"/small/user/v1/register";
    //商品详情
    public static final String SHOP_DETAILS = BASE_URL+"/small/commodity/v1/findCommodityDetailsById?commodityId=";
    //圈子列表
    public static final String  SHOP_CIRCLE = BASE_URL+"/small/circle/v1/findCircleList?count=5&page=";
    //购物车
    public static final String SHOPPING_CART = "http://www.zhaoapi.cn/product/getCarts";
}
