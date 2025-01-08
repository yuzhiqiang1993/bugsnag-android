package com.yzq.app

import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

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
//            Bugsnag.notify(Exception("Java Exception"))
            throw Exception("Java Exception")
        }

        findViewById<AppCompatButton>(R.id.btn_native).setOnClickListener {
            crashFromCXX()
        }

        findViewById<AppCompatButton>(R.id.btn_anr).setOnClickListener {
            //模拟ANR
            Looper.getMainLooper().run {
                Thread.sleep(20 * 1000) // 主线程阻塞
            }
        }
    }


}