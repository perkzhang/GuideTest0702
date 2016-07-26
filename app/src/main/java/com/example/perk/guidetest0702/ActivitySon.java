package com.example.perk.guidetest0702;

import android.app.usage.NetworkStatsManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.example.perk.guidetest0702.database.LocalDatabaseHelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ActivitySon extends AppCompatActivity implements View.OnClickListener {


    private static final int REQUEST_WRITE_NFC = 1;
    private static final int REQUEST_TAKE_PHOTO = 2;


    private EditText mName;
    private EditText mNumber;
    private TextView mAddress;
    private EditText mLockNumber;

    private Button mRecord;
    private Button mRead;
    private Button mWriteNFC;
    private Button mLocation;
    private Button mHelp;

    private TextView mDisplay;
    private TextView mNfcId;
    private TextView mDetail;

    private ImageView mTakePhoto;
    private ImageView mImageBack;
    private ImageView mImageReadNfc;

    private LocalDatabaseHelper dbHelper;

    String mLongitude;//经度
    String mLatitude;//纬度


    //1、初始化LoacationClint类
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            mLongitude = String.valueOf(location.getLongitude());//经度
            mLatitude = String.valueOf(location.getLatitude());//纬度

            Log.d("zgl", "mLatitude = " + mLatitude);
            Log.d("zgl", "mLontitude = " + mLongitude);
            location.getAddrStr();//地址信息

            mAddress.setText(location.getAddrStr());
            if (mAddress.getText() != null) {
                mLocationClient.stop();

            }

            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());//
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());//
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());//

                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //判断jni时候调用
        SDKInitializer.initialize(getApplicationContext());
        //Context需要全进程有效context使用getApplicationContext()声明
        mLocationClient = new LocationClient(getApplicationContext());//声明LocationClient类
        mLocationClient.registerLocationListener(myListener);//注册监听

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//FLAG_KEEP_SCREEN_ON保持屏幕常亮
        checkWifi();
        checkOpenGPS();
        setContentView(R.layout.activity_activity_son);
        initViews();
        initLocation();

        dbHelper = new LocalDatabaseHelper(ActivitySon.this, "LockInfo.db", null, 2);
    }

    //2、配置定位SDK参数
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    private void initViews() {
        mName = (EditText) findViewById(R.id.edit_name);
        mNumber = (EditText) findViewById(R.id.edit_userno);
        mAddress = (TextView) findViewById(R.id.edit_add);
        mLockNumber = (EditText) findViewById(R.id.edit_lockno);

        mDisplay = (TextView) findViewById(R.id.tv_display);
        mNfcId = (TextView) findViewById(R.id.tv_nfc_id);
        mDetail = (TextView) findViewById(R.id.tv_detail);
        mDetail.setOnClickListener(this);

        mRecord = (Button) findViewById(R.id.but_record);
        mRecord.setOnClickListener(this);

        mLocation = (Button) findViewById(R.id.but_located);
        mLocation.setOnClickListener(this);

        mHelp = (Button) findViewById(R.id.btn_help);
        mHelp.setOnClickListener(this);

        mTakePhoto = (ImageView) findViewById(R.id.image_takePhoto);
        mTakePhoto.setOnClickListener(this);
        mImageBack = (ImageView) findViewById(R.id.image_back);
        mImageBack.setOnClickListener(this);
        mImageReadNfc = (ImageView) findViewById(R.id.image_read_nfc);
        mImageReadNfc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_record:
                String username = mName.getText().toString().trim();
                String usernumber = mNumber.getText().toString().trim();
                String address = mAddress.getText().toString().trim();
                String locknumber = mLockNumber.getText().toString().trim();
                AssetManager takephoto = mTakePhoto.getContext().getAssets();
                Log.d("zgl", "takephoto = " + takephoto);
                if (username.isEmpty() || usernumber.isEmpty() || locknumber.isEmpty() || address.isEmpty()) {
                    Toast.makeText(ActivitySon.this, "请输入完整的信息", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    Toast.makeText(ActivitySon.this, "ok！", Toast.LENGTH_SHORT).show();
                    dbHelper.getWritableDatabase();//此方法一调用则回调SQLiteOpenHelper中onCreat()方法；
                    addData(username, usernumber, address, locknumber);
                    mName.setText("");
                    mNumber.setText("");
                    mAddress.setText("");
                    mLockNumber.setText("");
                    mTakePhoto.setImageURI(Uri.parse("res://com.example.perk.guidetest0702/" + R.drawable.ic_action_camera_normal));
                }
                Toast.makeText(ActivitySon.this, "记录成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.but_located:
                mLocationClient.start();//打开百度定位客户端
                break;
            case R.id.image_takePhoto:
                try {
                    if (TextUtils.isEmpty(mCurrentPhotoPath)){
                        takePhoto();
                    }else {
                        startActivity(new Intent(ActivitySon.this,ShowPictureActivity.class).putExtra("imagePath",mCurrentPhotoPath).putExtra("SHOW_PICTURE_LOCAL",true));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.image_read_nfc:
                if (mName.getText().toString().equals("")) {
                    Toast.makeText(ActivitySon.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Intent intent = new Intent(ActivitySon.this, ActivityNFC.class);
                    intent.putExtra("name", mName.getText().toString());
                    startActivityForResult(intent, REQUEST_WRITE_NFC);
                }
                break;
            case R.id.image_back:
                finish();
                break;
            case R.id.tv_detail:
                /*try {
                    querry();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                startActivity(new Intent(ActivitySon.this, DisplayActivity.class));
                break;
            case R.id.btn_help:
                startActivity(new Intent(ActivitySon.this, HActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
            mTakePhoto.setImageURI(Uri.parse(mCurrentPhotoPath));
        } else if (requestCode == REQUEST_WRITE_NFC && resultCode == RESULT_OK) {
            String id = data.getStringExtra("id");
            Log.d("zgl1", "id = " + id);
            mDisplay.setText(id);
            mDisplay.setVisibility(View.VISIBLE);
            //  mNfcId.setVisibility(View.VISIBLE);
        }

    }

    //添加数据
    private void addData(String username, String usernumber, String address, String locknumber) {
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("usernumber", usernumber);
        values.put("address", address);
        values.put("locknumber", locknumber);
        //独立添加经纬度
        values.put("longitude", mLongitude);
        values.put("latitude", mLatitude);
        //独立添加图片路径
        values.put("photopath", mCurrentPhotoPath);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert("Info", null, values);
        values.clear();


    }

    //更新新数据
    private void updata(String username, String usernumber, String locknumber) {
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("usernumber", usernumber);
        values.put("locknumber", locknumber);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

    }

    //查询数据
    private void querry() throws InterruptedException {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Info", null, null, null, null, null, null);

            /*do {
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String usernumber = cursor.getString(cursor.getColumnIndex("usernumber"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String locknumber = cursor.getString(cursor.getColumnIndex("locknumber"));

                String longitude = cursor.getString(cursor.getColumnIndex("longitude"));
                String latitude = cursor.getString(cursor.getColumnIndex("latitude"));

                String photopath = cursor.getString(cursor.getColumnIndex("photopath"));

                mDisplay.append(photopath
                        + "\n" +username
                        + "\n" + usernumber
                        + "\n" + address
                        + "\n" + locknumber
                        + "\n" + longitude
                        + "\n" + latitude);
                Toast.makeText(ActivitySon.this,username,Toast.LENGTH_SHORT).show();


                Thread.sleep(1000);

            }while (cursor.moveToNext());*/

        String username = cursor.getString(cursor.getColumnIndex("username"));
        String usernumber = cursor.getString(cursor.getColumnIndex("usernumber"));
        String address = cursor.getString(cursor.getColumnIndex("address"));
        String locknumber = cursor.getString(cursor.getColumnIndex("locknumber"));
        String photopath = cursor.getString(cursor.getColumnIndex("photopath"));


        String longitude = cursor.getString(cursor.getColumnIndex("longitude"));//经度
        String latitude = cursor.getString(cursor.getColumnIndex("latitude"));//纬度


        Intent queryIntnet = new Intent(ActivitySon.this, DisplayActivity.class);

        queryIntnet.putExtra("username", username);
        queryIntnet.putExtra("usernumber", usernumber);
        queryIntnet.putExtra("address", address);
        queryIntnet.putExtra("locknumber", locknumber);
        queryIntnet.putExtra("photopath", photopath);


        //传递路径
        queryIntnet.putExtra("longitude", longitude);
        queryIntnet.putExtra("latitude", latitude);

        startActivity(queryIntnet);


    }

    //拍照
    private void takePhoto() throws IOException {
        //向camera发出捕获照片的意图
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //确保系统有Camera 来接收这个意图
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                //创建文件夹
                photoFile = creatImageFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
            //继续只有当 photoFile 文件被成功创建
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));//传递路径
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    //创建图片目录
    String mCurrentPhotoPath;

    private File creatImageFile() throws IOException {
        //以日期时间戳做位新照片的文件名
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,/*prefix 前缀*/
                ".jpg",      /*suffix 后缀*/
                storageDir   /*directory 目录*/
        );
        mCurrentPhotoPath = image.getAbsolutePath();

        return image;
    }

    //检查GPS是否开启，进入该界面打开GPS
    private void checkOpenGPS(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return;//在返回类型为void的方法中用于结束该方法；
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("提示");
        dialog.setMessage("请前往打开GPS");
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);//调用系统GPS开关  /*ACTION_LOCALE_SETTINGS：调用系统语言设置接口*/
                startActivity(intent);
                dialogInterface.dismiss();
            }
        });
        dialog.show();
    }

    private void checkWifi(){
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()){
            Log.d("zgl", String.valueOf(wifiManager.isWifiEnabled()));
            return;
        }

        AlertDialog.Builder wifidialob = new AlertDialog.Builder(this);
        wifidialob.setTitle("提示");
        wifidialob.setMessage("请前往打开WIFI");
        wifidialob.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent wifiIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                startActivity(wifiIntent);
                dialogInterface.dismiss();

            }
        });
        wifidialob.show();
    }

    private void checkGPRS(){
        NetworkStatsManager networkStatsManager = (NetworkStatsManager) getSystemService(Context.NETWORK_STATS_SERVICE);

    }

}
