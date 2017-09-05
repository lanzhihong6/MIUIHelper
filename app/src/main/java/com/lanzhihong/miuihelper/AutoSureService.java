package com.lanzhihong.miuihelper;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * 自动确认安装
 */
public class AutoSureService extends AccessibilityService {


    @Override
    protected void onServiceConnected() {

        Log.d("lanzhihong", "onServiceConnected");
    }


    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

        int eventType = accessibilityEvent.getEventType();

        //com.miui.securitycenter.install.AdbInstallActivity   user安装提示  继续安装

        if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {

            if (accessibilityEvent.getPackageName().equals("com.miui.securitycenter")) {  //小米安全中心

                AccessibilityNodeInfo info = getViewByText("继续安装");

                if (info != null) {
                    info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }

            }
        }
    }


    @Override
    public void onInterrupt() {

    }


    private AccessibilityNodeInfo getViewByText(String text) {

        if (getRootInActiveWindow() == null) {
            return null;
        }

        List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByText(text);

        if (nodes != null && !nodes.isEmpty()) {

            return nodes.get(0);

        }

        return null;
    }

}
