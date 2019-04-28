package cn.fizzo.watch.sdksample.network;

import android.content.Context;

import org.xutils.http.RequestParams;

import cn.fizzo.watch.sdksample.config.UrlConfig;

/**
 * Created by Administrator on 2016/6/28.
 * 创建网络交互的参数
 */
public class RequestParamsBuilder {


    /**
     * 获取登录用的参数
     *
     * @param context
     * @return
     */
    public static RequestParams buildGetVersionList(final Context context) {
        MyRequestParams requestParams = new MyRequestParams(context, UrlConfig.GET_VERSION_LIST);
        return requestParams;
    }

    /**
     *  获得学校列表
     * @param context
     * @return
     */
    public static RequestParams buildGetSchoolList(final Context context){
        MyRequestParams requestParams = new MyRequestParams(context, UrlConfig.GET_SCHOOL_LIST);
        return requestParams;
    }

    /**
     *  获取手环包列表
     * @param context
     * @param schoolId
     * @return
     */
    public static RequestParams buildGetPkgList(final Context context, final int schoolId){
        MyRequestParams requestParams = new MyRequestParams(context, UrlConfig.GET_PKG_LIST);
        requestParams.addBodyParameter("storeid", schoolId + "");
        return requestParams;
    }


    /**
     * 获取手环列表
     * @param context
     * @param pkgId
     * @return
     */
    public static RequestParams buildGetDeviceList(final Context context,final int pkgId){
        MyRequestParams requestParams = new MyRequestParams(context, UrlConfig.GET_PKG_INFO);
        requestParams.addBodyParameter("hrpackageid", pkgId + "");
        return requestParams;
    }

}
