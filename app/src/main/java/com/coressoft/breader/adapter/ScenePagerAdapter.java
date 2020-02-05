package com.coressoft.breader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.coressoft.breader.base.BaseSceneFragment;
import com.coressoft.breader.ui.fragment.SceneTransformer;
import com.coressoft.breader.ui.fragment.SplashOneFragment;
import com.coressoft.breader.ui.fragment.SplashThreeFragment;
import com.coressoft.breader.ui.fragment.SplashTwoFragment;


/**
 * Created by Eoin on 8/8/16.
 */
public class ScenePagerAdapter extends FragmentPagerAdapter {

    private SceneTransformer mSceneTransformer;

    public ScenePagerAdapter(FragmentManager supportFragmentManager,
                             SceneTransformer sceneTransformer) {
        super(supportFragmentManager);

        mSceneTransformer = sceneTransformer;
    }

    @Override
    public Fragment getItem(int position) {
        BaseSceneFragment baseSceneFragment = null;

        switch (position) {
            case 0:
                baseSceneFragment = SplashOneFragment.newInstance(position);
                break;
            case 1:
                baseSceneFragment = SplashTwoFragment.newInstance(position);
                break;
            case 2:
                baseSceneFragment = SplashThreeFragment.newInstance(position);
                break;
        }

        mSceneTransformer.addSceneChangeListener(baseSceneFragment);
        return baseSceneFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}