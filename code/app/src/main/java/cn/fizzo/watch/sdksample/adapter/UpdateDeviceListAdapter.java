package cn.fizzo.watch.sdksample.adapter;

import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.fizzo.watch.sdksample.R;
import cn.fizzo.watch.sdksample.entity.DeviceUpdateEntity;
import cn.fizzo.watch.sdksample.entity.net.GetDeviceListRE;

/**
 * @author Raul.Fan
 * @email 35686324@qq.com
 * @date 2018/11/9 14:01
 */
public class UpdateDeviceListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<DeviceUpdateEntity> mData;

    public UpdateDeviceListAdapter(Context context, List<DeviceUpdateEntity> mList) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = mList;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DeviceVH holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_list_update_status, null);
            holder = new DeviceVH(convertView);
            convertView.setTag(holder);
        } else {
            holder = (DeviceVH) convertView.getTag();
        }

        DeviceUpdateEntity updateEntity = mData.get(position);

        holder.tvName.setText("(" + updateEntity.device.hrdevicenumber + ")" + updateEntity.device.name);
        holder.tvWatchMac.setText(updateEntity.device.macaddr);

        if (updateEntity.updateStaus == DeviceUpdateEntity.STATUS_FINISH){
            holder.tvStatus.setTextColor(Color.GREEN);
        }else {
            holder.tvStatus.setTextColor(Color.GRAY);
        }

        switch (updateEntity.updateStaus) {
            case DeviceUpdateEntity.STATUS_INIT:
                holder.tvStatus.setText("未开始");
                break;
            case DeviceUpdateEntity.STATUS_CONNECTING:
                holder.tvStatus.setText("正在连接");
                break;
            case DeviceUpdateEntity.STATUS_CONNECTED:
                holder.tvStatus.setText("已连接");
                break;
            case DeviceUpdateEntity.STATUS_MANUFACTURER_FAIL:
                holder.tvStatus.setText("验证厂商失败");
                break;
            case DeviceUpdateEntity.STATUS_UPDATING:
                holder.tvStatus.setText("准备升级");
                break;
            case DeviceUpdateEntity.STATUS_UPDATE_OVER:
                holder.tvStatus.setText("升级结束");
                break;
            case DeviceUpdateEntity.STATUS_UPDATE_ERROR:
                holder.tvStatus.setText("升级失败");
                break;
            case DeviceUpdateEntity.STATUS_CLEAR_FLASH:
                holder.tvStatus.setText("清除FLASH");
                break;
            case DeviceUpdateEntity.STATUS_FINISH:
                holder.tvStatus.setText("完成");
                break;
            case DeviceUpdateEntity.STATUS_CONNECT_FAIL:
                holder.tvStatus.setText("连接失败");
                break;
            case DeviceUpdateEntity.STATUS_CONNECT_OUT_OF_TIME:
                holder.tvStatus.setText("连接超时");
                break;
            case DeviceUpdateEntity.STATUS_UPDATE_READY_OUT_OF_TIME:
                holder.tvStatus.setText("准备升级超时");
                break;
            case DeviceUpdateEntity.STATUS_CLEAR_FLASH_OUT_OF_TIME:
                holder.tvStatus.setText("清除手表缓存超时");
                break;
        }

        if (updateEntity.updateStaus == DeviceUpdateEntity.STATUS_FINISH) {
            holder.tvStatus.setText("升级成功");
        }

        if (updateEntity.version != null) {
            holder.tvVersion.setText(updateEntity.version);
        }

        return convertView;
    }

    class DeviceVH {
        TextView tvName;//姓名
        TextView tvWatchMac;//手表地址
        TextView tvStatus;//选择状态
        TextView tvVersion;
        LinearLayout llBase;

        public DeviceVH(View convertView) {
            tvName = (TextView) convertView.findViewById(R.id.tv_name);
            tvWatchMac = (TextView) convertView.findViewById(R.id.tv_watch_mac);
            tvStatus = convertView.findViewById(R.id.v_select_state);
            tvVersion = convertView.findViewById(R.id.tv_version);
            llBase = convertView.findViewById(R.id.ll_base);
        }
    }
}
