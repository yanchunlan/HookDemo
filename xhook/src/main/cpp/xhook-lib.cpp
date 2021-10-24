#include <jni.h>
#include <string>

#include <string>
#include <stdio.h>
#include <unistd.h>
#include <pthread.h>

#include "xhook/xhook.h"
#include <android/log.h>

typedef void *hook_func;
static int my_system_log_print(int prio, const char* tag, const char* fmt, ...)
{
    va_list ap;
    char buf[1024];
    int r;

    snprintf(buf, sizeof(buf), "[%s] %s", (NULL == tag ? "" : tag), (NULL == fmt ? "" : fmt));

    va_start(ap, fmt);
    r = __android_log_vprint(prio, "xhook_system", buf, ap);
    va_end(ap);
    return r;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_xhook_NativeHandler_hookLog(JNIEnv *env, jobject thiz) {

    xhook_register("^/system/.*\\.so$",  "__android_log_print", (hook_func) &my_system_log_print,  NULL);
    xhook_register("^/vendor/.*\\.so$",  "__android_log_print", (hook_func) &my_system_log_print,  NULL);
    //just for testing
    xhook_ignore(".*/liblog\\.so$", "__android_log_print"); //ignore __android_log_print in liblog.so
    xhook_ignore(".*/libjavacore\\.so$", NULL); //ignore all hooks in libjavacore.so
    xhook_refresh(1);
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_xhook_NativeHandler_refresh(JNIEnv *env, jobject thiz, jboolean async) {
    return -1;
}


extern "C"
JNIEXPORT void JNICALL
Java_com_example_xhook_NativeHandler_clear(JNIEnv *env, jobject thiz) {

}


extern "C"
JNIEXPORT void JNICALL
Java_com_example_xhook_NativeHandler_enableDebug(JNIEnv *env, jobject thiz, jboolean flag) {

}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_xhook_NativeHandler_enableSigSegvProtection(JNIEnv *env, jobject thiz, jboolean flag) {

}
