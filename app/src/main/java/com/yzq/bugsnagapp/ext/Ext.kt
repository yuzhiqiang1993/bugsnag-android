package com.yzq.bugsnagapp.ext

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yzq.bugsnagapp.data.CrashInfo
import com.yzq.bugsnagapp.data.OriginalEventData
import com.yzq.bugsnagapp.utils.MoshiUtils
import java.text.SimpleDateFormat
import java.util.Date


fun OriginalEventData.toCrashInfo(): CrashInfo {
    val eventData = this
    val crashInfo = CrashInfo().apply {
        startTime = eventData.getLoadTime()
        crashTime = eventData.getCrashTime()
        appBundleId = eventData.app?.id
        appVersion = eventData.app?.version
        appVersionCode = kotlin.runCatching { eventData.app?.versionCode }.getOrNull()
        foreground = eventData.app?.inForeground
        deviceType =
            kotlin.runCatching { "${eventData.device?.manufacturer} ${eventData.device?.model}" }
                .getOrNull()
        deviceBrand = eventData.device?.manufacturer
        osVersion = eventData.device?.osVersion
        osName = eventData.device?.runtimeVersions?.osBuild
        cpuArch = eventData.getCpuArchStr()
        crashThread = eventData.crashThread()
        crashReason = eventData.getReason()
        rooted = eventData.device?.jailbroken ?: false
        originalData = MoshiUtils.toJson(eventData)

    }



    return crashInfo

}


/**
 * 获取App启动时间，这里就直接拿loadTime
 */
private fun OriginalEventData.getLoadTime() = kotlin.runCatching {
    val loadedBreadcrumbs = this.breadcrumbs?.find { it?.name == "Bugsnag loaded" }
    //把Date转成timestamp 字符串
    loadedBreadcrumbs?.timestamp
}.getOrNull()

/**
 * 获取崩溃时间
 */
private fun OriginalEventData.getCrashTime() = kotlin.runCatching {
    this.device?.time
}.getOrNull()


//把Date转成yyyy-MM-dd HH:mm:ss:SSS
private fun Date.toMillisecond() = kotlin.runCatching {
    SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(this)
}.getOrDefault(this.time.toString())


/**
 * 获取cpu架构
 */
private fun OriginalEventData.getCpuArchStr() = kotlin.runCatching {
    //数组转成字符串
    this.device?.cpuAbi?.joinToString(",")
}.getOrNull()

/**
 * 异常线程
 */
private fun OriginalEventData.crashThread() = kotlin.runCatching {
    this.threads?.get(0)?.name
}.getOrNull()


/**
 * 异常原因
 */
private fun OriginalEventData.getReason() = kotlin.runCatching {
    this.exceptions?.last()?.message
}.getOrNull()


private fun OriginalEventData.toJson(): String? {
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val adapter = moshi.adapter(OriginalEventData::class.java)

    val json = adapter.toJson(this)
    return json
}