package com.coressoft.breader;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;

/**
 * Created by xingyw on 18-1-17.
 */

public class Config {
    public final static String DB = "bible.db";
    public static final String BIBLE_DB = "/data/data/com.coressoft.breader/databases/bible.db";
    public static final String IMG = "/data/data/com.coressoft.breader/databases/default_avatar.png";
    public static final String KEY_BUTTON_VISIBILITY = "KEY_BUTTON_VISIBILITY";
    public static final String KEY_BUTTON_Y = "KEY_BUTTON_Y";
    private static Config config;
    private Context mContext;
    private SharedPreferences sp;

    private final static String SP_NAME = "config";


    public final static int PAGE_MODE_SIMULATION = 0;
    public final static int BOOK_BG_DEFAULT = 0;

    public final static String FONTTYPE_WAWA = "font/font1.ttf";

    public final static String FONTTYPE_FZXINGHEI = "font/fzxinghei.ttf";
    public final static String FONTTYPE_FZKATONG = "font/fzkatong.ttf";
    public final static String FONTTYPE_BYSONG = "font/bysong.ttf";

    public final static String FONTTYPE_DEFAULT = "";
    public final static String FONTTYPE_QIHEI = "font/qihei.ttf";

    public final static int BOOK_BG_1 = 1;
    public final static int BOOK_BG_2 = 2;
    public final static int BOOK_BG_3 = 3;
    public final static int BOOK_BG_4 = 4;


    public final static int PAGE_MODE_COVER = 1;
    public final static int PAGE_MODE_SLIDE = 2;
    public final static int PAGE_MODE_NONE = 3;





//    private Config(Context mContext){
//        this.mContext = mContext.getApplicationContext();
//        sp = this.mContext.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
//    }
//    //TODO 之后的sp需要放到SPUitil中
//    public static synchronized Config createConfig(Context context){
//        if (config == null){
//            config = new Config(context);
//        }
//
//        return config;
//    }
//    public static synchronized Config getInstance(){
//        return config;
//    }























}
