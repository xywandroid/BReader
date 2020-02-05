package com.coressoft.breader.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;

import com.coressoft.breader.Config;
import com.coressoft.breader.R;

/**
 * Created by ximsfei on 17-1-8.
 */

public class SPUtils {
    private static final String FILE_NAME = "meta-data";

    private static final String KEY_MODE_NIGHT = "mode-night";
    private static final String KEY_THEME_MODE = "mode_theme";
    private static final String KEY_CUR_SKIN = "cur-skin";
    private final static String PAGE_MODE_KEY = "page-mode";
    private final static String SYSTEM_LIGHT_KEY = "system-light";
    private final static String FONT_SIZE_KEY = "font-size";
    private final static String LIGHT_KEY = "light";
    private final static String BOOK_BG_KEY = "book-bg";
    private final static String FONT_TYPE_KEY = "font-type";
    private final static String NIGHT_KEY = "night";




    private static SPUtils sInstance;
    private final Context mApp;
    private final SharedPreferences mPref;
    private final SharedPreferences.Editor mEditor;
    /**
     * 阅读亮度
     */
    private float light = 0;
    /**
     * 阅读字体大小
     */
    private float mFontSize = 0;
    /**
     * 字体类型
     */
    private Typeface typeface;

    /**
     * 初始化
     *
     * @param context
     */
    public static void init(Context context) {
        if (sInstance == null) {
            synchronized (SPUtils.class) {
                if (sInstance == null) {
                    sInstance = new SPUtils(context.getApplicationContext());
                }
            }
        }
    }

    public static SPUtils getInstance() {
        return sInstance;
    }

    private SPUtils(Context applicationContext) {
        mApp = applicationContext;
        mPref = mApp.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    /**
     * 设置夜晚模式
     *
     * @param nightMode
     * @return
     */
    public SPUtils setNightMode(boolean nightMode) {
        mEditor.putBoolean(KEY_MODE_NIGHT, nightMode);
        return this;
    }

    /**
     * 获取夜晚模式
     *
     * @return
     */
    public boolean getNightMode() {
        return mPref.getBoolean(KEY_MODE_NIGHT, false);
    }

    /**
     * 设置主题模式
     *
     * @param color 颜色值
     * @return
     */
    public SPUtils setThemeMode(int color) {
        mEditor.putInt(KEY_THEME_MODE, color);
        return this;
    }

    /**
     * 获取主题模式
     *
     * @return 主题模式
     */
    public int getThemeMode() {
        return mPref.getInt(KEY_THEME_MODE, 0);
    }

    /**
     * 设置当前的主题
     *
     * @param curSkinName 皮肤名字
     * @return
     */
    public SPUtils setCurSkin(String curSkinName) {
        mEditor.putString(KEY_CUR_SKIN, curSkinName);
        return this;
    }

    /**
     * 获取当前的主题
     *
     * @return 皮肤模式
     */
    public String getCurSkin() {
        return mPref.getString(KEY_CUR_SKIN, "");
    }

    /**
     * 设置翻页模式
     *
     * @param pageMode 翻页模式
     */
    public void setPageMode(int pageMode) {
        mEditor.putInt(PAGE_MODE_KEY, pageMode).commit();
    }

    /**
     * 获取翻页模式
     *
     * @return 翻页模式
     */
    public int getPageMode() {
        return mPref.getInt(PAGE_MODE_KEY, Config.PAGE_MODE_SIMULATION);
    }

    /**
     * 设置系统的亮度
     *
     * @param isSystemLight 是否是系统亮度
     */
    public void setSystemLight(Boolean isSystemLight) {
        mEditor.putBoolean(SYSTEM_LIGHT_KEY, isSystemLight).commit();
    }

    /**
     * 获取系统的亮度
     *
     * @return 是否是系统亮度
     */
    public Boolean isSystemLight() {
        return mPref.getBoolean(SYSTEM_LIGHT_KEY, true);
    }

    /**
     * 设置阅读亮度
     *
     * @param light 亮度值
     */
    public void setLight(float light) {
        this.light = light;
        mEditor.putFloat(LIGHT_KEY, light).commit();
    }

    /**
     * 获取阅读亮度
     *
     * @return 亮度值
     */
    public float getLight() {
        if (light == 0) {
            light = mPref.getFloat(LIGHT_KEY, 0.1f);
        }
        return light;
    }

    /**
     * 设置字体的大小
     * @param fontSize 字号
     */
    public void setFontSize(float fontSize) {
        mFontSize = fontSize;
        mEditor.putFloat(FONT_SIZE_KEY, fontSize).commit();
    }

    /**
     * 获取当前阅读的字体大小
     * @param context 上下文
     * @return 字体大小
     */
    public float getFontSize(Context context) {
        if (mFontSize == 0) {
            mFontSize = mPref.getFloat(FONT_SIZE_KEY, context.getResources().getDimension(R.dimen.reading_default_text_size));
        }
        return mFontSize;
    }

    /**
     * 设置阅读背景
     *
     * @param type 背景类型
     */
    public void setBookBg(int type) {
        mEditor.putInt(BOOK_BG_KEY, type).commit();
    }

    /**
     * 获取阅读背景
     *
     * @return 背景的资源id
     */
    public int getBookBgType() {
        return mPref.getInt(BOOK_BG_KEY, Config.BOOK_BG_DEFAULT);
    }

    /**
     * 设置字体的类型
     *
     * @param contenxt 上下文
     * @param typefacePath 本地字体路径
     */
    public void setTypeface(Context contenxt, String typefacePath) {
        typeface = getTypeface(contenxt, typefacePath);
        mEditor.putString(FONT_TYPE_KEY, typefacePath).commit();
    }

    /**
     * 通过配置文件获取字体类型
     *
     * @return 获取当前配置文件中的字体类型
     */
    public Typeface getTypeface(Context context) {
        if (typeface == null) {
            String typePath = mPref.getString(FONT_TYPE_KEY, Config.FONTTYPE_QIHEI);
            typeface = getTypeface(context, typePath);
        }
        return typeface;
    }

    /**
     * 通过本地路径来获取字体类型
     *
     * @param contenxt 上下文
     * @param typeFacePath 字体的路径
     * @return 字体类型
     */
    public Typeface getTypeface(Context contenxt, String typeFacePath) {
        Typeface mTypeface;
        if (typeFacePath.equals(Config.FONTTYPE_DEFAULT)) {
            mTypeface = Typeface.DEFAULT;
        } else {
            mTypeface = Typeface.createFromAsset(contenxt.getAssets(), typeFacePath);
        }
        return mTypeface;
    }


    /**
     * 获取字体类的路径
     *
     * @return 本地字体路径
     */
    public String getTypefacePath() {
        String path = mPref.getString(FONT_TYPE_KEY, Config.FONTTYPE_QIHEI);
        return path;
    }

    /**
     * 设置阅读白黑模式
     * @param isNight
     */
    public void setDayOrNight(boolean isNight){
        mEditor.putBoolean(NIGHT_KEY,isNight).commit();
    }

    /**
     * 获取夜间还是白天阅读模式,
     * @return true为夜晚，false为白天
     */
    public boolean getDayOrNight() {
        return mPref.getBoolean(NIGHT_KEY, false);
    }

    public void commitEditor() {
        mEditor.apply();
    }


}