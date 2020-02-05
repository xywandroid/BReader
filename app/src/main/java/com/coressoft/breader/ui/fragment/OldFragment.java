package com.coressoft.breader.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.coressoft.breader.R;
import com.coressoft.breader.adapter.OldBookAdapter;
import com.coressoft.breader.animation.ContentScaleAnimation;
import com.coressoft.breader.animation.Rotate3DAnimation;
import com.coressoft.breader.base.BaseFragment;
import com.coressoft.breader.bean.Book;
import com.coressoft.breader.db.DBManager;
import com.coressoft.breader.ui.view.BookView;
import com.coressoft.breader.ui.view.DragGridView;
import com.coressoft.breader.utils.DisplayUtils;
import com.coressoft.breader.utils.SPUtils;
import com.coressoft.breader.utils.XLog;

import java.util.List;

/**
 * Created by xingyw on 18-1-18.
 */

public class OldFragment extends BaseFragment implements Animation.AnimationListener,AdapterView.OnItemClickListener {

    private OldBookAdapter mOldReadAdapter;
    protected DragGridView old_read_list;
    private List<Book> bibleOldBooks;
    //点击书本的位置
    private int itemPosition;

    private TextView itemTextView;
    //点击书本在屏幕中的x，y坐标
    private int[] location = new int[2];
    private WindowManager mWindowManager;
    private AbsoluteLayout wmRootView;
    private static TextView cover;
    //    private static ImageView content;
    //书本打开动画缩放比例
    private float scaleTimes;
    //书本打开缩放动画
    private static ContentScaleAnimation contentAnimation;
    private static Rotate3DAnimation coverAnimation;
    //书本打开缩放动画持续时间
    public static final int ANIMATION_DURATION = 2000;
    private int animationCount=0;
    //打开书本的第一个动画是否完成
    private boolean mIsOpen = false;
    private Typeface typeface;
    private ImageView backImage;

    private BookView mContentview;
    private int mPosition;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_old;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(View view) {
        typeface = SPUtils.getInstance().getTypeface(getContext());
        mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        wmRootView = new AbsoluteLayout(getContext());
        DBManager dbManager = DBManager.getInstance(getActivity());
        bibleOldBooks = dbManager.getOldBookName();
        initView(view);
    }

    private void initView(View view){
        old_read_list = (DragGridView) view.findViewById(R.id.bookShelf);
    }

    @Override
    protected void initListener() {
        mOldReadAdapter = new OldBookAdapter(getActivity(),bibleOldBooks);
        old_read_list.setAdapter(mOldReadAdapter);
        old_read_list.setOnItemClickListener(this);
    }

    //初始化dialog动画
    private void initAnimation() {
        Log.i("xing.yw","initAnimation");
        AccelerateInterpolator interpolator = new AccelerateInterpolator();

        float scale1 = DisplayUtils.getScreenWidthPixels(getContext()) / (float) itemTextView.getMeasuredWidth();
        float scale2 = DisplayUtils.getScreenHeightPixels(getContext()) / (float) itemTextView.getMeasuredHeight();
        scaleTimes = scale1 > scale2 ? scale1 : scale2;  //计算缩放比例

        contentAnimation = new ContentScaleAnimation( location[0], location[1],scaleTimes, false);
        contentAnimation.setInterpolator(interpolator);  //设置插值器
        contentAnimation.setDuration(ANIMATION_DURATION);
        contentAnimation.setFillAfter(true);  //动画停留在最后一帧
        contentAnimation.setAnimationListener(this);

        coverAnimation = new Rotate3DAnimation(0, -180, location[0], location[1], scaleTimes, false);
        coverAnimation.setInterpolator(interpolator);
        coverAnimation.setDuration(ANIMATION_DURATION);
        coverAnimation.setFillAfter(true);
        coverAnimation.setAnimationListener(this);
    }





    public void closeView(){
//        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        wmRootView.removeView(mContentview);
        wmRootView.removeAllViews();
        closeBookAnimation();
//
//
    }

