package com.yzq.bugsnagapp.data


data class OriginalEventData(
    val app: App?, // 应用相关信息
    val breadcrumbs: List<Breadcrumb?>?, // 用户操作的面包屑记录，用于重现问题
    val context: String?, // 当前上下文，一般是当前Activity或功能模块的名称，例如MainActivity
    val device: Device?, // 设备相关信息
    val exceptions: List<Exception?>?, // 异常信息列表
    val featureFlags: List<Any?>?, // 特性标志，记录了当前应用启用了哪些实验性功能
    val metaData: MetaData?, // 元数据，自定义附加信息
    val projectPackages: List<String?>?, // 项目包名，用于识别是否为应用内部代码
    val severity: String?, // 严重性，可能值包括"info", "warning", "error"
    val severityReason: SeverityReason?, // 严重性原因，描述为什么被标记为特定的严重性
    val threads: List<Thread?>?, // 线程信息
    val unhandled: Boolean?, // 是否是未处理的异常
) {

    data class App(
        val binaryArch: String?, // 应用的二进制架构，例如arm64
        val buildUUID: String?, // 构建的唯一标识符
        val duration: Int?, // 应用运行的总时长（毫秒）
        val durationInForeground: Int?, // 应用在前台运行的时长（毫秒）
        val id: String?, // 应用的包名，例如com.yzq.bugsnagapp
        val inForeground: Boolean?, // 应用当前是否在前台
        val isLaunching: Boolean?, // 是否处于启动阶段
        val releaseStage: String?, // 发布阶段，例如development, staging, production
        val type: String?, // 平台类型，例如android
        val version: String?, // 应用版本号，例如1.0
        val versionCode: Int? // 应用版本代码，例如1
    )


    data class Breadcrumb(
        val metaData: MetaData?, // 元数据，记录操作的附加信息
        val name: String?, // 面包屑名称，例如"Bugsnag loaded"
        val timestamp: String?, // 时间戳，记录操作发生的时间
        val type: String? // 类型，例如"state"表示状态变化
    ) {

        data class MetaData(
            val action: String?, // 动作，例如android.intent.action.MAIN
            val categories: String?, // 分类，例如android.intent.category.LAUNCHER
            val flags: String?, // 标志位，例如0x10000000
            val hasBundle: Boolean?, // 是否包含Bundle数据
            val hasData: Boolean?, // 是否包含数据
            val hasExtras: Boolean?, // 是否包含额外信息
            val previous: String? // 前一个操作，例如onCreate()
        )
    }


    data class Device(
        val cpuAbi: List<String?>?, // CPU架构信息，例如arm64-v8a
        val freeDisk: Long?, // 剩余磁盘空间（字节）
        val freeMemory: Long?, // 剩余内存（字节）
        val id: String?, // 设备唯一标识
        val jailbroken: Boolean?, // 是否越狱或root
        val locale: String?, // 区域设置，例如zh_CN
        val manufacturer: String?, // 制造商，例如realme
        val model: String?, // 设备型号，例如RMX3350
        val orientation: String?, // 屏幕方向，例如portrait（竖屏）
        val osName: String?, // 操作系统名称，例如android
        val osVersion: String?, // 操作系统版本，例如13
        val runtimeVersions: RuntimeVersions?, // 运行时版本信息
        val time: String?, // 当前时间戳
        val totalMemory: Long? // 总内存（字节）
    ) {

        data class RuntimeVersions(
            val androidApiLevel: String?, // Android API等级，例如33
            val osBuild: String? // 操作系统构建版本号，例如RMX3350_13.1.0.500(CN01)
        )
    }


    data class Exception(
        val errorClass: String?, // 异常类名，例如java.lang.UnsatisfiedLinkError
        val message: String?, // 异常消息内容
        val stacktrace: List<Stacktrace?>?, // 堆栈跟踪信息
        val type: String? // 异常类型，例如android
    ) {

        data class Stacktrace(
            val file: String?, // 文件名，例如MainActivity.kt
            val inProject: Boolean?, // 是否属于项目代码
            val lineNumber: Int?, // 行号
            val method: String? // 方法名，例如com.yzq.bugsnagapp.MainActivity.crashFromCXX
        )
    }


    data class MetaData(
        val app: App?, // 应用相关元数据
        val device: Device? // 设备相关元数据
    ) {

        data class App(
            val activeScreen: String?, // 当前活跃屏幕
            val freeMemory: Int?, // 剩余内存
            val installerPackage: String?, // 安装包来源
            val lowMemory: Boolean?, // 是否处于低内存状态
            val memoryLimit: Int?, // 内存限制
            val memoryTrimLevel: String?, // 内存修剪级别
            val memoryUsage: Int?, // 内存使用量
            val name: String?, // 应用名称
            val processImportance: String?, // 进程重要性
            val processName: String?, // 进程名称
            val totalMemory: Int? // 总内存
        )


        data class Device(
            val batteryLevel: Double?, // 电量水平，例如1.0表示100%
            val brand: String?, // 品牌，例如realme
            val charging: Boolean?, // 是否正在充电
            val dpi: Int?, // 屏幕DPI
            val emulator: Boolean?, // 是否为模拟器
            val locationStatus: String?, // 位置信息状态，例如allowed
            val networkAccess: String?, // 网络访问状态，例如wifi
            val screenDensity: Double?, // 屏幕密度
            val screenResolution: String? // 屏幕分辨率，例如2280x1080
        )
    }


    data class SeverityReason(
        val type: String?, // 严重性原因类型
        val unhandledOverridden: Boolean? // 是否覆盖了未处理标志
    )


    data class Thread(
        val errorReportingThread: Boolean?, // 是否为报告异常的线程
        val id: String?, // 线程ID
        val name: String?, // 线程名称，例如main
        val stacktrace: List<Stacktrace?>?, // 堆栈跟踪信息
        val state: String?, // 线程状态，例如RUNNABLE
        val type: String? // 类型，例如android
    ) {

        data class Stacktrace(
            val file: String?, // 文件名
            val inProject: Boolean?, // 是否属于项目代码
            val lineNumber: Int?, // 行号
            val method: String? // 方法名
        )
    }
}