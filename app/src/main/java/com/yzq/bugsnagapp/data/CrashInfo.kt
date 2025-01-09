package com.yzq.bugsnagapp.data

data class CrashInfo(
    var startTime: String? = null, // App启动时间
    var crashTime: String? = null, // 崩溃时间
    var appBundleId: String? = null, // 包名
    var appVersion: String? = null, // 版本名
    var appVersionCode: Int? = null, // 版本号
    var foreground: Boolean? = null, // 是否前台
    var deviceType: String? = null, // 设备机型
    var deviceBrand: String? = null, // 设备品牌
    var osVersion: String? = null, // Android的系统版本
    var osName: String? = null, // OS名
    var cpuArch: String? = null, // CPU架构
    var crashThread: String? = null, // 异常线程
    var crashReason: String? = null, // 异常原因
    var crashType: String? = "Native", // 异常类型，Native还是JS
    var rooted: Boolean = false, // 是否root
    var deviceId: String? = null, // 设备ID
    var sessionid: String? = null // x23的sessionid
)