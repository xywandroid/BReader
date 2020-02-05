package com.coressoft.breader.ui.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.coressoft.breader.R;
import com.coressoft.breader.ui.activity.MarkActivity;
import com.coressoft.breader.ui.fragment.OldFragment;
import com.coressoft.breader.utils.BrightnessUtil;
import com.coressoft.breader.utils.PageFactory;
import com.coressoft.breader.utils.SPUtils;
import com.coressoft.breader.utils.XLog;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;



public class BookView extends RelativeLayout implements View.OnClickListener ,SeekBar.OnSeekBarChangeListener,OnMenuItemClickListener, OnMenuItemLongClickListener {
    private Boolean isShow = false;
    private Boolean mDayOrNight;
    private ContentView bookpage;
    private RelativeLayout rl_bottom;

    private TextView tv_dayornight;
    private RelativeLayout appbar;

    private Context mContext;
    private PageFactory pageFactory;
    private SettingDialog mSettingDialog;
    private PageModeDialog mPageModeDialog;
    private int currentChapter = 1;
    private TextView tv_pagemode;
    private TextView tv_setting;
    private TextView tv_pre;
    private TextView tv_next;
    private TextView tv_directory;
    private TextView title_bar_back;
    TextView btn_more;
    private int mPosition;
    private OldFragment mFragment;
    //动画执行时间
    private static int DURATION_TIME = 500;


    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;

    public BookView(Context context, int position, OldFragment mFragment) {
        super(context);
        this.mPosition = position;
        this.mFragment = mFragment;
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.content_view_setting,this);
        bookpage = (ContentView) view.findViewById(R.id.bookpage);
        tv_dayornight = (TextView) view.findViewById(R.id.tv_dayornight);
        appbar = (RelativeLayout) view.findViewById(R.id.appbar);
        rl_bottom = (RelativeLayout) view.findViewById(R.id.rl_bottom);

        tv_pagemode = (TextView) view.findViewById(R.id.tv_pagemode);
        tv_setting = (TextView) view.findViewById(R.id.tv_setting);
        tv_pre = (TextView) view.findViewById(R.id.tv_pre);
        tv_next = (TextView) view.findViewById(R.id.tv_next);
        tv_directory = (TextView) view.findViewById(R.id.tv_directory);
        title_bar_back = (TextView) view.findViewById(R.id.title_bar_back);
        btn_more = (TextView) view.findViewById(R.id.btn_more);
        title_bar_back.setOnClickListener(this);
        tv_directory.setOnClickListener(this);
        tv_dayornight.setOnClickListener(this);
        tv_next.setOnClickListener(this);
        tv_pre.setOnClickListener(this);
        tv_pagemode.setOnClickListener(this);
        tv_setting.setOnClickListener(this);

