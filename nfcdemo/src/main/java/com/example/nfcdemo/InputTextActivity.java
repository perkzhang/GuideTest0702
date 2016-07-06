package com.example.nfcdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class InputTextActivity extends AppCompatActivity {

    private EditText mTextTag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_text);
        mTextTag = (EditText) findViewById(R.id.edittext_text_tag);
        mTextTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("text", mTextTag.getText().toString());
                setResult(1, intent);
                finish();
            }
        });
    }



}
