package cn.fizzo.watch.sdksample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.fizzo.watch.sdksample.R;
import cn.fizzo.watch.sdksample.entity.net.GetDeviceListRE;

/**
 * @author Raul.Fan
 * @email 35686324@qq.com
 * @date 2018/9/12 13:58
 */
public class DeviceListAdapter extends BaseAdapter {


    private Context mContext;
    private LayoutInflater mInflater;
    private List<GetDeviceListRE.HrdevicesBean> mData;

    public DeviceListAdapter(Context context, List<GetDeviceListRE.HrdevicesBean> mList) {
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
            convertView = mInflater.inflate(R.layout.item_list_device, null);
            holder = new DeviceVH(convertView);
            convertView.setTag(holder);
        } else {
            holder = (DeviceVH) convertView.getTag();
        }

        GetDeviceListRE.HrdevicesBean device = mData.get(position);

        holder.tvName.setText("(" + device.hrdevicenumber + ")" + device.name);
        holder.tvWatchMac.setText(device.macaddr);

        if (device.select){
            holder.vSelect.setBackgroundResource(R.drawable.ic_select_yes);
        }else {
            holder.vSelect.setBackgroundResource(R.drawable.ic_select_no);
        }


        return convertView;
    }

    class DeviceVH {
        TextView tvName;//姓名
        TextView tvWatchMac;//手表地址
        View vSelect;//选择状态

        public DeviceVH(View convertView) {
            tvName = (TextView) convertView.findViewById(R.id.tv_name);
            tvWatchMac = (TextView)convertView.findViewById(R.id.tv_watch_mac);
            vSelect = convertView.findViewById(R.id.v_select_state);
        }
    }
}
