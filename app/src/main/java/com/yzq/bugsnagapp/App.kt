package com.yzq.bugsnagapp

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


        val configuration = Configuration("yzq").apply {
            delivery = CustomerDelivery()
            logger = BugsnagLogger()
            autoDetectErrors = true
            autoTrackSessions = false//不跟踪会话信息
            enabledErrorTypes.anrs = true//捕获ANR
            enabledErrorTypes.ndkCrashes = true//捕获Native异常
            enabledErrorTypes.unhandledExceptions = true//捕获未捕获异常
        }


        Bugsnag.start(this, configuration)
        Bugsnag.leaveBreadcrumb("App Loaded")

    }

}