    public void closeBookAnimation() {
        XLog.i("closeBookAnimation----->>");
        if (mIsOpen && wmRootView!=null) {
            //动画逆向运行
            if (!contentAnimation.getMReverse()) {
                contentAnimation.reverse();
            }
            if (!coverAnimation.getMReverse()) {
                coverAnimation.reverse();
            }
            //清除动画再开始动画
            backImage.clearAnimation();
            backImage.startAnimation(contentAnimation);
            cover.clearAnimation();
            cover.startAnimation(coverAnimation);
        }
    }



    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //有两个动画监听会执行两次，所以要判断
        if (!mIsOpen) {
            mContentview = new BookView(getContext(),mPosition,this);
            wmRootView.addView(mContentview);
            XLog.i("mIsOpen----->>"+mIsOpen);
            XLog.i("animationCount----->>"+animationCount);
            animationCount++;
            XLog.i("animationCount----->>"+animationCount);
            if (animationCount >= 2) {
                mIsOpen = true;
                Book bookList = bibleOldBooks.get(itemPosition);
                bookList.setId(bibleOldBooks.get(0).getId());
            }

        } else {
            XLog.i("mIsOpen----->>"+mIsOpen);
            animationCount--;
            XLog.i("animationCount----->>"+animationCount);
            if (animationCount <= 0) {
                mIsOpen = false;
//                wmRootView.removeView(mContentview);
                wmRootView.removeView(cover);
                wmRootView.removeView(backImage);
                mWindowManager.removeView(wmRootView);
            }
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    //获取dialog属性
    private WindowManager.LayoutParams getDefaultWindowParams() {
        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        wmParams.format = PixelFormat.RGBA_8888;
        wmParams.flags =  WindowManager.LayoutParams.FLAG_FULLSCREEN;
        wmParams.x = 0;
        wmParams.y = 0;
        wmParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL;
        wmParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        wmParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        return wmParams;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPosition = position+1;
        if (bibleOldBooks.size() > position) {
            itemPosition = position;
            String bookname = bibleOldBooks.get(itemPosition).getName();
            Log.i("xing.yw", "bookname--->>" + bookname);
            final Book bookList = bibleOldBooks.get(itemPosition);
            bookList.setId(bibleOldBooks.get(0).getId());


            //TODO Animation start
            itemTextView = (TextView) view.findViewById(R.id.tv_name);
            //获取item在屏幕中的x，y坐标
            itemTextView.getLocationInWindow(location);

            //初始化dialog
            mWindowManager.addView(wmRootView, getDefaultWindowParams());
            cover = new TextView(getContext().getApplicationContext());
            cover.setBackgroundDrawable(getResources().getDrawable(R.drawable.cover_default_new));
            cover.setCompoundDrawablesWithIntrinsicBounds(null, null, null, getResources().getDrawable(R.drawable.cover_type_txt));
            cover.setText(bookname);
            cover.setTextColor(getResources().getColor(R.color.read_textColor));
            cover.setTypeface(typeface);
            int coverPadding = (int) CommonUtil.convertDpToPixel(getContext().getApplicationContext(), 10);
            cover.setPadding(coverPadding, coverPadding, coverPadding, coverPadding);
            //TODO　需要更改为自定义View 因为ImageView不能满足需求
            backImage = new ImageView(getContext().getApplicationContext());

            Bitmap contentBitmap = Bitmap.createBitmap(itemTextView.getMeasuredWidth(), itemTextView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            contentBitmap.eraseColor(getResources().getColor(R.color.read_background_paperYellow));
            backImage.setImageBitmap(contentBitmap);
//                    mContentview.setBgColor(getResources().getColor(R.color.read_background_paperYellow));
            AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(
                    itemTextView.getLayoutParams());
            params.x = location[0];
            params.y = location[1];
            wmRootView.addView(backImage, params);
            wmRootView.addView(cover, params);

            initAnimation();
            if (contentAnimation.getMReverse()) {
                contentAnimation.reverse();
            }
            if (coverAnimation.getMReverse()) {
                coverAnimation.reverse();
            }
            cover.clearAnimation();
            cover.startAnimation(coverAnimation);
            backImage.clearAnimation();
            backImage.startAnimation(contentAnimation);
            //TODO Animation end
        }
    }
}
