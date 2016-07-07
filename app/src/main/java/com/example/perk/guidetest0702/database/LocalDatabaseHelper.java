package com.example.perk.guidetest0702.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by perkzhang on 2016/7/3.
 */
public class LocalDatabaseHelper extends SQLiteOpenHelper {


    //创表语句
    public static final String CREATE_INFO = "create table Info ("
            + "_id integer primary key autoincrement, "
            + "username text, "
            + "usernumber text, "
            + "address text, "
            + "locknumber text, "
            + "longitude text, "
            + "latitude text, "
            + "photopath text)";
    private Context mContext;

    //声明上下文
    public LocalDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_INFO);
        Toast.makeText(mContext,"建表成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
