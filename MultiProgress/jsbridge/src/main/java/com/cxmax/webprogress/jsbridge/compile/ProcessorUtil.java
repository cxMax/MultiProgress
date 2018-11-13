package com.cxmax.webprogress.jsbridge.compile;

import android.support.annotation.NonNull;
import android.text.TextUtils;


import com.cxmax.webprogress.jsbridge.annotation.JsInterface;
import com.cxmax.webprogress.jsbridge.pojo.JsMethod;
import com.cxmax.webprogress.jsbridge.pojo.JsModule;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import com.cxmax.webprogress.jsbridge.pojo.JsRunMethodFactory.*;


/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 2018/11/13.
 */
public class ProcessorUtil {

    /**
     * 获取所有JsModule下的注解JsMethod
     * @param module
     * @param protocol
     * @return
     */
    public static HashMap<String, JsMethod> parseAllJsMethods(JsModule module, String protocol) {
        HashMap<String, JsMethod> methods = new HashMap<>();
        Class clz = module.getClass();
        Method[] declaredMethods = clz.getDeclaredMethods();
        if (declaredMethods == null || declaredMethods.length == 0) {
            return methods;
        }
        for (Method method : declaredMethods) {
            String name = method.getName();
            int modifiers = method.getModifiers();
            // 静态函数， 抽象函数直接略过
            if (TextUtils.isEmpty(name) || Modifier.isAbstract(modifiers) || Modifier.isStatic(modifiers)) {
                continue;
            }
            // 是否是标记让javascript调用的native函数
            JsInterface annotation = method.getAnnotation(JsInterface.class);
            if (annotation == null) {
                continue;
            }
            // todo 暂时定义void函数， 无返回参数
            JsMethod jsMethod = JsMethod.create(module, method, name, protocol);
            methods.put(name, jsMethod);
        }

        return methods;
    }

    public static String getUtilMethods(@NonNull String protocol) {
        StringBuilder builder = new StringBuilder();
        String loadReadyMethod = String.format("on%sReady", protocol);
        AbsJsRunMethod[] methods = new AbsJsRunMethod[]{new GetType(), new ParseFunction(),
                new OnJsBridgeReady(loadReadyMethod), new CallJava(),
                new Printer(), new CallMethod()
        };
        for (AbsJsRunMethod method : methods) {
            builder.append(method.getMethod());
        }
        return builder.toString();
    }

}
