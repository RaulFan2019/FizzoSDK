package cn.fizzo.watch.sdksample.entity;

import cn.fizzo.watch.sdksample.entity.net.GetDeviceListRE;
import cn.fizzo.watch.sdksample.entity.net.PkgListRE;

/**
 * @author Raul.Fan
 * @email 35686324@qq.com
 * @date 2018/11/9 13:56
 */
public class DeviceUpdateEntity {

    public static final int STATUS_INIT = 0;            //初始化
    public static final int STATUS_CONNECTING = 1;       //连接中
    public static final int STATUS_CONNECTED = 2;       //已连接
    public static final int STATUS_MANUFACTURER_FAIL =3; //验证厂商失败
    public static final int STATUS_UPDATING = 4;        //升级中
    public static final int STATUS_UPDATE_OVER = 5;     //升级结束
    public static final int STATUS_UPDATE_ERROR = 6;    //DFU 升级错误
    public static final int STATUS_CLEAR_FLASH = 7;     //清除FLASH
    public static final int STATUS_FINISH = 8;


    public static final int STATUS_CONNECT_FAIL = 11;
    public static final int STATUS_CONNECT_OUT_OF_TIME = 12;
    public static final int STATUS_UPDATE_READY_OUT_OF_TIME = 13;
    public static final int STATUS_CLEAR_FLASH_OUT_OF_TIME = 14;


    public GetDeviceListRE.HrdevicesBean device;
    public int updateStaus;
    public String version;

    public DeviceUpdateEntity() {
    }

    public DeviceUpdateEntity(GetDeviceListRE.HrdevicesBean device, int updateStaus) {
        this.device = device;
        this.updateStaus = updateStaus;
    }
}
