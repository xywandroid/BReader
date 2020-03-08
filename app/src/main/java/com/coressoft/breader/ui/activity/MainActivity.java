package com.coressoft.breader.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coressoft.breader.R;
import com.coressoft.breader.base.BaseActivity;
import com.coressoft.breader.lib.DrawerLayout;
import com.coressoft.breader.ui.fragment.FragmentAbout;
import com.coressoft.breader.ui.fragment.FragmentDonate;
import com.coressoft.breader.ui.fragment.FragmentMark;
import com.coressoft.breader.ui.fragment.FragmentNote;
import com.coressoft.breader.ui.fragment.FragmentRead;
import com.coressoft.breader.ui.fragment.FragmentSettings;
import com.coressoft.breader.ui.fragment.FragmentShare;
import com.coressoft.breader.utils.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    @BindView(R.id.navigation_view)
    NavigationView navigation_view;
    @BindView(R.id.tool_bar)
    Toolbar tool_bar;
    @BindView(R.id.double_title)
    LinearLayout double_title;
    @BindView(R.id.one_title)
    TextView one_title;

    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
    private FragmentManager fragmentManager;
    private Fragment currentFragment = new Fragment();
    private List<Fragment> fragments = new ArrayList<>();

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar(tool_bar);
        initNavigationView(navigation_view);
        initFragementView(savedInstanceState);
        initBarString(currentIndex);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    public void initToolbar(Toolbar toolBar) {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolBar.setNavigationIcon(R.drawable.ic_menu);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout gl = (DrawerLayout) findViewById(R.id.drawer_layout);
                gl.openDrawer(GravityCompat.START);
                drawer_layout.openDrawer(GravityCompat.START);
            }
        });
    }


    private void initFragementView(Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            // “内存重启”时调用
            XLog.i("savedInstanceState != null");
            //获取“内存重启”时保存的索引下标
            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT, 0);

            //注意，添加顺序要跟下面添加的顺序一样！！！！
            fragments.removeAll(fragments);
            fragments.add(fragmentManager.findFragmentByTag(0 + ""));
            fragments.add(fragmentManager.findFragmentByTag(1 + ""));
            fragments.add(fragmentManager.findFragmentByTag(2 + ""));
            fragments.add(fragmentManager.findFragmentByTag(3 + ""));
            fragments.add(fragmentManager.findFragmentByTag(4 + ""));
            fragments.add(fragmentManager.findFragmentByTag(5 + ""));
            fragments.add(fragmentManager.findFragmentByTag(6 + ""));
            //恢复fragment页面
            restoreFragment();
        } else {      //正常启动时调用
            XLog.i("savedInstanceState == null");
            fragments.add(new FragmentRead());
            fragments.add(new FragmentMark());
            fragments.add(new FragmentNote());
            fragments.add(new FragmentSettings());

            fragments.add(new FragmentShare());
            fragments.add(new FragmentDonate());
            fragments.add(new FragmentAbout());

            showFragment();
        }
    }


    /**
     * 使用show() hide()切换页面
     * 显示fragment
     */
    private void showFragment() {

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //如果之前没有添加过
        if (!fragments.get(currentIndex).isAdded()) {
            XLog.i("之前没有添加过");
            transaction
                    .hide(currentFragment)
                    .add(R.id.main_framelayout, fragments.get(currentIndex), "" + currentIndex);  //第三个参数为添加当前的fragment时绑定一个tag

        } else {
            XLog.i("之前有添加过");
            transaction.hide(currentFragment);
            transaction.show(fragments.get(currentIndex));
        }

        currentFragment = fragments.get(currentIndex);

        transaction.commit();
    }

    /**
     * 恢复fragment
     */
    private void restoreFragment() {
        FragmentTransaction mBeginTreansaction = fragmentManager.beginTransaction();

        for (int i = 0; i < fragments.size(); i++) {

            if (i == currentIndex) {
                mBeginTreansaction.show(fragments.get(i));
            } else {
                mBeginTreansaction.hide(fragments.get(i));
            }
        }
        mBeginTreansaction.commit();
        //把当前显示的fragment记录下来
        currentFragment = fragments.get(currentIndex);

    }


    private void initNavigationView(NavigationView navigationView) {
        View headerView = getLayoutInflater().inflate(R.layout.nav_header_main, null, false);
        navigationView.addHeaderView(headerView);
        navigationView.setNavigationItemSelectedListener(this);

        View footView = getLayoutInflater().inflate(R.layout.left_view_bottom_layout, null, false);
        navigationView.addView(footView);
        LinearLayout settingBtn = footView.findViewById(R.id.settings_btn_left);
        LinearLayout dayNigthBtn = footView.findViewById(R.id.day_night_btn_left);
        LinearLayout weatherBtn = footView.findViewById(R.id.weather_btn_left);

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XLog.i("点击了setting 按钮");
            }
        });

        dayNigthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XLog.i("切换白天黑夜");
            }
        });

        weatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XLog.i("查看天气");
            }
        });
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        XLog.i("=======id=======" + id);
        switch (id) {
            case R.id.nav_read:
                currentIndex = 0;
                initBarString(currentIndex);
                break;
            case R.id.nav_mark:
                currentIndex = 1;
                initBarString(currentIndex);
                break;
            case R.id.nav_note:
                currentIndex = 2;
                initBarString(currentIndex);
                break;
            case R.id.nav_im:
                XLog.i("nav_setting is Selected");
                currentIndex = 3;
//                initBarString(currentIndex);
                break;

            case R.id.nav_share:
                XLog.i("nav_setting is Selected");
                currentIndex = 4;
                initBarString(currentIndex);
                break;

            case R.id.nav_donate:
                XLog.i("nav_setting is Selected");
                currentIndex = 5;
                initBarString(currentIndex);
                break;

            case R.id.nav_about:
                XLog.i("nav_setting is Selected");
                currentIndex = 6;
                initBarString(currentIndex);
                break;
        }
        XLog.i("currentIndex------->>" + currentIndex);
        drawer_layout.closeDrawer(GravityCompat.START);
        showFragment();
        return true;
    }

    private void initBarString(int index) {
        switch (index) {
            case 0:
                double_title.setVisibility(View.VISIBLE);
                one_title.setVisibility(View.GONE);

                break;
            case 1:
                double_title.setVisibility(View.GONE);
                one_title.setVisibility(View.VISIBLE);
                one_title.setText("书签");
                break;
            case 2:
                double_title.setVisibility(View.GONE);
                one_title.setVisibility(View.VISIBLE);
                one_title.setText("笔记");
                break;
            case 3:
                double_title.setVisibility(View.GONE);
                one_title.setVisibility(View.VISIBLE);
                one_title.setText("姊妹交流");
                break;
            case 4:
                double_title.setVisibility(View.GONE);
                one_title.setVisibility(View.VISIBLE);
                one_title.setText("好友分享");
                break;
            case 5:
                double_title.setVisibility(View.GONE);
                one_title.setVisibility(View.VISIBLE);
                one_title.setText("捐赠");
                break;
            case 6:
                double_title.setVisibility(View.GONE);
                one_title.setVisibility(View.VISIBLE);
                one_title.setText("关于我们");
                break;
        }
    }
}
//TODO Setting 的按钮没有进行设置正确，需要进行修改，重新建立IM 交流页面，并且图标需要进行更换。