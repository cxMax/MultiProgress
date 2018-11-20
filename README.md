## SafeWebView
Android WebView使用相关集成

## webview的问题以及解决方案 :
1. 内存泄漏 , 使用多进程的方式进行处理 :
* webview多进程开发 : https://www.jianshu.com/p/b66c225c19e2
* webview多进程demo : https://github.com/xudjx/webprogress
2. webview处理js交互方
* WebView addJavascriptInterface方法
     * 在 android 4.2以下存在严重安全漏洞，
     * 而且和 JS 交换的数据仅仅局限于基本类型(int,float,double,String 等)
     * 不支持直接 JS 函数调用和回调(需要通过注入 JS 支持
* 拦截自定义协议链接实现数据交换
    * shouldOverrideUrlLoading 
* 实现 prompt,console等原生方法来数据交互
    * onJsPrompt

3. webview其他问题
* 操作cookie， 
* cache缓存
* dns劫持
* 免流量

## 相关开源项目
> webview相关
* FinestWebView-Android : https://github.com/TheFinestArtist/FinestWebView-Android
* Android-AdvancedWebView : https://github.com/delight-im/Android-AdvancedWebView

> JsBridge相关
* https://github.com/lzyzsd/JsBridge
* https://github.com/pengwei1024/JsBridge
* https://github.com/hiphonezhu/JsBridge 改造后的lzyzsd/JsBridge
* https://github.com/wendux/DSBridge-Android WebView addJavascriptInterface得封装
* https://github.com/pedant/safe-java-js-webview-bridge


> webview cache相关
* https://github.com/Carson-Ho/Webview_Cache 
* https://github.com/NEYouFan/ht-candywebcache-android
> webview dns
* https://github.com/aliyun/alicloud-android-demo/tree/master/httpdns_android_demo
* https://github.com/tencentyun/httpdns-android-sdk 
> webview 独立进程 
* https://github.com/cxMax/MultiProgress_WebView

## 相关blog
* 如何设计一个优雅健壮的Android WebView ? (上): https://juejin.im/post/5a94f9d15188257a63113a74
* 如何设计一个优雅健壮的Android WebView ? (下): https://juejin.im/post/5a94fb046fb9a0635865a2d6
* JsBridge 实现 JavaScript 和 Java 的互相调用 : https://juejin.im/entry/573534f82e958a0069b27646
* Android：手把手教你构建 全面的WebView ： https://www.jianshu.com/p/5e7075f4875f
* webview漏洞 ： https://www.jianshu.com/p/3a345d27cd42

## License
   Copyright (C) cxMax  
   Copyright (C) MultiProgress  

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
