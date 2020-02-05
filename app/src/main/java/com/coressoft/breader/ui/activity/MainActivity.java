package com.coressoft.breader.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coressoft.breader.R;
import com.coressoft.breader.base.BaseActivity;
import com.coressoft.breader.lib.DrawerLayout;
import com.coressoft.breader.ui.fragment.FragmentMark;
import com.coressoft.breader.ui.fragment.FragmentNote;
import com.coressoft.breader.ui.fragment.FragmentRead;
import com.coressoft.breader.ui.fragment.FragmentSettings;
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

            //恢复fragment页面
            restoreFragment();
        } else {      //正常启动时调用
            XLog.i("savedInstanceState == null");
            fragments.add(new FragmentRead());
            fragments.add(new FragmentMark());
            fragments.add(new FragmentNote());
            fragments.add(new FragmentSettings());

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
            case R.id.nav_setting:
                XLog.i("nav_setting is Selected");
                currentIndex = 3;
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
                one_title.setText("系统设置");
                break;
        }
    }
}
