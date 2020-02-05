package com.coressoft.breader.ui.fragment;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coressoft.breader.R;
import com.coressoft.breader.base.BaseSceneFragment;
import com.coressoft.breader.utils.XLog;

import butterknife.BindView;


/**
 * Created by Eoin on 8/8/16.
 */
public class SplashOneFragment extends BaseSceneFragment {
    @BindView(R.id.root)
    RelativeLayout root;
    @BindView(R.id.deviceImage)
    ImageView deviceImage;
    @BindView(R.id.sharedImage)
    ImageView sharedImage;
    @BindView(R.id.deviceText)
    TextView deviceText;

    public static final String KEY_SHARED_VIEW_Y = "KEY_SHARED_VIEW_Y";
    public static final String KEY_SHARED_VIEW_RADIUS = "KEY_SHARED_VIEW_RADIUS";


    public static SplashOneFragment newInstance(int position) {
        XLog.i("SplashOneFragment is newInstance");
        SplashOneFragment scene = new SplashOneFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        scene.setArguments(args);

        return scene;
    }


    @Override
    protected int getLayoutId() {
        XLog.i("SplashOneFragment is getLayoutId");
        return  R.layout.splash_fragment_one;
    }

    @Override
    protected void initView(Bundle savedState) {
        XLog.i("SplashOneFragment is initView");
        setRootPositionTag(root);
        Resources resources = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_shared);
        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(resources, bitmap);
        drawable.setAntiAlias(true);
        if(savedState != null){
            sharedImage.setTranslationY(savedState.getFloat(KEY_SHARED_VIEW_Y));
            drawable.setCornerRadius(savedState.getFloat(KEY_SHARED_VIEW_RADIUS));
        }
        sharedImage.setImageDrawable(drawable);
    }

    @Override
    protected void initData(View view) {

    }

    @Override
    protected void loadData() {
//        setRootPositionTag(root);
    }


    @Override
    protected void initListener() {

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save the shared image view y and radius
        outState.putFloat(KEY_SHARED_VIEW_Y, sharedImage.getTranslationY());
        outState.putFloat(KEY_SHARED_VIEW_RADIUS,
                ((RoundedBitmapDrawable) sharedImage.getDrawable()).getCornerRadius());
    }

    @Override
    public void enterScene(ImageView sharedElement, float position) {
        // starts center so no entrance needed
    }

    @Override
    public void centerScene(ImageView sharedElement) {
        sharedImage.setTranslationY(0);
        setSharedImageRadius(sharedImage, 0);

        deviceImage.setAlpha(1.0f);
        deviceText.setAlpha(1.0f);
        deviceImage.setAlpha(1.0f);
        deviceImage.setScaleX(1.0f);
    }

    //position goes from -1.0 to 0.0
    @Override
    public void exitScene(ImageView sharedElement, float position) {
        sharedImage.setTranslationY(
                getResources().getDimension(R.dimen.tutorial_shared_element_translate_y)* position);

        setSharedImageRadius(sharedImage, -position);

        deviceText.setAlpha(1 + position);
        deviceImage.setAlpha(1 + position);
        deviceImage.setScaleX(1 - position); // stretch
    }

    @Override
    public void notInScene() {
        deviceImage.setAlpha(0.0f);
        deviceText.setAlpha(0.0f);
    }
}
