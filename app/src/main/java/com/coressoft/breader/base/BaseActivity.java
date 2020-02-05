package com.coressoft.breader.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.coressoft.breader.R;
import com.coressoft.breader.utils.PreUtils;
import com.coressoft.breader.utils.Theme;

import butterknife.ButterKnife;
import skin.support.app.SkinCompatActivity;


public abstract class BaseActivity extends SkinCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);

            initView(savedInstanceState);
        initData();
        initListener();
        onPreCreate();
        // 初始化View注入
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //ButterKnife.unbind(this);
    }

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initData();

    protected abstract void initListener();


    @LayoutRes
    protected abstract int getLayoutResId();

    private void onPreCreate() {
        Theme theme = PreUtils.getCurrentTheme(this);
        switch (theme) {
            case Blue:
                setTheme(R.style.BlueTheme);
                break;
            case Red:
                setTheme(R.style.RedTheme);
                break;
            case Brown:
                setTheme(R.style.BrownTheme);
                break;
            case Green:
                setTheme(R.style.GreenTheme);
                break;
//            case Purple:
//                setTheme(R.style.PurpleTheme);
//                break;
//            case Teal:
//                setTheme(R.style.TealTheme);
//                break;
            case Pink:
                setTheme(R.style.PinkTheme);
                break;
            case DeepPurple:
                setTheme(R.style.DeepPurpleTheme);
                break;
//            case Orange:
//                setTheme(R.style.OrangeTheme);
//                break;
//            case Indigo:
//                setTheme(R.style.IndigoTheme);
//                break;
//            case LightGreen:
//                setTheme(R.style.LightGreenTheme);
//                break;
//            case Lime:
//                setTheme(R.style.LimeTheme);
//                break;
//            case DeepOrange:
//                setTheme(R.style.DeepOrangeTheme);
//                break;
//            case Cyan:
//                setTheme(R.style.CyanTheme);
//                break;
            case BlueGrey:
                setTheme(R.style.BlueGreyTheme);
                break;
        }
    }

//    @TargetApi(19)




     private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            Log.i("xing.yw","========");
            winParams.flags |= bits;
        }
        win.setAttributes(winParams);
    }

    protected String getName() {
        return BaseActivity.class.getName();
    }

        public void initToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

//    protected void initToolbar(Toolbar toolbar){
////        ToolbarUtils.initToolbar(toolbar, this);
////    }

    public int getColorPrimary(){
        TypedValue typedValue = new  TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }
}

