package com.cxmax.webprogress.jsbridge;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.JsPromptResult;
import android.webkit.WebView;

import com.cxmax.webprogress.jsbridge.compile.ProcessorUtil;
import com.cxmax.webprogress.jsbridge.pojo.JsMethod;
import com.cxmax.webprogress.jsbridge.pojo.JsModule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * @describe :
 *           1. 不支持参数回调给JavaScript函数'
 *           2. 不支持静态函数
 *           3. 不支持模块使用包名， 现在仅支持字符串
 *           4. js注入， 现在是全局注入， 后续在提供js分模块注入
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 2018/11/13.
 */
public class JsBridgeImpl implements IJsBridge {

    @NonNull
    private JsBridgeConfig config;
    private final Map<JsModule, HashMap<String, JsMethod>> exposedMethods;
    private final String className;
    private final Set<String> moduleSet;
    private String preLoad;


    private static class SingletonHolder {
        private static final JsBridgeImpl INSTANCE = new JsBridgeImpl();
    }

    public JsBridgeImpl() {
        config = new JsBridgeConfig();
        exposedMethods = new HashMap<>();
        className = Integer.toHexString(hashCode());
        moduleSet = new HashSet<>();
    }

    public static JsBridgeImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public JsBridgeImpl registerDefaultModule(Class<? extends JsModule>... inputs) {
        config.registerDefaultModule(inputs);
        return this;
    }

    public JsBridgeImpl setProtocol(String protocol) {
        config.setProtocol(protocol);
        return this;
    }

    @Override
    public void init() {
        try {
            for (Class<? extends JsModule> clz : config.getModules()) {
                JsModule module = clz.newInstance();
                if (module != null && !TextUtils.isEmpty(module.getModuleName())) {
                    HashMap<String, JsMethod> methods = ProcessorUtil.parseAllJsMethods(module, config.getProtocol());
                    exposedMethods.put(module, methods);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Schedulers.single().scheduleDirect(new Runnable() {
//            @Override
//            public void run() {
                preLoad = getInjectJsString();
//            }
//        });
    }

    @Override
    public void injectJs(final @NonNull WebView webView) {
        webView.loadUrl("javascript:" + preLoad);
    }

    // todo 需要完成JavaScript调用native的逻辑
    @Override
    public boolean callJsPrompt(@NonNull String methodArgs, @NonNull JsPromptResult result) {
        return false;
    }

    @Override
    public void release() {

    }

    private String getInjectJsString() {
        StringBuilder builder = new StringBuilder();
        builder.append("var " + className + " = function () {");
        // 注入通用方法
        builder.append(ProcessorUtil.getUtilMethods(config.getProtocol()));
        // 注入默认方法
        for (JsModule module: exposedMethods.keySet()) {
            HashMap<String, JsMethod> methods = exposedMethods.get(module);
            if (methods == null || methods.keySet() == null) {
                continue;
            }
            builder.append(className + ".prototype." + module.getModuleName() + " = {");
            if (methods != null && methods.keySet() != null) {
                for (String method : methods.keySet()) {
                    JsMethod jsMethod = methods.get(method);
                    builder.append(jsMethod.getInjectJs());
                }
            }
            builder.append("};");
        }
        builder.append("};");
        builder.append("window." + config.getProtocol() + " = new " + className + "();");
        builder.append(config.getProtocol() + ".OnJsBridgeReady();");
        return builder.toString();
    }
}
