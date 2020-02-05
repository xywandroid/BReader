package com.coressoft.breader.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;

import com.coressoft.breader.R;
import com.coressoft.breader.adapter.MyPagerAdapter;
import com.coressoft.breader.base.BaseActivity;
import com.coressoft.breader.bean.Verse;
import com.coressoft.breader.utils.PageFactory;
import com.coressoft.breader.utils.SPUtils;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * Created by Administrator on 2016/1/6.
 */
public class MarkActivity extends BaseActivity {

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.tool_bar_mark)
    Toolbar tool_bar_mark;
    public static MarkActivity main_context;
    private PageFactory pageFactory;
    private Typeface typeface;
    private ArrayList<Verse> catalogueList = new ArrayList<>();
    private DisplayMetrics dm;


    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        initToolbar(tool_bar_mark);
        main_context=this;
        pageFactory = PageFactory.getInstance();
        dm = getResources().getDisplayMetrics();
        typeface = SPUtils.getInstance().getTypeface(this);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),pageFactory.getBookNum()));
    }

    @Override
    public void initToolbar(Toolbar toolBar) {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolBar.setNavigationIcon(R.drawable.back_up);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int getLayoutResId() {
        main_context=this;
        return R.layout.activity_mark;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_end,R.anim.activity_close);
    }
}
