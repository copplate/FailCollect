package com.shangeyun.parttime;

import android.app.Application;

import com.github.gzuliyujiang.oaid.DeviceIdentifier;

public class JulyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DeviceIdentifier.register(this);
    }
}
