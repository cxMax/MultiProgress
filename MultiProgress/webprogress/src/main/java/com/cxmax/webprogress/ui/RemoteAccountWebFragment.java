package com.cxmax.webprogress.ui;

import android.os.Bundle;

import com.cxmax.webprogress.CommandsManager;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 18-1-20.
 */

public class RemoteAccountWebFragment extends RemoteCommonWebFragment {

    private final int COMMAND_LEVEL_ACCOUNT = CommandsManager.LEVEL_ACCOUNT;

    public static RemoteAccountWebFragment newInstance(String url) {
        RemoteAccountWebFragment fragment = new RemoteAccountWebFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getCommandLevel() {
        return COMMAND_LEVEL_ACCOUNT;
    }
}
