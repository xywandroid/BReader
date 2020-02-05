package com.coressoft.breader.db;

import android.content.Context;


import com.coressoft.breader.bean.Book;
import com.coressoft.breader.bean.Note;
import com.coressoft.breader.bean.Verse;
import com.coressoft.breader.utils.TxtUtil;
import com.coressoft.breader.utils.XLog;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by xingyanwei on 16/7/14.
 */
public class DBManager {
    public List<Book> mBibleOldBookNameList;
    public List<Book> mBibleNewBookNameList;

    public List<Verse> mBibleContentList;
    public List<Verse> mBibleChapterNameList;

    private static Context mContext;

    private DBManager() {}
    private static DBManager single=null;
    //静态工厂方法
    public static DBManager getInstance(Context context) {
        mContext = context;
        if (single == null) {
            single = new DBManager();
        }
        return single;
    }

    /**
     * 通过Book 的id Verse 的chapter 进行查询内容
     * @param book_id book的ID
     * @param verse_chapter verse的chapter
     * @return BibleContent list数组
     */
//    public List<Verse> queryBibleContentListData(int book_id, int verse_chapter){
//        if(null == mBibleContentlist) {
//            mBibleContentlist = new ArrayList<Verse>();
//        }
//        Cursor cursor = dbHelper.getContent(book_id, verse_chapter);
//
//        while (cursor.moveToNext()) {
//
//            int id = cursor.getInt(cursor.getColumnIndex("id"));
//            int book = cursor.getInt(cursor.getColumnIndex("book"));
//            int chapter = cursor.getInt(cursor.getColumnIndex("chapter"));
//            int verse = cursor.getInt(cursor.getColumnIndex("verse"));
//            String con = cursor.getString(cursor.getColumnIndex("con"));
//            String conEn = cursor.getString(cursor.getColumnIndex("conEn"));
////
////            BookCatalogue mBibleConent = new BookCatalogue();
////            mBibleConent.setId(id);
////            mBibleConent.setBookNum(book);
////            mBibleConent.setBookCatalogue(chapter);
////            mBibleConent.setVersr(verse);
////            mBibleConent.setCon(con);
////            mBibleConent.setConEn(conEn);
////            mBibleContentlist.add(mBibleConent);
//        }
//        return mBibleContentlist;
//    }

    /**
     * 旧约名称
     * @return
     */
    public List<Book> getOldBookName(){
        if(null == mBibleOldBookNameList) {
            mBibleOldBookNameList = new ArrayList<Book>();
        }else{
            mBibleOldBookNameList.clear();
        }
        BookDao mBookDao = GreenDaoManager.getInstance().getSession().getBookDao();
        QueryBuilder qb = mBookDao.queryBuilder();

        qb.where(BookDao.Properties.Id.between(1,39));
        mBibleOldBookNameList = qb.build().list();
        return mBibleOldBookNameList;
    }

    public void insertData(Note note) {
//        GreenDaoManager.getInstance().initNoteDatabase(mContext);
        NoteDao mNoteDao = GreenDaoManager.getInstance().getSession().getNoteDao();
        mNoteDao.insert(note);
    }

    public List<Note> getNoteData() {
        GreenDaoManager.getInstance().initNoteDatabase(mContext);
        NoteDao noteDao = GreenDaoManager.getInstance().getSession().getNoteDao();
        return noteDao.queryBuilder()

                .build()
                .list();
    }

    /**
     * 新约名称
     * @return
     */
    public List<Book> getNewBookName(){
        if(null == mBibleNewBookNameList){
            mBibleNewBookNameList = new ArrayList<Book>();
        }else{
            mBibleNewBookNameList.clear();
        }
        BookDao mBookDao = GreenDaoManager.getInstance().getSession().getBookDao();
        QueryBuilder qb = mBookDao.queryBuilder();

        qb.where(BookDao.Properties.Id.between(40,66));
        mBibleNewBookNameList = qb.build().list();
        return mBibleNewBookNameList;
    }

    public File getBibleOldContent(int book, int chapter){
        if(null == mBibleContentList){
            mBibleContentList = new ArrayList<Verse>();
        }else{
            mBibleContentList.clear();
        }
        String mContentStr = "";
        VerseDao mVerseDao = GreenDaoManager.getInstance().getSession().getVerseDao();
        QueryBuilder qb = mVerseDao.queryBuilder();
        qb.where(VerseDao.Properties.Book.eq(book),VerseDao.Properties.Chapter.eq(chapter));
        mBibleContentList = qb.build().list();
        for(int i = 1;i<mBibleContentList.size();i++){

            mContentStr += mBibleContentList.get(i).getVerse()+" "+mBibleContentList.get(i).getCon()+" ".trim();

            XLog.i("mContentStr----->>"+mContentStr);
        }
        return TxtUtil.writeTxtFile(mContentStr);
    }

    public List<Verse> getChapterName(int book, int verse){
        if(null == mBibleChapterNameList){
            mBibleChapterNameList = new ArrayList<Verse>();
        }else{
            mBibleChapterNameList.clear();
        }
        VerseDao mVerseDao = GreenDaoManager.getInstance().getSession().getVerseDao();
        QueryBuilder qb = mVerseDao.queryBuilder();
        qb.where(VerseDao.Properties.Book.eq(book),VerseDao.Properties.Verse.eq(verse));
        mBibleChapterNameList = qb.build().list();
        return mBibleChapterNameList;
    }






//    public ArrayList<ChapterName> queryChapterName(int position){
//        if(null == mChapterNameList) {
//            mChapterNameList = new ArrayList<ChapterName>();
//        }
//        Cursor mChapterCursor = dbHelper.getChapterName(position);
//
//        while (mChapterCursor.moveToNext()) {
//            String chapter = mChapterCursor.getString(mChapterCursor.getColumnIndex("chapter"));
//            String verse = mChapterCursor.getString(mChapterCursor.getColumnIndex("verse"));
//            String con = mChapterCursor.getString(mChapterCursor.getColumnIndex("con"));
//
//            ChapterName mChapterName = new ChapterName();
//            mChapterName.setId(Integer.parseInt(chapter));
//            mChapterName.setChapter(verse);
//            mChapterName.setChapterName(con);
//
//            mChapterNameList.add(mChapterName);
//        }
//        return mChapterNameList;
//    }


}
