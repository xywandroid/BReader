package com.coressoft.breader.ui.fragment;

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.coressoft.breader.R;
import com.coressoft.breader.base.BaseSceneFragment;
import com.coressoft.breader.utils.ViewHelper;

import butterknife.BindView;


/**
 * Created by Eoin on 8/8/16.
 */
public class SplashTwoFragment extends BaseSceneFragment {
    @BindView(R.id.root)
    RelativeLayout root;
    @BindView(R.id.ameba_icon)
    ImageView ameba_icon;
    @BindView(R.id.twitter_icon)
    ImageView twitter_icon;
    @BindView(R.id.facebook_icon)
    ImageView facebook_icon;

    public static SplashTwoFragment newInstance(int position) {
        SplashTwoFragment scene = new SplashTwoFragment();

        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        scene.setArguments(args);

        return scene;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.splash_fragment_two;
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
//        View view = View.inflate(getActivity(), R.layout.splash_fragment_two, null);
//        setRootPositionTag(root);
//        return view;
//    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setRootPositionTag(root);
    }

    @Override
    protected void initData(View view) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void enterScene(ImageView sharedElement, float position) {
        root.setAlpha(1);

        moveSnsIconOut(position, sharedElement, ameba_icon);
        moveSnsIconOut(position, sharedElement, facebook_icon);
        moveSnsIconOut(position, sharedElement, twitter_icon);

        // scale icons up
        scaleIcon(ameba_icon, 1 - position);
        scaleIcon(facebook_icon, 1 - position);
        scaleIcon(twitter_icon, 1 - position);
    }

    @Override
    public void centerScene(ImageView sharedElement) {
        sharedElement.setTranslationY(
                -getResources().getDimension(R.dimen.tutorial_shared_element_translate_y));
        setSharedImageRadius(sharedElement, 1);
    }

    //position goes from -1.0 to 0.0
    @Override
    public void exitScene(ImageView sharedElement, float position) {
        root.setAlpha(1);

        // scale icons down
        scaleIcon(ameba_icon, 1 + position);
        scaleIcon(facebook_icon, 1 + position);
        scaleIcon(twitter_icon, 1 + position);

        ameba_icon.setAlpha(1 + position);
        facebook_icon.setAlpha(1 + position);
        twitter_icon.setAlpha(1 + position);
    }

    @Override
    public void notInScene() {
        root.setAlpha(0);
    }

    private void moveSnsIconOut(float position, ImageView sharedElement, ImageView icon) {
        Point iconCenter = ViewHelper.getViewCenterPoint(icon);
        Point sharedCenter = ViewHelper.getViewCenterPoint(sharedElement);

        float distanceX = (sharedCenter.x - iconCenter.x) * position;
        float distanceY = (sharedCenter.y - iconCenter.y) * position;

        // multiple position again for an accelerate effect
        icon.setTranslationX(distanceX * position);
        icon.setTranslationY(distanceY * position);
    }

    private void scaleIcon(ImageView icon, float position) {
        icon.setScaleX(position);
        icon.setScaleY(position);
    }
}
