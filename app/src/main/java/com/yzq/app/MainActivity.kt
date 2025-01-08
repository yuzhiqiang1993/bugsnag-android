package com.yzq.app

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.bugsnag.android.Bugsnag

class MainActivity : AppCompatActivity() {


    companion object {
        init {
            System.loadLibrary("native-lib");
        }
    }

    // 声明本地方法
    external fun crashFromCXX()
    external fun causeMemoryAccessViolation()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<AppCompatButton>(R.id.btn_java).setOnClickListener {
            Bugsnag.notify(Exception("Java Exception"))

        }

        findViewById<AppCompatButton>(R.id.btn_native).setOnClickListener {
            crashFromCXX()
        }

        findViewById<AppCompatButton>(R.id.btn_anr).setOnClickListener {
            //模拟ANR
            SystemClock.sleep(6000) // 模拟 20 秒的延迟，触发 ANR
        }
    }


}