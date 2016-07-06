package com.example.perk.guidetest0702;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huiwu.model.http.ConnectionUtil;
import com.huiwu.model.http.StringConnectionCallBack;
import com.lzy.okhttputils.request.BaseRequest;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog dialog;
    private Gson gson;

    private SharedPreferences.Editor editor;
    private SharedPreferences check;

    private EditText mUserName;
    private EditText mUserPassword;
    private Button mLogin;
    private CheckBox mCheckRecordKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        editor =getSharedPreferences("userinfo",MODE_PRIVATE).edit();
        check = getSharedPreferences("userinfo",MODE_PRIVATE);

        if(check.getString("username","null") != "null"  && check.getString("password","null") != "null"){
           String name  = check.getString("username","空");
           String password =  check.getString("password","空");
            startActivity(new Intent( MainActivity.this,ActivitySon.class));
            finish();
            return;
        }
        setContentView(R.layout.activity_main);
        dialog = new ProgressDialog(MainActivity.this,ProgressDialog.STYLE_SPINNER);
        gson = new Gson();
        initViews();

    }

    private void initViews(){
        mUserName = (EditText) findViewById(R.id.edit_name);
        mUserPassword = (EditText) findViewById(R.id.edit_key);
        mLogin = (Button) findViewById(R.id.but_login);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String username = mUserName.getText().toString().trim();
                    String password = mUserPassword.getText().toString().trim();
                if(username.isEmpty()|| password.isEmpty()){
                    Toast.makeText(MainActivity.this,"请填写完整登陆信息",Toast.LENGTH_SHORT).show();
                }else {
                    login(username,password);
                }

            }
        });

        mCheckRecordKey = (CheckBox) findViewById(R.id.cb_record_key);
        mCheckRecordKey.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean choose) {
                if(choose){
                    if(mUserName.getText().toString().isEmpty() || mUserPassword.getText().toString().isEmpty()){
                        return;
                    }else {
                        editor.putString("username",mUserName.getText().toString().trim());
                        editor.putString("password",mUserPassword.getText().toString().trim());
                        editor.commit();
                    }
                }else {
                    return;
                }
            }
        });
    }

    private void login(final String username,final String password){
        HashMap<String,String> userinfo = new HashMap<>();
        userinfo.put("username",username);
        userinfo.put("password",password);
        userinfo.put("Language", true ? "zh-CN" : "en-us");

        ConnectionUtil.postParams("http://www.yunrfid.com" + "/CoreSYS.SYS/LGKeyLogin.index.ajax", userinfo, new StringConnectionCallBack() {
            @Override
            public void sendStart(BaseRequest baseRequest) {
                dialog.setMessage("提交信息");

                dialog.show();
            }

            @Override
            public void sendFinish(boolean b, @Nullable String s, Call call, @Nullable Response response, @Nullable Exception e) {
                dialog.dismiss();
            }

            @Override
            public void onParse(String s, Response response) {
                Log.d("zgl","s = " + s);

                JSONModel.ReturnObject returnObject = gson.fromJson(s,JSONModel.ReturnObject.class);
                Log.d("zgl1","returnObject = " + returnObject.getsMsg());

                if(!returnObject.isbOK()){
                    Toast.makeText(MainActivity.this,returnObject.getsMsg(),Toast.LENGTH_LONG).show();
                    return;
                }else{
                    Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this,ActivitySon.class));
                    finish();
                }
            }

            @Override
            public void onParseFailed(@Nullable Response response) {

            }

            @Override
            public void onLost() {

            }
        });
    }

}
