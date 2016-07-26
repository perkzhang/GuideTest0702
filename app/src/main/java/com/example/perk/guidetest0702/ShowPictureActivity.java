package com.example.perk.guidetest0702;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ShowPictureActivity extends BaseActivity implements View.OnClickListener{

    String imageUrl;
    boolean show_picture_local;//判断是否有拍摄的图片；

    PhotoView pwPhotoView;
    TextView textDescribe;
    LinearLayout layoutShowPicture;

    PhotoViewAttacher attacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);
        initView();

        attacher = new PhotoViewAttacher(pwPhotoView);
        attacher.setOnMatrixChangeListener(new PhotoViewAttacher.OnMatrixChangedListener() {
            @Override
            public void onMatrixChanged(RectF rectF) {

            }
        });
        attacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                finish();
            }

            @Override
            public void onOutsidePhotoTap() {

            }
        });

        imageUrl = getIntent().getStringExtra("imagePath");//从ActivitySon获取图片地址
        show_picture_local = getIntent().getBooleanExtra("SHOW_PICTURE_LOCAL",true);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(ShowPictureActivity.this));//使用ImageLoader之前必须要初始化设置
        /*file:/,//,///,////...至少有一个/，ImageLoader才可以加载出来*/
        imageLoader.loadImage(
                show_picture_local ? "file:///" + imageUrl : imageUrl,
                getDefaultDisplayImageOptions(),
                new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {

                    }//图片开始加载

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        Log.d("zgl","imageUri false = "+ imageUri);
                        pwPhotoView.setImageResource(R.drawable.ic_default_picture);
                    }//图片加载失败

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        if (loadedImage !=null){
                            pwPhotoView.setImageBitmap(loadedImage);
                            Log.d("zgl","imageUri true = "+ imageUri);

                        }

                    }//图片加载完成

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {

                    }//图片加载取消
                });
    }

    private void initView(){
        pwPhotoView = (PhotoView) findViewById(R.id.pw_photo_view);
        pwPhotoView.setOnClickListener(this);
        textDescribe = (TextView) findViewById(R.id.tv_describe);
        textDescribe.setOnClickListener(this);
        layoutShowPicture = (LinearLayout) findViewById(R.id.layput_show_picture);
        layoutShowPicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pw_photo_view:
                finish();
                break;
            case R.id.tv_describe:
                finish();
                break;
            case R.id.layput_show_picture:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        attacher.cleanup();
    }
}
