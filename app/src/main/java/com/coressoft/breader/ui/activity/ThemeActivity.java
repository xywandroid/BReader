package com.coressoft.breader.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coressoft.breader.R;
import com.coressoft.breader.base.BaseActivity;
import com.coressoft.breader.utils.SPUtils;
import com.coressoft.breader.utils.XLog;

import butterknife.BindView;
import skin.support.SkinCompatManager;

/**
 * Created by xingyw on 18-1-17.
 */

public class ThemeActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.tool_bar)
    Toolbar tool_bar;
    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.rl_red_theme)
    RelativeLayout rl_red_theme;
    @BindView(R.id.rl_white_theme)
    RelativeLayout rl_white_theme;
    @BindView(R.id.rl_color_theme)
    RelativeLayout rl_color_theme;

    @BindView(R.id.red_preview)
    ImageView red_preview;
    @BindView(R.id.white_preview)
    ImageView white_preview;
    @BindView(R.id.color_preview)
    ImageView color_preview;
    private int mCurSkin = -1;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_theme;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar(tool_bar);
        initView();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    private void initView(){
        rl_red_theme.setOnClickListener(this);
        rl_white_theme.setOnClickListener(this);
        rl_color_theme.setOnClickListener(this);
        int color = SPUtils.getInstance().getThemeMode();
        switch (color){
            case 0 :
                red_preview.setVisibility(View.VISIBLE);
                white_preview.setVisibility(View.GONE);
                color_preview.setVisibility(View.GONE);
                SPUtils.getInstance().setThemeMode(1).commitEditor();
                break;
            case 1:
                red_preview.setVisibility(View.VISIBLE);
                white_preview.setVisibility(View.GONE);
                color_preview.setVisibility(View.GONE);
                break;
            case 2:
                red_preview.setVisibility(View.GONE);
                white_preview.setVisibility(View.VISIBLE);
                color_preview.setVisibility(View.GONE);
                break;
            case 3:
                red_preview.setVisibility(View.GONE);
                white_preview.setVisibility(View.GONE);
                color_preview.setVisibility(View.VISIBLE);
                break;
        }

    }



    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_red_theme:
                SkinCompatManager.getInstance().restoreDefaultTheme();
                SPUtils.getInstance().setThemeMode(1).commitEditor();
                red_preview.setVisibility(View.VISIBLE);
                white_preview.setVisibility(View.GONE);
                color_preview.setVisibility(View.GONE);
                break;
            case R.id.rl_white_theme:
                SkinCompatManager.getInstance().loadSkin("white.skin", new SkinCompatManager.SkinLoaderListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess() {
                        XLog.i("white_theme is onSuccess");
                        SPUtils.getInstance().setThemeMode(2).commitEditor();
                        red_preview.setVisibility(View.GONE);
                        white_preview.setVisibility(View.VISIBLE);
                        color_preview.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailed(String s) {
                        XLog.i("white_theme is onFailed");
                    }
                });

                break;
            case R.id.rl_color_theme:
                SPUtils.getInstance().setThemeMode(3).commitEditor();
                red_preview.setVisibility(View.GONE);
                white_preview.setVisibility(View.GONE);
                color_preview.setVisibility(View.VISIBLE);
                break;
        }
    }


//    private void initHeaderView() {
//        mHeaderBinding.red.name.setText("官方红");
//        mHeaderBinding.red.preview.setAspectRatio(4 * 1.0f / 3);
//        mHeaderBinding.red.preview.setImageResource(R.drawable.skin_red);
//        mHeaderBinding.red.getRoot().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SkinCompatManager.getInstance().restoreDefaultTheme();
//                SPUtils.getInstance().setNightMode(false).commitEditor();
//                showSkinIndicator(0);
//            }
//        });
//        mHeaderBinding.white.name.setText("官方白");
//        mHeaderBinding.white.preview.setAspectRatio(4 * 1.0f / 3);
//        mHeaderBinding.white.preview.setImageResource(R.drawable.skin_white);
//        mHeaderBinding.white.getRoot().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SkinCompatManager.getInstance().loadSkin("white.skin", new SkinCompatManager.SkinLoaderListener() {
//                    @Override
//                    public void onStart() {
//                    }
//
//                    @Override
//                    public void onSuccess() {
//                        SPUtils.getInstance().setNightMode(false).commitEditor();
//                        showSkinIndicator();
//                    }
//
//                    @Override
//                    public void onFailed(String s) {
//                    }
//                });
//            }
//        });
////        mHeaderBinding.color.getRoot().setVisibility(View.INVISIBLE);
//        mHeaderBinding.color.name.setText("自选颜色");
//        mHeaderBinding.color.preview.setAspectRatio(4 * 1.0f / 3);
//        mHeaderBinding.color.preview.setImageResource(R.drawable.skin_color);
//        showSkinIndicator();
//    }
//
//    private void showSkinIndicator() {
//        String curSkinName = SkinCompatManager.getInstance().getCurSkinName();
//        for (int i = 0; i < SKIN_LIBS.length; i++) {
//            if (SKIN_LIBS[i].equals(curSkinName)) {
//                showSkinIndicator(i);
//            }
//        }
//    }
//
//    private void showSkinIndicator(int index) {
//        hideSkinIndicator(mCurSkin);
//        switch (index) {
//            case 0:
//                mHeaderBinding.red.description.setText("使用中");
//                mHeaderBinding.red.indicator.setVisibility(View.VISIBLE);
//                mHeaderBinding.red.description.setVisibility(View.VISIBLE);
//                mCurSkin = 0;
//                break;
//            case 1:
//                mHeaderBinding.white.description.setText("使用中");
//                mHeaderBinding.white.indicator.setVisibility(View.VISIBLE);
//                mHeaderBinding.white.description.setVisibility(View.VISIBLE);
//                mCurSkin = 1;
//                break;
//            case 2:
//                mHeaderBinding.color.description.setText("使用中");
//                mHeaderBinding.color.indicator.setVisibility(View.VISIBLE);
//                mHeaderBinding.color.description.setVisibility(View.VISIBLE);
//                mCurSkin = 2;
//                break;
//        }
//    }
//
//    private void hideSkinIndicator(int index) {
//        mCurSkin = -1;
////        switch (index) {
////            case 0:
////                red.indicator.setVisibility(View.INVISIBLE);
////                mHeaderBinding.red.description.setVisibility(View.INVISIBLE);
////                break;
////            case 1:
////                mHeaderBinding.white.indicator.setVisibility(View.INVISIBLE);
////                mHeaderBinding.white.description.setVisibility(View.INVISIBLE);
////                break;
////            case 2:
////                mHeaderBinding.color.indicator.setVisibility(View.INVISIBLE);
////                mHeaderBinding.color.description.setVisibility(View.INVISIBLE);
////                break;
////        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_skin_lib_options, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

}