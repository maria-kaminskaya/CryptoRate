package com.example.cryptorate

import android.app.Application
import android.os.SystemClock

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        SystemClock.sleep(java.util.concurrent.TimeUnit.SECONDS.toMillis(3));
    }
}