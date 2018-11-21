package com.cxmax.webprogress.lib.webview.jsbridge.jsparams;

import com.tap4fun.hox.bussiness.webview.jsbridge.entity.JsCallback;

import java.util.Set;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 2018/11/20.
 */
public interface JsMap extends JsObject{
    boolean isEmpty();

    boolean hasKey(String name);

    boolean isNull(String name);

    Object get(String name);

    boolean getBoolean(String name);

    double getDouble(String name);

    int getInt(String name);

    long getLong(String name);

    String getString(String name);

    JsCallback getCallback(String name);

    JsMap getJsMap(String name);

    JsArray getJsArray(String name);

    Set<String> keySet();
}
