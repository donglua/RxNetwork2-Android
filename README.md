# RxNetwork2-Android
A simple util and example of how to track connectivity changes in Android applications.


### How to use
In Android Manifest add 
```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```

Call to receive stream
```java
RxNetwork.stream(context)
```
or

```
RxNetwork.flow(context)
```


### Download

* RxJava 1 (https://github.com/Laimiux/rxnetwork-android)

```java
compile 'com.laimiux.rxnetwork:rxnetwork:0.0.4'
```

* RxJava 2 (https://github.com/donglua/RxNetwork2-Android)

```
compile 'cn.jingzhuan.lib:rxnetwork:1.0.0'



