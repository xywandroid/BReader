package com.coressoft.breader.ui.fragment;

import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.coressoft.breader.R;
import com.coressoft.breader.adapter.SplashImageAdapter;
import com.coressoft.breader.base.BaseSceneFragment;
import com.coressoft.breader.utils.ViewHelper;

import butterknife.BindView;


/**
 * Created by Eoin on 8/8/16.
 */
public class SplashThreeFragment extends BaseSceneFragment {
    @BindView(R.id.root)
    LinearLayout root;
    @BindView(R.id.recycler_1)
    RecyclerView recycler_1;
    @BindView(R.id.recycler_2)
    RecyclerView recycler_2;
    @BindView(R.id.recycler_3)
    RecyclerView recycler_3;
    @BindView(R.id.recycler_4)
    RecyclerView recycler_4;
    @BindView(R.id.recycler_5)
    RecyclerView recycler_5;
    private static final int SCROLL_OFFSET = 200;

    private Point transitionDistance;
    private int scrollOffsetX;
    private int finishWidth, finishHeight;


    public static SplashThreeFragment newInstance(int position) {
        SplashThreeFragment scene = new SplashThreeFragment();

        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        scene.setArguments(args);

        return scene;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.splash_fragment_three;
    }



    @Override
    protected void initView(Bundle savedState) {
        setRootPositionTag(root);
        setRecyclerView(recycler_1, getResources().obtainTypedArray(R.array.images_1));
        setRecyclerView(recycler_2, getResources().obtainTypedArray(R.array.images_2));
        setRecyclerView(recycler_3, getResources().obtainTypedArray(R.array.images_3));
        setRecyclerView(recycler_4, getResources().obtainTypedArray(R.array.images_4));
        setRecyclerView(recycler_5, getResources().obtainTypedArray(R.array.images_5));

        if (savedState != null) {
            transitionDistance = savedState.getParcelable("transitionDistance");
            finishWidth = savedState.getInt("finishWidth");
            finishHeight = savedState.getInt("finishHeight");
            scrollOffsetX = savedState.getInt("scrollOffsetX");

            moveScrollViews(0);

            // make sure finish view is invisible
            ImageView finishView =
                    (ImageView) recycler_1.getLayoutManager().findViewByPosition(3);
            if (finishView != null) {
                finishView.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initData(View view) {

    }

    @Override
    protected void initListener() {

    }

    private void setRecyclerView(RecyclerView recyclerView, TypedArray drawables) {
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        SplashImageAdapter adapter = new SplashImageAdapter(drawables);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("transitionDistance", transitionDistance);
        outState.putInt("scrollOffsetX", scrollOffsetX);
        outState.putInt("finishWidth", finishWidth);
        outState.putInt("finishHeight", finishHeight);
    }

    @Override
    public void enterScene(ImageView sharedElement, float position) {
        if (transitionDistance == null) {
            setTransition(sharedElement);
        }

        root.setAlpha(1 - position);

        sharedElement.setX(transitionDistance.x * (1 - position));
        sharedElement.setY(-getResources().getDimension(R.dimen.tutorial_shared_element_translate_y)
                + (transitionDistance.y * (1 - position)));
        scaleSharedElement(position, sharedElement);
        setSharedImageRadius(sharedElement, position);

        moveScrollViews(position);
    }

    private void setTransition(ImageView sharedElement) {
        // get the finish view
        ImageView finishView =
                (ImageView) recycler_2.getLayoutManager().findViewByPosition(3);
        finishView.setVisibility(View.INVISIBLE);

        finishHeight = finishView.getHeight();
        finishWidth = finishView.getWidth();

        Point finishViewLocation = ViewHelper.getViewLocation(finishView);

        // find the point of the screen(middle - half image) and for final point to be centered
        int screenCenterX = ViewHelper.getDisplaySize(getActivity()).x / 2;
        int finishWidth = finishView.getWidth() / 2;
        int finishX = screenCenterX - finishWidth;

        // the distance the recyclerview needs to scroll for the finish view to be centered
        scrollOffsetX = finishX - finishViewLocation.x;

        Point sharedLocation = ViewHelper.getViewLocation(sharedElement);
        transitionDistance = new Point();
        transitionDistance.x = finishX - sharedLocation.x;
        transitionDistance.y = finishViewLocation.y - sharedLocation.y;
    }

    @Override
    public void centerScene(ImageView sharedElement) {
        root.setAlpha(1.0f);

        // make sure shared element is set in the correct place
        sharedElement.setX(transitionDistance.x);
        sharedElement.setY(-getResources().getDimension(R.dimen.tutorial_shared_element_translate_y)
                + transitionDistance.y);
        scaleSharedElement(0, sharedElement);

        setSharedImageRadius(sharedElement, 0);
        moveScrollViews(0);
    }

    //position goes from -1.0 to 0.0
    @Override
    public void exitScene(ImageView sharedElement, float position) {
        // last scene, it wont exit
    }

    @Override
    public void notInScene() {
        // reset scroll views
        moveScrollViews(1.0f);
        root.setAlpha(0);
    }

    private void moveScrollViews(float position) {
        // use and odd and even scroll so images don't end up perfectly aligned
        int scroll = (int) (scrollOffsetX * (1 - position) + SCROLL_OFFSET);

        scrollRecycleViewTo(recycler_1, -scroll);
        scrollRecycleViewTo(recycler_2, scroll);
        scrollRecycleViewTo(recycler_3, -scroll);
        scrollRecycleViewTo(recycler_4, scroll);
        scrollRecycleViewTo(recycler_5, -scroll);
    }

    private void scrollRecycleViewTo(RecyclerView recyclerView, int offset) {
        LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
        layoutManager.scrollToPositionWithOffset(2, offset);
    }

    private void scaleSharedElement(float position, ImageView sharedElement) {
        float scaleX = 1 - ((float) finishWidth / sharedElement.getWidth());
        float scaleY = 1 - ((float) finishHeight / sharedElement.getHeight());

        // scale around the center
        sharedElement.setPivotX(1.0f);
        sharedElement.setPivotY(1.0f);

        // scale the shared image to the finish views size
        sharedElement.setScaleX((1 - (scaleX * (1 - position))));
        sharedElement.setScaleY((1 - (scaleY * (1 - position))));
    }
}
