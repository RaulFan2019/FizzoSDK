package cn.fizzo.watch.sdksample.config;

/**
 * @author Raul.Fan
 * @email 35686324@qq.com
 * @date 2018/8/22 10:56
 */
public class UrlConfig {




    /**
     * 根据编译版本获取Host Ip 地址
     *
     * @return
     */
    public static String getHostIp() {
        return "hhttp://www.123yd.cn/fitness/V2/";
    }



    public static final String GET_VERSION_LIST = "http://120.26.217.228/heartrate/V1/HrUpload/getFirmwareList";

    public static final String GET_SCHOOL_LIST = "http://www.123yd.cn/fitness/V2/School/getSchoolList";

    public static final String GET_PKG_LIST = "http://www.123yd.cn/fitness/V2/School/getSchoolHrpackageList";

    public static final String GET_PKG_INFO = "http://www.123yd.cn/fitness/V2/School/getHrpackageHrdeviceList";
}
