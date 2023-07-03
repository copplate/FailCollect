package com.shangeyun.parttime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.github.gzuliyujiang.oaid.DeviceID;
import com.github.gzuliyujiang.oaid.DeviceIdentifier;
import com.github.gzuliyujiang.oaid.IGetter;

public class MainActivityJava extends AppCompatActivity {
    private TelephonyManager telephonyManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java);
        /**
         * imei,deprecated
         * */
        /*// 获取TelephonyManager实例
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        // 检查权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // 请求权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        } else {
            // 获取IMEI
            String imei = telephonyManager.getImei();
            Log.d("fasdfasfa", imei);
        }*/
        // 获取GUID，随机生成，不会为空
        String guid = DeviceIdentifier.getGUID(this);
        Log.d("fasdfasfa", "onCreate: ------guid-----" + guid);
        // 是否支持OAID/AAID
        boolean b = DeviceID.supportedOAID(this);
        Log.d("fasdfasfa", "onCreate: ------supportedOAID-----" + b);

        // 获取OAID/AAID，同步调用
        String oaid = DeviceIdentifier.getOAID(this);
        Log.d("fasdfasfa", "onCreate: --同步调用----oaid-----" + oaid);

        // 获取OAID/AAID，异步回调
        DeviceID.getOAID(this, new IGetter() {
            @Override
            public void onOAIDGetComplete(String result) {
                // 不同厂商的OAID/AAID格式是不一样的，可进行MD5、SHA1之类的哈希运算统一
                Log.d("fasdfasfa", "onCreate: ---异步回调---oaid-----" + result);

            }

            @Override
            public void onOAIDGetError(Exception error) {
                // 获取OAID/AAID失败
            }
        });




    }







    // 处理权限请求结果
    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 获取IMEI
                String imei = telephonyManager.getImei();
                Log.d("IMEI", imei);
            } else {
                // 权限被拒绝
                Log.d("IMEI", "Permission denied");
            }
        }
    }*/
}