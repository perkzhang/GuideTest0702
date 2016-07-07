package com.example.perk.guidetest0702.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.perk.guidetest0702.R;
import com.example.perk.guidetest0702.valueclass.Info;

import java.util.ArrayList;

/**
 * Created by perk on 2016/7/5.
 */
public class DisplayAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<Info> mData;

    public DisplayAdapter(Context context, ArrayList<Info> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        public TextView mUserName;
        public TextView mUserNumber;
        public TextView mLocation;
        public TextView mLockNumber;
        public TextView mImagePath;

        public TextView mLongitude;
        public TextView mLatitude;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.lv_display_format,null);
            holder.mUserName = (TextView) view.findViewById(R.id.tv_name_display);
            holder.mUserNumber = (TextView) view.findViewById(R.id.tv_number_display);
            holder.mLocation = (TextView) view.findViewById(R.id.tv_location_display);
            holder.mLockNumber = (TextView) view.findViewById(R.id.tv_location_display);
            holder.mImagePath  = (TextView) view.findViewById(R.id.tv_image_path_display);

            holder.mLongitude = (TextView) view.findViewById(R.id.tv_longitude);
            holder.mLatitude = (TextView) view.findViewById(R.id.tv_latitude);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        Info info = mData.get(i);

        holder.mUserName.setText(info.getmName());
        holder.mUserNumber.setText(info.getmNumber());
        holder.mLocation.setText(info.getmName());
        holder.mLockNumber.setText(info.getmLockNumber());
        holder.mImagePath.setText(info.getmPhotoPath());

        holder.mLongitude.setText(info.getmLongitude());
        holder.mLatitude.setText(info.getmLatitude());
        return view;
    }
}
