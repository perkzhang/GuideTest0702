package com.example.perk.guidetest0702;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.LocationClient;
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

public class DisplayActivity extends AppCompatActivity {
    // 定位相关
    LocationClient mLocClient;
    //public MyLocationListenner myListener = new MyLocationListenner();

    private TextView mName;
    private TextView mNumber;
    private TextView mLocation;
    private TextView mLockNumber;
    private TextView mPhotoPath;
    private ImageView mPotoDisPlay;
    private ImageView mImageBack;

    MapView mMapView;
    BaiduMap mBaiduMap;
    Marker marker;

    boolean isFirstLoc = true; // 是否首次定位



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_display);
        initViwes();
        getResource();

        Double longitude = Double.parseDouble(getIntent().getStringExtra("longitude"));
        Double latitude = Double.parseDouble(getIntent().getStringExtra("latitude"));

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();//获取地图控制器
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        /*mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();*/


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
        marker = (Marker) (mBaiduMap.addOverlay(options));

        //调用BaiduMap对象的setOnMarkerDragListener方法设置marker拖拽的监听
        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            public void onMarkerDrag(Marker marker) {
                //拖拽中
            }
            public void onMarkerDragEnd(Marker marker) {
                //拖拽结束
            }
            public void onMarkerDragStart(Marker marker) {
                //开始拖拽
            }
        });

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





    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();

    }

    private void initViwes(){
        mName = (TextView) findViewById(R.id.tv_name_display);
        mNumber = (TextView) findViewById(R.id.tv_number_display);
        mLocation = (TextView) findViewById(R.id.tv_location_display);
        mLockNumber = (TextView) findViewById(R.id.tv_locknumber_display);
        mPhotoPath = (TextView) findViewById(R.id.tv_image_path_display);
        mPotoDisPlay = (ImageView) findViewById(R.id.image_photo_display);
        mImageBack = (ImageView) findViewById(R.id.image_back);
        mImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void getResource(){
        String username = getIntent().getStringExtra("username");
        String usernumber = getIntent().getStringExtra("usernumber");
        String address = getIntent().getStringExtra("address");
        String locknumber = getIntent().getStringExtra("locknumber");
        String photopath = getIntent().getStringExtra("photopath");


        mName.setText(username);
        mNumber.setText(usernumber);
        mLocation.setText(address);
        mLockNumber.setText(locknumber);
        mPhotoPath.setText(photopath);
        mPotoDisPlay.setImageBitmap(BitmapFactory.decodeFile(photopath));



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
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mMapView = null;

    }

    /**
     * 定位SDK监听函数
     */
   /* public class MyLocationListenner implements BDLocationListener {



        @Override
        public void onReceiveLocation(BDLocation location) {
            Double longitude = Double.parseDouble(getIntent().getStringExtra("longitude"));
            Double latitude = Double.parseDouble(getIntent().getStringExtra("latitude"));

            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(latitude)
                    .longitude(longitude).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(latitude, longitude);
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }*/


}
