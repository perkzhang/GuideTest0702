package com.example.perk.guidetest0702;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.perk.guidetest0702.adapter.DisplayAdapter;
import com.example.perk.guidetest0702.database.LocalDatabaseHelper;
import com.example.perk.guidetest0702.mapview.MapViewActivity;
import com.example.perk.guidetest0702.valueclass.Info;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    private TextView mName;
    private TextView mNumber;
    private TextView mLocation;
    private TextView mLockNumber;
    private TextView mPhotoPath;
    private ImageView mPotoDisPlay;
    private ImageView mImageBack;


    private ListView mDisplay;
    private SwipeRefreshLayout mSwipLayout;

    LocalDatabaseHelper helper;
    DisplayAdapter adapter;
    ArrayList<Info> dataList = new ArrayList<Info>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display);
        helper = new LocalDatabaseHelper(DisplayActivity.this, "LockInfo.db", null, 2);
        initViwes();
        query();
        listViewItemListener();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initViwes() {

        mImageBack = (ImageView) findViewById(R.id.image_back);
        mImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mDisplay = (ListView) findViewById(R.id.lv_display);

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void query() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("info", null, null, null, null, null, null);
        //android中数据库处理使用cursor时，游标不是放在为0的下标，而是放在为-1的下标处开始的。
        //也就是说返回给cursor查询结果时，不能够马上从cursor中提取值。
        Log.d("zgl1", "count = " + cursor.getCount());

        if (cursor.moveToFirst()) {
            do {
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String usernumber = cursor.getString(cursor.getColumnIndex("usernumber"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String locknumber = cursor.getString(cursor.getColumnIndex("locknumber"));
                String photopath = cursor.getString(cursor.getColumnIndex("photopath"));

                String longitude = cursor.getString(cursor.getColumnIndex("longitude"));//经度
                String latitude = cursor.getString(cursor.getColumnIndex("latitude"));//纬度

                Info info = new Info();
                info.setmName(username);
                info.setmNumber(usernumber);
                info.setmLocation(address);
                info.setmLockNumber(locknumber);
                info.setmPhotoPath(photopath);

                info.setmLongitude(longitude);
                info.setmLatitude(latitude);
                dataList.add(info);
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter = new DisplayAdapter(DisplayActivity.this, dataList);
        mDisplay.setAdapter(adapter);
    }

    private void listViewItemListener() {
        mDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mapviewintent = new Intent(DisplayActivity.this, MapViewActivity.class);
                mapviewintent.putExtra("longitude",dataList.get(i).getmLongitude());
                mapviewintent.putExtra("latitude",dataList.get(i).getmLatitude());
                startActivity(mapviewintent);

            }
        });
    }
}
