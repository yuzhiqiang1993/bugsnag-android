package com.example.bugsnag.android

import android.app.Application
import com.bugsnag.android.Bugsnag
import com.bugsnag.android.Configuration
import com.bugsnag.android.okhttp.BugsnagOkHttpPlugin
import com.yzq.logger.Logger
import com.yzq.logger.console.ConsoleLogConfig
import com.yzq.logger.console.ConsoleLogPrinter
import okhttp3.OkHttpClient
import java.io.File

class ExampleApplication : Application() {

    private val bugsnagOkHttpPlugin = BugsnagOkHttpPlugin()
    val httpClient = OkHttpClient.Builder().eventListener(bugsnagOkHttpPlugin).build()

    companion object {
        init {
//            if you support API <= 17 you should uncomment this to load the bugsnag library
//            before any libraries that link to it
//            https://docs.bugsnag.com/platforms/android/#initialize-the-bugsnag-client
//
//            System.loadLibrary("bugsnag-ndk")
//            System.loadLibrary("bugsnag-plugin-android-anr")

            System.loadLibrary("entrypoint")
        }
    }

    private external fun performNativeBugsnagSetup()

    override fun onCreate() {
        super.onCreate()

        Logger.addPrinter(
            ConsoleLogPrinter.getInstance(
                ConsoleLogConfig.Builder().enable(true).build()
            )
        )

        val config = Configuration.load(this).apply {
            addPlugin(bugsnagOkHttpPlugin)
            delivery = CustomerDelivery()
            logger = BugsnagLogger()
            autoDetectErrors = true
            autoTrackSessions = false//不跟踪会话信息
            enabledErrorTypes.anrs = true//捕获ANR
            enabledErrorTypes.ndkCrashes = true//捕获Native异常
            enabledErrorTypes.unhandledExceptions = true//捕获未捕获异常

        }
//        config.setUser("123456", "joebloggs@example.com", "Joe Bloggs")
//        config.addMetadata("user", "age", 31)


        // Configure the persistence directory when running MultiProcessActivity in a separate
        // process to ensure the two Bugsnag clients are independent
//        val processName = findCurrentProcessName()
//        if (processName.endsWith("secondaryprocess")) {
//            config.persistenceDirectory = File(filesDir, processName)
//        }

        Bugsnag.start(this, config)

        // Initialise native callbacks
        performNativeBugsnagSetup()
    }

}
