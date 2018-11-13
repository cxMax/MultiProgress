package com.cxmax.webprogress.jsbridge.pojo;

import android.support.annotation.NonNull;

import com.cxmax.webprogress.jsbridge.annotation.JsInterface;

import java.lang.reflect.Method;
import java.util.Locale;

/**
 * @describe : the method which annotated by {@link JsInterface}
 *            todo list ： 暂时不支持
 *            1. 支持有返回参数的函数
 *            2. 静态函数
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 2018/11/13.
 */
public class JsMethod {

    /**
     * 对应的java函数
     */
    private Method method;
    /**
     * 所属的 {@link JsModule}
     */
    private JsModule module;
    /**
     * java函数名称
     */
    private String methodName;
    /**
     * 和JavaScript约定的
     */
    private String protocol;


    @NonNull public static JsMethod create(JsModule module, Method method, String methodName, String protocol) {
        JsMethod jsMethod = new JsMethod();
        jsMethod.setMethod(method);
        jsMethod.setMethodName(methodName);
        jsMethod.setModule(module);
        jsMethod.setProtocol(protocol);
        return jsMethod;
    }

    /**
     * 注入的JS代码
     *
     * @return
     */
    public String getInjectJs() {
        StringBuilder builder = new StringBuilder();
        builder.append(getMethodName() + ":function(){");
        builder.append("if(!" + getCallback() + ")" + getCallback() + "={};");
        builder.append(String.format(Locale.getDefault(), "return _method(%s,arguments,%d,'%s','%s')",
                getCallback(), 0,
                getModule().getModuleName(), getMethodName()));
        builder.append("}");
        builder.append(",");
        return builder.toString();
    }

    public String getCallback() {
        return String.format("%s.%s.%sCallback", getProtocol(),
                getModule().getModuleName(), getMethodName());
    }

    /* getter and setter*/

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public JsModule getModule() {
        return module;
    }

    public void setModule(JsModule module) {
        this.module = module;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
