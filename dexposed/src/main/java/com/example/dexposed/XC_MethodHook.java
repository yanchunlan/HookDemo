package com.example.dexposed;

public abstract class XC_MethodHook extends XCallback {
    
    /**
     * Called before the invocation of the method.
     * <p>Can use {@link MethodHookParam#setResult(Object)} and {@link MethodHookParam#setThrowable(Throwable)}
     * to prevent the original method from being called.
     */
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {}
    
    /**
     * Called after the invocation of the method.
     * <p>Can use {@link MethodHookParam#setResult(Object)} and {@link MethodHookParam#setThrowable(Throwable)}
     * to modify the return value of the original method.
     */
    protected void afterHookedMethod(MethodHookParam param) throws Throwable  {}
}