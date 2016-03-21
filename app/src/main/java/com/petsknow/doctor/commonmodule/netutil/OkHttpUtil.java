package com.petsknow.doctor.commonmodule.netutil;

import com.alibaba.fastjson.JSON;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.constant.PetsknowDoctorApplication;
import com.petsknow.doctor.commonmodule.utils.L;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.usermodule.manger.UserManger;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * Created by yukuo on 2016/3/18.
 * 这是一个okhttp请求的工具类
 */
public class OkHttpUtil {
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();
    //请求类型
    public static final MediaType mJSON = MediaType.parse("application/json; charset=utf-8");
    //请求编码格式
    private static final String CHARSET_NAME = "UTF-8";

    /**
     * 这是一个get请求拼接请求路径的方法
     *
     * @param requestPacket
     * @return
     */
    public static String appendUrl(RequestPacket requestPacket) {
        return requestPacket.url + "?" + appendArguments(requestPacket);
    }

    /**
     * 这是一个拼接get请求请求参数的方法
     *
     * @param requestPacket
     * @return
     */
    public static String appendArguments(RequestPacket requestPacket) {
        String argument = "";
        for (String key : requestPacket.arguments.keySet()) {
            if (requestPacket.getArgument(key) != null) {
                if (argument.equals("")) {
                    argument = key + "=" + requestPacket.getArgument(key);
                } else {
                    argument = argument + "&" + key + "=" + requestPacket.getArgument(key);
                }
            }
        }
        return argument;
    }

    /**
     * 这是一个请求的网络的方法
     *
     * @param method
     * @param requestPacket
     * @param listener
     */
    public static void Request(int method, RequestPacket requestPacket, final ResponseListener listener) {
        Request request = null;
        Request.Builder builder = new Request.Builder();
        //设置请求类型
        builder.header("Content-Type", "application/json");
        // 添加头信息
        for (String key : requestPacket.headers.keySet()) {
            if (requestPacket.getHeader(key) != null) {
                builder.header(key, requestPacket.headers.get(key));
                L.i(key, requestPacket.headers.get(key));
            }
        }
        //设置请求的url
        if (method == RequestPacket.POST) {
            //如果是post请求
            RequestBody body = RequestBody.create(mJSON, JSON.toJSONString(requestPacket.arguments));
            request = new Request.Builder()
                    .url(requestPacket.url)
                    .post(body)
                    .addHeader("dt_id", UserManger.getUserId() + "")
                    .build();
        } else if (method == RequestPacket.GET) {
            String url = appendUrl(requestPacket);
            //如果是get请求
            request = builder.url(url).build();
        }
        L.i("", request.toString() + JSON.toJSONString(requestPacket.arguments));
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                listener.onFailure(request);
                if (e instanceof SocketTimeoutException) {
                    T.showLong(PetsknowDoctorApplication.context, R.string.net_error);
                } else if (e instanceof ConnectException) {
                    T.showLong(PetsknowDoctorApplication.context, R.string.net_null);
                }
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                final String result = response.body().string();
                PetsknowDoctorApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         * 获取要转换的json类型
                         */
                        Type genType = listener.getClass().getGenericSuperclass();
                        Class clzss = (Class) ((ParameterizedType) genType).getActualTypeArguments()[0];
                        try {
                            L.i("响应", result);
                            listener.onSuccess(JSON.parseObject(result, clzss));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
