package com.cxmax.webprogress.interfaces;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 18-1-20.
 */

public class JsRemoteInterface {

    private final Context mContext;
    private final Handler mHandler = new Handler();
    private final Map<String, Command> mCommands = new HashMap<>();
    private AidlCommand aidlCommand;

    public interface AidlCommand {
        void exec(String cmd, String params);
    }

    public JsRemoteInterface(Context context) {
        mContext = context;
    }

    @JavascriptInterface
    public void post(final String cmd, final String param) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    final Command command = mCommands.get(cmd);
                    if (command != null) {
                        if (TextUtils.isEmpty(param) || param.equals("undefined")) {
                            command.exec(mContext, null, null);
                        } else {
                            command.exec(mContext, new Gson().fromJson(param, Map.class), null);
                        }
                    } else {
                        if (aidlCommand != null) {
                            aidlCommand.exec(cmd, param);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setAidlCommand(AidlCommand aidlCommand) {
        this.aidlCommand = aidlCommand;
    }

    public void registerCommand(Command command) {
        mCommands.put(command.name(), command);
    }

    public void unregisterCommand(Command command) {
        mCommands.remove(command.name());
    }

    public void unregisterAllCommands() {
        mCommands.clear();
    }

}
