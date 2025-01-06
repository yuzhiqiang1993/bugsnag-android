package com.yzq.bugsnag_app

import android.app.Application
import com.bugsnag.android.Bugsnag
import com.bugsnag.android.Configuration

class App : Application() {

    override fun onCreate() {
        super.onCreate()

//        AppManager.init(this)
//        Logger.addPrinter(
//            ConsoleLogPrinter.getInstance(
//                ConsoleLogConfig.Builder().tag("Bugsnag").build()
//            )
//        )


        val configuration = Configuration("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx").apply {
            delivery = CustomerDelivery()
        }

        Bugsnag.start(this, configuration)


    }

}