package com.adobs.logscope;

import android.app.Application;
import android.content.Context;
import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.app.BActivityThread;

/**
 * Application Class.
 * यह ऐप का entry point है। यहाँ हम Virtual Engine स्टार्ट करते हैं।
 */
public class LogScopeApp extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            // Virtual Engine को Context के साथ जोड़ना
            BlackBoxCore.get().doAttachBaseContext(base);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            // Engine को बूट करना
            BlackBoxCore.get().doOnCreate();
            
            // यह चेक करना कि क्या हम Virtual Process में हैं या Main Process में
            if (BlackBoxCore.get().isVirtualProcess()) {
                 // अगर यह कोड Virtual Environment के अंदर चल रहा है, 
                 // तो UI load मत करो, सिर्फ Engine load करो।
                 return;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
