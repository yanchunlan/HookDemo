# hookDemo
熟系hook对于性能优化至关重要，目前市面上主流native性能监控基本上都离不开hook，所以本demo是对所有hook的一个总结梳理，主要探究的有以下3点：

#### 1. 学会使用hook框架
    xHook (not root) https://github.com/iqiyi/xHook
    bHook (not root) https://github.com/bytedance/bhook
    xposed (root) 
    dexposed (2.0-5.0) https://github.com/alibaba/dexposed
    epic(5.0-11) https://github.com/tiann/epic
    frida(root,python + javascript) https://github.com/frida/frida
    
#### 2. 研究hook框架的原理
    got hook
    inline hook
    
#### 3. 制作相对应的hook支持的功能
    malloc/free监控
    thread监控
    bitmap监控
    socket监控
    io监控
    
[hook总结文档](https://github.com/yanchunlan/SourceCodeSummary/blob/master/%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96/%E6%9E%81%E8%87%B4%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E6%80%BB%E7%BB%93/17_nativehook%E6%80%BB%E7%BB%93.txt)

#### 其他

    jvmti https://github.com/AndroidAdvanceWithGeektime/JVMTI_Sample/blob/master/jvmtilib/src/androidTest/java/com/dodola/jvmtilib/ExampleInstrumentedTest.java
    
