package com.rusefi.ts_plugin;

import com.rusefi.ui.util.FrameHelper;

public class RemoteTabSandbox {
    public static void main(String[] args) {
        new FrameHelper().showFrame(new RemoteTab().getContent());
    }
}
