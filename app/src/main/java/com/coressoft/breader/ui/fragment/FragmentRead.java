package com.coressoft.breader.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.coressoft.breader.R;
import com.coressoft.breader.adapter.TabFragmentPagerAdapter;
import com.coressoft.breader.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by xingyw on 18-1-31.
 */

public class FragmentRead extends BaseFragment {

    @BindView(R.id.view_pager)
    ViewPager view_pager;

    private ViewPagerListener mViewPagerListener = new ViewPagerListener();
    private int mCurrentFragment = TabState.OLD;


    public interface TabState {
        int OLD = 0;
        int NEW = 1;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_read;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(View view) {
        configFragments();
    }

    @Override
    protected void initListener() {

    }


    public void goOldTab(View view) {
        setPageSelected(TabState.OLD);
    }

    public void goNewTab(View view) {
        setPageSelected(TabState.NEW);
    }

    public void setPageSelected(int position) {
        mCurrentFragment = position;
        if (position != view_pager.getCurrentItem()) {
            view_pager.setCurrentItem(position);
        }
//        old_title.setSelected(false);
//        new_title.setSelected(false);
//        switch (position) {
//            case TabState.OLD:
//                old_title.setSelected(true);
//                break;
//            case TabState.NEW:
//                new_title.setSelected(true);
//                break;
//
//        }
    }

    public class ViewPagerListener extends ViewPager.SimpleOnPageChangeListener {
        @Override
        public void onPageSelected(int position) {
            setPageSelected(position);
        }
    }

    private void configFragments() {
        List<Fragment> list = new ArrayList<>();
        list.add(new OldFragment());
        list.add(new NewFragment());
        view_pager.setAdapter(new TabFragmentPagerAdapter(getActivity().getSupportFragmentManager(), list));
        view_pager.addOnPageChangeListener(mViewPagerListener);
        setPageSelected(mCurrentFragment);
    }

}
