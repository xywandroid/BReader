package com.coressoft.breader.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.coressoft.breader.Config;


/**
 * Created by xing.yw on 2017/8/1.
 */

public class GreenDaoManager {
    private static DaoMaster.OpenHelper mHelper;
    private static SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static GreenDaoManager mInstance;//单例
    private GreenDaoManager(){
        //TODO 需要加入异常判断，找不到数据库的时候需要提示用户。
        if(mInstance==null) {
            db = SQLiteDatabase.openDatabase(Config.BIBLE_DB, null, SQLiteDatabase.OPEN_READONLY);
            mDaoMaster = new DaoMaster(db);
            mDaoSession = mDaoMaster.newSession();
        }
    }
    public static GreenDaoManager getInstance() {
        if(mInstance==null) {
            synchronized(GreenDaoManager.class) {//保证异步处理安全操作
                if(mInstance==null) {
                    mInstance=new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }


    public void initNoteDatabase(Context context) {
        //创建数据库note.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "node.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        mDaoSession = daoMaster.newSession();
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }
    public DaoSession getSession() {
        return mDaoSession;
    }
    public DaoSession getNewSession() {
        mDaoSession=mDaoMaster.newSession();
        return mDaoSession;
    }
}
