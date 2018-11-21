package com.cxmax.webprogress.aidl.mainpro;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

import com.cxmax.webprogress.CommandsManager;
import com.cxmax.webprogress.IWebAidlCallback;
import com.cxmax.webprogress.IWebAidlInterface;
import com.cxmax.webprogress.RemoteActionConstants;
import com.cxmax.webprogress.interfaces.ResultBack;
import com.google.gson.Gson;

import java.util.Map;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 18-1-20.
 */
public class MainProAidlInterface extends IWebAidlInterface.Stub {

    private Context context;

    public MainProAidlInterface(Context context) {
        this.context = context;
    }

    @Override
    public void handleWebAction(int level, String actionName, String jsonParams, IWebAidlCallback callback) throws RemoteException {
        int pid = android.os.Process.myPid();
        Log.d("webli" , String.format("MainProAidlInterface: 进程ID（%d）， WebView请求（%s）, 参数 （%s）", pid, actionName, jsonParams));
        try {
            handleRemoteAction(level, actionName, new Gson().fromJson(jsonParams, Map.class), callback);
            if (actionName.equals(RemoteActionConstants.ACTION_EVENT_BUS)) {
                handleEventAction(level, actionName, new Gson().fromJson(jsonParams, Map.class), callback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleRemoteAction(int level, final String actionName, Map paramMap, final IWebAidlCallback callback) throws Exception {
        CommandsManager.getInstance(context).findAndExec(level, actionName, paramMap, new ResultBack() {
            @Override
            public void onResult(int status, String action, Object result) {
                try {
                    if (callback != null) {
                        callback.onResult(status, actionName, new Gson().toJson(result));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void handleEventAction(int level, String actinName, Map paramMap, IWebAidlCallback callback) throws Exception {
        String eventType = paramMap.get("messageType").toString();
        Gson gson = new Gson();
    }
}
