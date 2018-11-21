package com.cxmax.webprogress.lib.webview.jsbridge.entity;

import com.tap4fun.hox.libcommon.Constants;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 2018/11/20.
 */
public class JsStaticModule extends JsModule {

    @Override
    public final String getModuleName() {
        return Constants.WebView.STATIC_METHOD_NAME;
    }
}
