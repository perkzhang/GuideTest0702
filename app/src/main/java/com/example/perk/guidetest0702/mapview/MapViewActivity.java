package com.example.perk.guidetest0702.mapview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.perk.guidetest0702.R;

public class MapViewActivity extends AppCompatActivity {

    MapView mMapView;
    BaiduMap mBaiduMap;
    Marker mMarker;

    private ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map_view);

        Double longitude = Double.parseDouble(getIntent().getStringExtra("longitude"));
        Double latitude = Double.parseDouble(getIntent().getStringExtra("latitude"));

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        //获取地图控制器

        //定义Marker坐标点
        LatLng point = new LatLng(latitude,longitude);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker);
        //构建MarkerOption
        OverlayOptions options = new MarkerOptions()
                .position(point) //设置marker的位置
                .icon(bitmap) //设置marker图标
                .zIndex(9)//设置marker所在层级
                .draggable(true);//设置手势拖拽

        mBaiduMap.addOverlay(options);
        //将marker添加到地图上
        mMarker = (Marker) (mBaiduMap.addOverlay(options));

        /**
         * 设置中心点
         */

        LatLng center = new LatLng(latitude,longitude);
        MapStatus mapStatus = new MapStatus.Builder()
                .target(center)
                .zoom(20)
                .build();//定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        initViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBaiduMap.setMyLocationEnabled(false);
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mMapView = null;
    }

    private void initViews(){
        mBack = (ImageView) findViewById(R.id.image_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
