package com.yzq.bugsnag_app

import android.app.Application
import com.bugsnag.android.Bugsnag
import com.bugsnag.android.Configuration
import com.yzq.application.AppManager
import com.yzq.logger.Logger
import com.yzq.logger.console.ConsoleLogConfig
import com.yzq.logger.console.ConsoleLogPrinter

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AppManager.init(this)
        Logger.addPrinter(
            ConsoleLogPrinter.getInstance(
                ConsoleLogConfig.Builder().tag("Bugsnag").build()
            )
        )


        val configuration = Configuration("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx").apply {
            delivery = CustomerDelivery()
            logger = BugsnagLogger()
        }

        Bugsnag.start(this, configuration)


    }

}