package com.yzq.bugsnag_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.bugsnag.android.Bugsnag

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<AppCompatButton>(R.id.btn_java).setOnClickListener {
            Bugsnag.notify(Exception("Java Exception"))

        }

        findViewById<AppCompatButton>(R.id.btn_native).setOnClickListener {

        }
    }


}