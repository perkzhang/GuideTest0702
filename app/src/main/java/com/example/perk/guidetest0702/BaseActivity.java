package com.example.perk.guidetest0702;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class BaseActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkCheck networkCheck;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intentFilter = new IntentFilter();
        networkCheck = new NetworkCheck();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(networkCheck,intentFilter);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkCheck);
    }

    /**
     * 加载图片属性
     *
     * @return
     */

    long backTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (System.currentTimeMillis() - backTime > 2000) {
            backTime = System.currentTimeMillis();
            Toast.makeText(BaseActivity.this,"再按一次退出",Toast.LENGTH_SHORT).show();//用Activity.this 弹出一个标准Toast,而getApplication()弹出一个包含完显示内容的Toast
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public DisplayImageOptions getDefaultDisplayImageOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher) // 设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)  // 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)// 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)// 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  // 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY)// 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型
                //.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
                //设置图片加入缓存前，对bitmap进行设置
                //.preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                //.displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(1000))// 是否图片加载好后渐入的动画时间
                .build();
        return options;
    }

    class NetworkCheck extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo mobNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            if (wifiNetworkInfo.isConnected()){
                return;
            }
            if (mobNetworkInfo.isConnected()){
                Toast.makeText(context,"当前没有网络",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder openGRPS = new AlertDialog.Builder(context);
                openGRPS.setTitle("提示");
                openGRPS.setMessage("请开启WIFI");
                openGRPS.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent wifiIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        startActivity(wifiIntent);
                        dialogInterface.dismiss();
                    }
                });
                openGRPS.show();
            }
            if (!mobNetworkInfo.isConnected()){
                Toast.makeText(context,"当前没有移动网络",Toast.LENGTH_SHORT).show();

                AlertDialog.Builder openGRPS = new AlertDialog.Builder(context);
                openGRPS.setTitle("提示");
                openGRPS.setMessage("请开启移动网络");
                openGRPS.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent gprsIntent = new Intent(Settings.ACTION_SETTINGS);
                        startActivity(gprsIntent);
                        dialogInterface.dismiss();
                    }
                });
                openGRPS.show();
            }else if (!wifiNetworkInfo.isConnected()){
                Toast.makeText(context,"当前没有网络",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder openGRPS = new AlertDialog.Builder(context);
                openGRPS.setTitle("提示");
                openGRPS.setMessage("请开启WIFI");
                openGRPS.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent wifiIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        startActivity(wifiIntent);
                        dialogInterface.dismiss();
                    }
                });
                openGRPS.show();
            }
        }

    }
}
