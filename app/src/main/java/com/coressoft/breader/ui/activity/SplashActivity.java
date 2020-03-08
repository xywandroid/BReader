package com.coressoft.breader.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.coressoft.breader.Config;
import com.coressoft.breader.R;
import com.coressoft.breader.adapter.ScenePagerAdapter;
import com.coressoft.breader.adapter.TextPagerAdapter;
import com.coressoft.breader.base.PermissionActivity;
import com.coressoft.breader.ui.fragment.SceneTransformer;
import com.coressoft.breader.utils.CopyFileUtils;
import com.coressoft.breader.utils.XLog;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.File;

import butterknife.BindView;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;


/**
 *  
 * @ProjectName:    BReader 掌上圣经
 * @Package:        SplashActivity.java
 * @ClassName:      SplashActivity
 * @Description:    java类作用描述
 * @Author:         xingyan_wei
 * @CreateDate:     2019/12/22 2019/12/22
 * @UpdateUser:     更新者
 * @UpdateDate:     2019/12/22 2019/12/22
 * @UpdateRemark:   更新内容
 * @Version:        1.0
 */
public class SplashActivity extends PermissionActivity
        implements View.OnTouchListener, View.OnClickListener, ViewPager.OnPageChangeListener {
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.indicator)
    CirclePageIndicator indicator;
    @BindView(R.id.tutorial_pager)
    ViewPager tutorial_pager;
    @BindView(R.id.text_pager)
    ViewPager text_pager;
    @BindView(R.id.touch_interceptor_layout)
    RelativeLayout touch_interceptor_layout;
    @BindView(R.id.root)
    RelativeLayout root;

    ScenePagerAdapter scenePagerAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        XLog.i("SplashActivity is initView");
        SceneTransformer sceneTransformer = new SceneTransformer();
        if (null == scenePagerAdapter) {
            scenePagerAdapter = new ScenePagerAdapter(getSupportFragmentManager(), sceneTransformer);
        }

        tutorial_pager.setAdapter(scenePagerAdapter);
        tutorial_pager.setOffscreenPageLimit(3);
        tutorial_pager.setPageTransformer(true, sceneTransformer);

        TextPagerAdapter textAdapter = new TextPagerAdapter();
        text_pager.setAdapter(textAdapter);

        indicator.setViewPager(text_pager);
        indicator.setSnap(true);
        if (savedInstanceState != null) {
            // re-add the listeners from the scene fragments
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                sceneTransformer.addSceneChangeListener((SceneTransformer.SceneChangeListener) fragment);
            }
        }
    }

    @Override
    protected void initData() {
        copyFileDB();
        getTakePicPermission();
    }

    @Override
    protected void initListener() {
        start.setOnClickListener(this);
        touch_interceptor_layout.setOnTouchListener(this);
        indicator.setOnPageChangeListener(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.splash_activity;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(Config.KEY_BUTTON_VISIBILITY, start.getVisibility());
        savedInstanceState.putFloat(Config.KEY_BUTTON_Y, start.getTranslationY());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        start.setVisibility(savedInstanceState.getInt(Config.KEY_BUTTON_VISIBILITY));
        start.setTranslationY(savedInstanceState.getFloat(Config.KEY_BUTTON_Y));
    }

    private void loadMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void copyFileDB() {
        final File addressDBFile = new File(this.getFilesDir(), Config.DB);
        if (addressDBFile.exists() && addressDBFile.length() > 0) {
            XLog.i("文件已经存在,不需要再次拷贝");
        } else {
            XLog.i("文件不存在,拷贝数据库");
            new Thread() {
                @Override
                public void run() {
                    String f = CopyFileUtils.toSDWriteFile(SplashActivity.this, Config.DB);
                    if (f == null) {
                        XLog.i("拷贝失败");
                    } else {
                        XLog.i("拷贝成功");
                    }
                }

                ;
            }.start();
        }
    }

    private void requestPhotoPermiss() {
        PermissionGen.with(this).addRequestCode(100)
                .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA).request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = 100)
    public void requestPhotoSuccess() {
        // 成功之后的处理
        // .......
    }

    @PermissionFail(requestCode = 100)
    public void requestPhotoFail() {
        // 失败之后的处理，我一般是跳到设置界面
        goToSetting(this);
    }

    public void goToSetting(Context context) {
        // go to setting view
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position == 1) {
            start.setVisibility(View.VISIBLE);
            start.setTranslationY(text_pager.getBottom() * (1 - positionOffset));
            indicator.setAlpha(1 - positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.start:
            loadMainActivity();
            XLog.i("进入主界面中...");
            finish();
            break;
            default:
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        tutorial_pager.onTouchEvent(event);
        text_pager.onTouchEvent(event);
        return true;
    }
}
