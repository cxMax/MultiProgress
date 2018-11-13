package com.cxmax.webprogress.jsbridge.pojo;

/**
 * @describe : 按照 {@link JsMethod} 不同的业务进行对应的JsModule注册管理， 进行分类管理，
 *             一个 {@link JsModule} 包含多个 {@link JsMethod}
 *             一个项目可以包含多个不同业务线的 {@link JsModule}
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 2018/11/13.
 */
public abstract class JsModule {

    /**
     * 需要每个子类进行定义
     * @return
     */
    public abstract String getModuleName();
}
