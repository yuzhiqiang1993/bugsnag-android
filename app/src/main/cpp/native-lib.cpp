#include <jni.h>
#include <stdexcept>
#include <android/log.h>

#define LOG_TAG "NativeLib"
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

extern "C" {

// 模拟 C++ 层异常：标准异常
JNIEXPORT void JNICALL
Java_com_yzq_app_MainActivity_crashFromCXX(JNIEnv *env, jobject thiz) {
//    try {
        // 抛出 C++ 异常
        throw std::runtime_error("Simulated C++ Exception");
//    } catch (const std::exception &e) {
//        // 捕获 C++ 异常，记录日志
//        LOGE("Caught C++ exception: %s", e.what());
//
//        // 转换为 Java RuntimeException 并抛出到 Java 层
//        jclass exceptionClass = env->FindClass("java/lang/RuntimeException");
//        if (exceptionClass != nullptr) {
//            env->ThrowNew(exceptionClass, e.what());
//        }
//    }
}

// 模拟 C++ 层异常：内存访问异常
JNIEXPORT void JNICALL
Java_com_yzq_app_MainActivity_causeMemoryAccessViolation(JNIEnv *env, jobject thiz) {
    // 引发空指针访问异常
    volatile int *ptr = nullptr;
    *ptr = 42; // 此操作将导致崩溃
}

}