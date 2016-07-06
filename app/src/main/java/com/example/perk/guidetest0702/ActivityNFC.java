package com.example.perk.guidetest0702;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.util.Locale;

public class ActivityNFC extends AppCompatActivity {


    private TextView mInputText;

    private String mUserName;

    public NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_nfc);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        mFilters = new IntentFilter[]{ndef};
        mTechLists = new String[][]{new String[]{android.nfc.tech.NfcV.class.getName()}, new String[]{android.nfc.tech.NfcA.class.getName()}};

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mNfcAdapter != null) {
            if (mNfcAdapter.isEnabled()) {
                mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters, mTechLists);
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNfcAdapter != null && mNfcAdapter.isEnabled()) {
            mNfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        mUserName = getIntent().getStringExtra("name");

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            byte[] bytes = tag.getId();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(String.format("%02X",bytes[i]>0?bytes[i]:bytes[i]+256));
                /*
                %02X：一种输出格式：X:表示16进制、02：表示不足两位前面补0输出、%：表示打印
                * */
            }

            Log.d("TAG",sb.toString());
            //创建NdefMessage对象和ndefRecord对象
            NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]{createTextRecord(mUserName)});
            if(writeTag(ndefMessage,tag)){
                setResult(RESULT_OK,new Intent().putExtra("id",sb.toString()));
                finish();
            }else {
                Toast.makeText(ActivityNFC.this,"写入失败，重新写入",Toast.LENGTH_SHORT).show();
            }
    }

    boolean writeTag(NdefMessage message, Tag tag) {
        int size = message.toByteArray().length;

        try {
            //获取Ndef对象
            Ndef ndef = Ndef.get(tag);
            if (ndef != null) {
                //允许对标签进行IO操作
                ndef.connect();

                if (!ndef.isWritable()) {
                    Toast.makeText(this, "NFC Tag是只读的！", Toast.LENGTH_LONG)
                            .show();
                    return false;

                }
                if (ndef.getMaxSize() < size) {
                    Toast.makeText(this, "NFC Tag的空间不足！", Toast.LENGTH_LONG)
                            .show();
                    return false;
                }

                //向标签写入数据
                ndef.writeNdefMessage(message);
                Toast.makeText(this, "已成功写入数据！", Toast.LENGTH_LONG).show();
                return true;

            } else {
                //获取可以格式化和向标签写入数据NdefFormatable对象
                NdefFormatable format = NdefFormatable.get(tag);
                //向非NDEF格式或未格式化的标签写入NDEF格式数据
                if (format != null) {
                    try {
                        //允许对标签进行IO操作
                        format.connect();
                        format.format(message);
                        Toast.makeText(this, "已成功写入数据！", Toast.LENGTH_LONG)
                                .show();
                        return true;

                    } catch (Exception e) {
                        Toast.makeText(this, "写入NDEF格式数据失败！", Toast.LENGTH_LONG)
                                .show();
                        return false;
                    }
                } else {
                    Toast.makeText(this, "NFC标签不支持NDEF格式！", Toast.LENGTH_LONG)
                            .show();
                    return false;
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }

    }

    public NdefRecord createTextRecord(String text) {
        //生成语言编码的字节数组，中文编码
        byte[] langBytes = Locale.CHINA.getLanguage().getBytes(
                Charset.forName("US-ASCII"));
        //将要写入的文本以UTF_8格式进行编码
        Charset utfEncoding = Charset.forName("UTF-8");
        //由于已经确定文本的格式编码为UTF_8，所以直接将payload的第1个字节的第7位设为0
        byte[] textBytes = text.getBytes(utfEncoding);
        int utfBit = 0;
        //定义和初始化状态字节
        char status = (char) (utfBit + langBytes.length);
        //创建存储payload的字节数组
        byte[] data = new byte[1 + langBytes.length + textBytes.length];
        //设置状态字节
        data[0] = (byte) status;
        //设置语言编码
        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
        //设置实际要写入的文本
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length,
                textBytes.length);
        //根据前面设置的payload创建NdefRecord对象
        NdefRecord record = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
                NdefRecord.RTD_TEXT, new byte[0], data);
        return record;
    }
}
