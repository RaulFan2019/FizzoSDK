package cn.fizzo.watch.sdksample.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import org.json.JSONArray;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.fizzo.watch.sdksample.R;
import cn.fizzo.watch.sdksample.adapter.DeviceListAdapter;
import cn.fizzo.watch.sdksample.entity.BleScanAE;
import cn.fizzo.watch.sdksample.entity.net.BaseRE;
import cn.fizzo.watch.sdksample.entity.net.GetDeviceListRE;
import cn.fizzo.watch.sdksample.entity.net.PkgListRE;
import cn.fizzo.watch.sdksample.entity.net.SchoolListRE;
import cn.fizzo.watch.sdksample.entity.net.VersionRE;
import cn.fizzo.watch.sdksample.network.BaseResponseParser;
import cn.fizzo.watch.sdksample.network.HttpExceptionHelper;
import cn.fizzo.watch.sdksample.network.RequestParamsBuilder;
import cn.fizzo.watch.utils.Log;

/**
 * @author Raul.Fan
 * @email 35686324@qq.com
 * @date 2018/11/8 15:21
 */
public class UpdateTestSelectActivity extends BaseActivity {

    private static final String TAG = "UpdateTestActivity";

    private static final int MSG_GET_VERSION_OK = 0x01;
    private static final int MSG_GET_VERSION_ERROR = 0x02;
    private static final int MSG_GET_SCHOOL_OK = 0x03;
    private static final int MSG_GET_SCHOOL_ERROR = 0x04;
    private static final int MSG_GET_PKG_OK = 0x05;
    private static final int MSG_GET_PKG_ERROR = 0x06;
    private static final int MSG_GET_DEVICE_LIST_OK = 0x07;
    private static final int MSG_GET_DEVICE_LIST_ERROR = 0x08;


    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.tv_school)
    TextView tvSchool;
    @BindView(R.id.tv_pkg)
    TextView tvPkg;
    @BindView(R.id.list)
    ListView list;
    @BindView(R.id.btn_start)
    Button btnStart;

    private ArrayAdapter mAdapter;

    VersionRE mSelectVersion;
    List<VersionRE> listVersion = new ArrayList<>();
    List<String> listVersionInfo = new ArrayList<>();

    SchoolListRE.StoresBean mSelectSchool;
    List<SchoolListRE.StoresBean> listSchool = new ArrayList<>();
    List<String> listSchoolInfo = new ArrayList<>();

    PkgListRE.HrpackagesBean mSelectPkg;
    List<PkgListRE.HrpackagesBean> listPkg = new ArrayList<>();
    List<String> listPkgInfo = new ArrayList<>();

    DeviceListAdapter adapterDeviceList;
    private List<GetDeviceListRE.HrdevicesBean> listDevice = new ArrayList<>();
    private List<GetDeviceListRE.HrdevicesBean> listUpdateDevice = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_test;
    }


    @OnClick({R.id.btn_select_version, R.id.btn_select_pkg, R.id.btn_start,
            R.id.btn_select_school, R.id.btn_select_all,R.id.btn_select_all_none})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_select_version:
                postGetVersionList();
                break;
            case R.id.btn_select_school:
                postGetSchoolList();
                break;
            case R.id.btn_select_pkg:
                if (mSelectSchool != null) {
                    postGetPkgList();
                }
                break;
            case R.id.btn_start:
                if (mSelectVersion == null) {
                    Toast.makeText(UpdateTestSelectActivity.this, "请选择升级版本", Toast.LENGTH_SHORT);
                    return;
                }
                if (mSelectPkg == null) {
                    Toast.makeText(UpdateTestSelectActivity.this, "请选择升级手环包", Toast.LENGTH_SHORT);
                    return;
                }
                listUpdateDevice.clear();
                for (GetDeviceListRE.HrdevicesBean device : listDevice) {
                    if (device.select) {
                        listUpdateDevice.add(device);
                    }
                }
                if (listUpdateDevice.size() == 0) {
                    Toast.makeText(UpdateTestSelectActivity.this, "请选择升级手环", Toast.LENGTH_SHORT);
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("version", mSelectVersion);
                bundle.putSerializable("device", (Serializable) listUpdateDevice);
                startActivity(UpdateTestTodoActivity.class, bundle);
                break;
            //选择所有
            case R.id.btn_select_all:
                if (listDevice != null && listDevice.size() > 0) {
                    listUpdateDevice.clear();
                    for (GetDeviceListRE.HrdevicesBean device : listDevice) {
                        device.select = true;
                        listUpdateDevice.add(device);
                    }
                    adapterDeviceList.notifyDataSetChanged();
                }
                break;
            case R.id.btn_select_all_none:
                if (listDevice != null && listDevice.size() > 0) {
                    listUpdateDevice.clear();
                    for (GetDeviceListRE.HrdevicesBean device : listDevice) {
                        device.select = false;
                    }
                    adapterDeviceList.notifyDataSetChanged();
                }
                break;

        }
    }

    @Override
    protected void myHandleMsg(Message msg) {
        switch (msg.what) {
            case MSG_GET_VERSION_OK:
                updateVersionListView();
                break;
            case MSG_GET_VERSION_ERROR:
                Toast.makeText(UpdateTestSelectActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
                break;
            case MSG_GET_SCHOOL_OK:
                updateSchoolListView();
                break;
            case MSG_GET_SCHOOL_ERROR:
                Toast.makeText(UpdateTestSelectActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
                break;
            case MSG_GET_PKG_OK:
                updatePkgListView();
                break;
            case MSG_GET_PKG_ERROR:
                Toast.makeText(UpdateTestSelectActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
                break;
            case MSG_GET_DEVICE_LIST_OK:
                adapterDeviceList = new DeviceListAdapter(UpdateTestSelectActivity.this, listDevice);
                list.setAdapter(adapterDeviceList);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        listDevice.get(i).select = !listDevice.get(i).select;
                        adapterDeviceList.notifyDataSetChanged();
                    }
                });

                break;
            case MSG_GET_DEVICE_LIST_ERROR:
                Toast.makeText(UpdateTestSelectActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void doMyCreate() {

    }

    @Override
    protected void causeGC() {

    }


    /**
     * 更新版本信息列表
     */
    private void updateVersionListView() {
        mAdapter = new ArrayAdapter<String>(UpdateTestSelectActivity.this, android.R.layout.simple_expandable_list_item_1, listVersionInfo);
        list.setAdapter(mAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSelectVersion = listVersion.get(i);
                tvVersion.setText(mSelectVersion.name);
            }
        });
    }

    /**
     * 更新学校列表
     */
    private void updateSchoolListView() {
        mAdapter = new ArrayAdapter<String>(UpdateTestSelectActivity.this, android.R.layout.simple_expandable_list_item_1, listSchoolInfo);
        list.setAdapter(mAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSelectSchool = listSchool.get(i);
                tvSchool.setText(mSelectSchool.name);
            }
        });
    }


    /**
     * 更新手环包列表
     */
    private void updatePkgListView() {
        mAdapter = new ArrayAdapter<String>(UpdateTestSelectActivity.this, android.R.layout.simple_expandable_list_item_1, listPkgInfo);
        list.setAdapter(mAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSelectPkg = listPkg.get(i);
                tvPkg.setText(mSelectPkg.name);
                postGetDeviceList();
            }
        });
    }


    /**
     * 获取版本信息列表
     */
    private void postGetVersionList() {
        x.task().post(new Runnable() {
            @Override
            public void run() {
                RequestParams params = RequestParamsBuilder.buildGetVersionList(UpdateTestSelectActivity.this);
                mCancelable = x.http().post(params, new Callback.CommonCallback<BaseRE>() {
                    @Override
                    public void onSuccess(BaseRE result) {
                        if (result.errorcode == BaseResponseParser.ERROR_CODE_NONE) {
//                            Log.e(TAG, "postGetVersionList: " + result.result);
                            listVersion = JSON.parseArray(result.result, VersionRE.class);
                            listVersionInfo.clear();
                            for (VersionRE re : listVersion) {
                                listVersionInfo.add(re.name + "\n" + re.description);
                            }
                            mHandler.sendEmptyMessage(MSG_GET_VERSION_OK);
                        } else {
                            Message msg = new Message();
                            msg.what = MSG_GET_VERSION_ERROR;
                            msg.obj = result.errormsg;
                            mHandler.sendMessage(msg);
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Message msg = new Message();
                        msg.what = MSG_GET_VERSION_ERROR;
                        msg.obj = HttpExceptionHelper.getErrorMsg(ex);
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            }
        });
    }

    /**
     * 获取学校列表
     */
    private void postGetSchoolList() {
        x.task().post(new Runnable() {
            @Override
            public void run() {
                RequestParams params = RequestParamsBuilder.buildGetSchoolList(UpdateTestSelectActivity.this);
                mCancelable = x.http().post(params, new Callback.CommonCallback<BaseRE>() {
                    @Override
                    public void onSuccess(BaseRE result) {
                        if (result.errorcode == BaseResponseParser.ERROR_CODE_NONE) {
                            SchoolListRE listRE = JSON.parseObject(result.result, SchoolListRE.class);
                            listSchool.clear();
                            listSchool.addAll(listRE.stores);
                            listSchoolInfo.clear();
                            for (SchoolListRE.StoresBean storesBean : listSchool) {
                                listSchoolInfo.add(storesBean.name);
                            }
                            mHandler.sendEmptyMessage(MSG_GET_SCHOOL_OK);
                        } else {
                            Message msg = new Message();
                            msg.what = MSG_GET_SCHOOL_ERROR;
                            msg.obj = result.errormsg;
                            mHandler.sendMessage(msg);
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Message msg = new Message();
                        msg.what = MSG_GET_SCHOOL_ERROR;
                        msg.obj = HttpExceptionHelper.getErrorMsg(ex);
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            }
        });
    }

    private void postGetPkgList() {
        x.task().post(new Runnable() {
            @Override
            public void run() {
                RequestParams params = RequestParamsBuilder.buildGetPkgList(UpdateTestSelectActivity.this, mSelectSchool.id);
                mCancelable = x.http().post(params, new Callback.CommonCallback<BaseRE>() {
                    @Override
                    public void onSuccess(BaseRE result) {
                        if (result.errorcode == BaseResponseParser.ERROR_CODE_NONE) {
                            PkgListRE listRE = JSON.parseObject(result.result, PkgListRE.class);
                            listPkg.clear();
                            listPkg.addAll(listRE.hrpackages);
                            listPkgInfo.clear();
                            for (PkgListRE.HrpackagesBean pkg : listPkg) {
                                listPkgInfo.add(pkg.name);
                            }
                            mHandler.sendEmptyMessage(MSG_GET_PKG_OK);
                        } else {
                            Message msg = new Message();
                            msg.what = MSG_GET_PKG_ERROR;
                            msg.obj = result.errormsg;
                            mHandler.sendMessage(msg);
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Message msg = new Message();
                        msg.what = MSG_GET_PKG_ERROR;
                        msg.obj = HttpExceptionHelper.getErrorMsg(ex);
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            }
        });
    }


    private void postGetDeviceList() {
        x.task().post(new Runnable() {
            @Override
            public void run() {
                RequestParams params = RequestParamsBuilder.buildGetDeviceList(UpdateTestSelectActivity.this, mSelectPkg.id);
                mCancelable = x.http().post(params, new Callback.CommonCallback<BaseRE>() {
                    @Override
                    public void onSuccess(BaseRE result) {
                        if (result.errorcode == BaseResponseParser.ERROR_CODE_NONE) {
                            Log.e(TAG, "postGetDeviceList:" + result.result);
                            GetDeviceListRE re = JSON.parseObject(result.result, GetDeviceListRE.class);
                            if (re.hrdevices != null) {
                                listDevice.clear();
                                listDevice.addAll(re.hrdevices);
                            }
                            mHandler.sendEmptyMessage(MSG_GET_DEVICE_LIST_OK);
                        } else {
                            Message msg = new Message();
                            msg.what = MSG_GET_DEVICE_LIST_ERROR;
                            msg.obj = result.errormsg;
                            mHandler.sendMessage(msg);
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Message msg = new Message();
                        msg.what = MSG_GET_DEVICE_LIST_ERROR;
                        msg.obj = HttpExceptionHelper.getErrorMsg(ex);
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            }
        });
    }

}
