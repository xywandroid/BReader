package com.coressoft.breader;

import android.app.Application;


import com.coressoft.breader.utils.SPUtils;

import skin.support.SkinCompatManager;




public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SPUtils.init(this);
//        SkinMaterialManager.init(this);
        SkinCompatManager.init(this).loadSkin();
//        SkinCompatManager.withoutActivity(this)                         // Basic Widget support
//                .addInflater(new SkinMaterialViewInflater())            // material design support           [selectable]
//                .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout support          [selectable]
//                .addInflater(new SkinCardViewInflater())                // CardView v7 support               [selectable]
//                .setSkinStatusBarColorEnable(false)                     // Disable statusBarColor skin support，default true   [selectable]
//                .setSkinWindowBackgroundEnable(false)                   // Disable windowBackground skin support，default true [selectable]
//                .loadSkin();

    }
}
