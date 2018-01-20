package com.cxmax.webprogress.interfaces;

import android.content.Context;

import java.util.Map;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 18-1-20.
 */

public interface Command {

    String name();

    void exec(Context context, Map params, ResultBack resultBack);

}
