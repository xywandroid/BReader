package com.coressoft.breader.ui.activity;

import android.os.Bundle;

import com.coressoft.breader.R;
import com.coressoft.breader.base.BaseActivity;


/**
 * Created by lgp on 2015/5/24.
 */
public class SettingActivity extends BaseActivity {


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        init();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

//    @Override
//    protected void initializeDependencyInjector() {
//        App app = (App) getApplication();
//        mActivityComponent = DaggerActivityComponent.builder()
//                .activityModule(new ActivityModule(this))
//                .appComponent(app.getAppComponent())
//                .build();
//        mActivityComponent.inject(this);
//    }




//    @Override
//    protected void initToolbar(){
//        super.initToolbar(toolbar);
//        toolbar.setTitle(R.string.setting);
//    }

//    private void init(){
//        SettingFragment settingFragment = SettingFragment.newInstance();
//        getFragmentManager().beginTransaction().replace(R.id.fragment_content, settingFragment).commit();
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == EvernoteSession.REQUEST_CODE_LOGIN){
//            boolean result = resultCode == RESULT_OK;
//            EventBus.getDefault().post(result);
//        }
//    }

}
