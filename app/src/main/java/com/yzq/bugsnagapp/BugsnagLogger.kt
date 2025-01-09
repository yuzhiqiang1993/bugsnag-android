package com.yzq.bugsnagapp

import com.bugsnag.android.Logger

class BugsnagLogger : Logger {

    companion object {
        const val TAG = "BugsnagLogger"
    }

    override fun i(msg: String) {
        com.yzq.logger.Logger.it(TAG, msg)
    }

    override fun e(msg: String) {
        com.yzq.logger.Logger.et(TAG, msg)
    }

}