package com.coressoft.breader.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coressoft.breader.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xingyw on 18-1-17.
 */

public abstract class BaseFragment extends Fragment {
    private View rootView;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        rootView = view;
        // 初始化View注入
        bind = ButterKnife.bind(this, view);
        initData(view);
        initListener();
        initView(savedInstanceState);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    public int getColorPrimary(){
        TypedValue typedValue = new  TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }
    protected abstract void loadData();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initData(View view);

    protected abstract void initListener();

    protected abstract int getLayoutId();
}