        btn_more.setOnClickListener(this);
        bookpage.setBgColor(getResources().getColor(R.color.read_bg_3));
        initData();
        initListener();
        initMenuFragment();
        fragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
    }




    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }


    private List<MenuObject> getMenuObjects() {

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.icn_close);

        MenuObject send = new MenuObject("简注");
        send.setResource(R.drawable.icn_1);

        MenuObject addFr = new MenuObject("笔记");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.icn_3));
        addFr.setDrawable(bd);

        MenuObject addFav = new MenuObject("收藏");
        addFav.setResource(R.drawable.icn_4);

        MenuObject block = new MenuObject("书签");
        block.setResource(R.drawable.icn_5);

        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
        menuObjects.add(block);
        return menuObjects;
    }



    public BookView(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    private void initData(){
        if(Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 19){
            bookpage.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        PageFactory.createPageFactory(mContext);
        pageFactory = PageFactory.getInstance();

        IntentFilter mfilter = new IntentFilter();
        mfilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        mfilter.addAction(Intent.ACTION_TIME_TICK);
        mContext.registerReceiver(myReceiver, mfilter);

        mSettingDialog = new SettingDialog(mContext);
        mPageModeDialog = new PageModeDialog(mContext);
        //获取屏幕宽高
        WindowManager manage = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = manage.getDefaultDisplay();
        Point displaysize = new Point();
        display.getSize(displaysize);
        //保持屏幕常亮
        ((Activity)getContext()).getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //改变屏幕亮度
        if (!SPUtils.getInstance().isSystemLight()) {
            BrightnessUtil.setBrightness(((Activity)getContext()), SPUtils.getInstance().getLight());
        }
        bookpage.setPageMode(SPUtils.getInstance().getPageMode());
        pageFactory.setPageWidget(bookpage);
//
        try {
            pageFactory.openChapterContent(mPosition,currentChapter);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(mContext, "打开电子书失败", Toast.LENGTH_SHORT).show();
        }
        initDayOrNight();
    }







    protected void initListener() {
        bookpage.setTouchListener(new ContentView.TouchListener() {
            @Override
            public void center() {
                if (isShow) {
                    hideReadSetting();
                } else {
                    showReadSetting();
                }
            }

            @Override
            public Boolean prePage() {
                if (isShow ){
                    return false;
                }

                pageFactory.prePage();
                if (pageFactory.isfirstPage()) {
                    return false;
                }

                return true;
            }

            @Override
            public Boolean nextPage() {
                if (isShow ){
                    return false;
                }

                pageFactory.nextPage();
                if (pageFactory.islastPage()) {
                    return false;
                }
                return true;
            }

            @Override
            public void cancel() {
                pageFactory.cancelPage();
            }
        });

        mPageModeDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
//                hideSystemUI();
            }
        });

        mPageModeDialog.setPageModeListener(new PageModeDialog.PageModeListener() {
            @Override
            public void changePageMode(int pageMode) {
                bookpage.setPageMode(pageMode);
            }
        });

        mSettingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
            }
        });

        mSettingDialog.setSettingListener(new SettingDialog.SettingListener() {
            @Override
            public void changeSystemBright(Boolean isSystem, float brightness) {
                if (!isSystem) {
                    BrightnessUtil.setBrightness((Activity)getContext(), brightness);
                } else {
                    int bh = BrightnessUtil.getScreenBrightness((Activity)getContext());
                    BrightnessUtil.setBrightness((Activity)getContext(), bh);
                }
            }

            @Override
            public void changeFontSize(int fontSize) {
                pageFactory.changeFontSize(fontSize);
            }

            @Override
            public void changeTypeFace(Typeface typeface) {
                pageFactory.changeTypeface(typeface);
            }

            @Override
            public void changeBookBg(int type) {
                pageFactory.changeBookBg(type);
            }
        });


    }


    public void initDayOrNight(){
        mDayOrNight = SPUtils.getInstance().getDayOrNight();
        if (mDayOrNight){
            tv_dayornight.setText(getResources().getString(R.string.read_setting_day));
        }else{
            tv_dayornight.setText(getResources().getString(R.string.read_setting_night));
        }
    }

    /**
     * 改变显示模式
     */
    public void changeDayOrNight(){
        if (mDayOrNight){
            mDayOrNight = false;
            tv_dayornight.setText(getResources().getString(R.string.read_setting_night));
        }else{
            mDayOrNight = true;
            tv_dayornight.setText(getResources().getString(R.string.read_setting_day));
        }
        SPUtils.getInstance().setDayOrNight(mDayOrNight);
        pageFactory.setDayOrNight(mDayOrNight);
    }


    /**
     * 显示Bar和Menu
     */
    private void showReadSetting(){
        isShow = true;

        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0.0f,
                Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,-1.0f,Animation.RELATIVE_TO_SELF,0.0f);
        translateAnimation.setDuration(DURATION_TIME);
        appbar.setAnimation(translateAnimation);
        appbar.setVisibility(View.VISIBLE);

        TranslateAnimation translateAnimation1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        translateAnimation1.setDuration(DURATION_TIME);
        rl_bottom.setAnimation(translateAnimation1);
        rl_bottom.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏Bar和Menu
     */
    private void hideReadSetting() {
        isShow = false;

        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0.0f,
                Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,-1.0f);
        translateAnimation.setDuration(DURATION_TIME);
        appbar.setAnimation(translateAnimation);
        appbar.setVisibility(View.INVISIBLE);

        TranslateAnimation translateAnimation1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        translateAnimation1.setDuration(DURATION_TIME);
        rl_bottom.setAnimation(translateAnimation1);
        rl_bottom.setVisibility(View.INVISIBLE);
    }


    /**
     * 接收电池信息更新的广播
     */
    private BroadcastReceiver myReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
                Log.e(TAG, Intent.ACTION_BATTERY_CHANGED);
                int level = intent.getIntExtra("level", 0);
                Log.i("level","level："+level);
                pageFactory.updateBattery(level);
            }else if (intent.getAction().equals(Intent.ACTION_TIME_TICK)){
                Log.e(TAG, Intent.ACTION_TIME_TICK);
                pageFactory.updateTime();
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_pagemode:
                hideReadSetting();
                mPageModeDialog.show();
                break;
            case R.id.tv_setting:
                hideReadSetting();
                mSettingDialog.show();
                break;
            case R.id.tv_pre:
                pageFactory.preChapter();
                break;
            case R.id.tv_next:
                pageFactory.nextChapter();
                break;
            case R.id.tv_dayornight:
                changeDayOrNight();
                break;
            case R.id.tv_directory:
                Intent intent = new Intent(mContext, MarkActivity.class);
                mContext.startActivity(intent);
                mFragment.getActivity().overridePendingTransition(R.anim.activity_open,R.anim.activity_stay);
                break;
            case R.id.title_bar_back:
                hideReadSetting();
                mFragment.closeView();
                break;
            case R.id.btn_more:
                XLog.i("btn_more----->>");
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    XLog.i("btn_more----fragmentManager->>");
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public void onMenuItemClick(View view, int i) {
        XLog.i("i------->>"+i);
        switch (i){
            case 0:
                hideReadSetting();
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;

        }
    }

    @Override
    public void onMenuItemLongClick(View view, int i) {

    }
}
