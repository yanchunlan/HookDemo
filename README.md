# hookDemo
熟系hook对于性能优化至关重要，目前市面上主流native性能监控基本上都离不开hook，所以本demo是对所有hook的一个总结梳理，主要探究的有以下3点：

#### 1. 学会使用hook框架

[xHook](https://github.com/iqiyi/xHook) (not root) </br>
[xposed](https://github.com/rovo89/Xposed) (root) </br>
[dexposed](https://github.com/alibaba/dexposed) (2.0-5.0) </br>
[epic](https://github.com/tiann/epic) (5.0-11) </br>
[frida](https://github.com/frida/frida) (root,python + javascript) </br>
    
#### 2. 研究hook框架的原理
    got hook
    inline hook
    
#### 3. 制作相对应的hook支持的功能
    malloc/free监控
    thread监控
    bitmap监控
    socket监控
    io监控
    
[hook总结文档](https://github.com/yanchunlan/SourceCodeSummary/blob/master/%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96/%E5%BC%80%E5%8F%91%E9%AB%98%E6%89%8B/17_nativehook%E6%80%BB%E7%BB%93.txt)

#### 其他

[jvmti](https://github.com/AndroidAdvanceWithGeektime/JVMTI_Sample/blob/master/jvmtilib/src/androidTest/java/com/dodola/jvmtilib/ExampleInstrumentedTest.java)
 
    
