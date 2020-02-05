package com.coressoft.breader.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {


	private final static int VERSION = 1;
	private final static String DB_NAME = "bible.db";
	private final static String TABLE_NAME_BOOK = "book";
	private final static String TABLE_NAME_VERSE = "verse";

	private SQLiteDatabase db;

	private static ArrayList<String> a;
	private static String string;


	/**
	 * 创建数据库的方法，这里不需要进行创建数据库，所以为空方法
	 * @param db
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {


	}

	/**
	 * 更新数据库版本的方法
	 * @param db 数据库
	 * @param oldVersion 老版本号
	 * @param newVersion 新版本号
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	/**
	 * SQLiteOpenHelper子类必须要的一个构造函数
	 * @param context
	 * @param name
	 * @param factory
     * @param version
     */
	public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
		//必须通过super 调用父类的构造函数
		super(context, name, factory, version);
	}

	/**
	 * 数据库的构造函数，传递三个参数的
	 * @param context
	 * @param name
	 * @param version
     */
	public DBHelper(Context context, String name, int version){
		this(context, name, null, version);
	}


	/**
	 * 数据库的构造函数，传递一个参数的， 数据库名字和版本号都写死了
	 * @param context
     */
	public DBHelper(Context context){

		this(context, DB_NAME, null, VERSION);
//		this.db = SQLiteDatabase.openDatabase(Config.BIBLE_DB, null, SQLiteDatabase.OPEN_READONLY);
	}


	/**
	 * 通过Book 的id Verse 的chapter 进行查询Cursor
	 * @param book_id  book的ID
	 * @param verse_chapter verse的chapter
     * @return Cursor
     */
	public Cursor getContent(int book_id, int verse_chapter){
//		db = getWritableDatabase();
//		Cursor cursor = db
//				.rawQuery(
//						"select * from verse where book = (select id from book where id = ?) and chapter = ?",
//						new String[]{String.valueOf(book_id), String.valueOf(verse_chapter)});
		return null;
	}
	
	
	/**
	 * 查询所有书中章节数
	 * @return 这本书所有的章节集合
	 */
	public static ArrayList getZhangJie(){
//		ArrayList<String> a1 = new ArrayList<String>();
//		SQLiteDatabase db = SQLiteDatabase.openDatabase(Config.BIBLE_DB, null, SQLiteDatabase.OPEN_READONLY);
//		Cursor cursor = db
//				.rawQuery("select * from book",null);
//		while (cursor.moveToNext()) {
//			a1.add(cursor.getString(5));
//		}
//		cursor.close();
//		db.close();
		return null;
	}
	
	
	public static ArrayList<String> getItemContext(String book, String chapter){
//		ArrayList<String > lcontext = new ArrayList<String>();
//		SQLiteDatabase db = SQLiteDatabase.openDatabase(Config.BIBLE_DB, null, SQLiteDatabase.OPEN_READONLY);
//		Cursor cursor = db
//				.rawQuery("select con  from verse where book = ? and chapter = ?",new String[] {book,chapter});
//		while (cursor.moveToNext()) {
//			String string = cursor.getString(0);
//
//			lcontext.add(cursor.getString(0));
//		}
//		cursor.close();
		return null;
		
	}
	public static String getItemContextCount(String count){
//		ArrayList<String> lcontext = new ArrayList<String>();
//		SQLiteDatabase db = SQLiteDatabase.openDatabase(Config.BIBLE_DB, null, SQLiteDatabase.OPEN_READONLY);
//		Cursor cursor = db
//				.rawQuery("select bookchaptercount from book where id = ?",new String[] {count});
//		while (cursor.moveToNext()) {
//			string = cursor.getString(0);
//		}
//		cursor.close();
		return null;
		
	}
	
	
	/**
	 * 根据书的Id来查询书中的章节数
	 * @param count 书的id
	 * @return 书中的章节数
	 */
	public static String getBookChapterCount(String count){
//		SQLiteDatabase db = SQLiteDatabase.openDatabase(Config.BIBLE_DB, null, SQLiteDatabase.OPEN_READONLY);
//		Cursor cursor = db
//				.rawQuery("select bookchaptercount from book where id = ?",new String[] {count});
//		while (cursor.moveToNext()) {
//			return cursor.getString(0);
//		}
//		cursor.close();
//		db.close();
		return null;
	}
	
	
	public static String getItemBookChapterCount(String count){
//		SQLiteDatabase db = SQLiteDatabase.openDatabase(Config.BIBLE_DB, null, SQLiteDatabase.OPEN_READONLY);
//		Cursor cursor = db
//				.rawQuery("select bookchaptercount from book where id = ?",new String[] {count});
//		while (cursor.moveToNext()) {
//			return cursor.getString(0);
//		}
//		cursor.close();
//		db.close();
		return null;
	}
	
	public static String getBookChapterCountss(String name){
//		SQLiteDatabase db = SQLiteDatabase.openDatabase(Config.BIBLE_DB, null, SQLiteDatabase.OPEN_READONLY);
//		Cursor cursor = db
//				.rawQuery("select bookchaptercount from book where name = ?",new String[] {name});
//		while (cursor.moveToNext()) {
//			return cursor.getString(0);
//		}
//		cursor.close();
//		db.close();
		return null;
	}
	
	public static Cursor getBookChapterCounts(String count){
//		SQLiteDatabase db = SQLiteDatabase.openDatabase(Config.BIBLE_DB, null, SQLiteDatabase.OPEN_READONLY);
//		Cursor cursor = db
//				.rawQuery("select bookchaptercount as _id  from book where id = ?",new String[] {count});
		return null;
	}
	
	//select bookchaptercount from book where abbr= "锟斤拷"
	/**
	 * 根据书的简称来查询书中的章节数
	 * @param name 书的简称
	 * @return  返回的是章节数
	 */
	public static String getBookChapterCountForName(String name){
//		SQLiteDatabase db = SQLiteDatabase.openDatabase(Config.BIBLE_DB, null, SQLiteDatabase.OPEN_READONLY);
//		Cursor cursor = db
//				.rawQuery("select bookchaptercount from book where abbr= ?",new String[] {name});
//		while (cursor.moveToNext()) {
//			return cursor.getString(0);
//		}
//		cursor.close();
//		db.close();
		return null;
	}


    /**
	 * 查询旧约中所有书名的简称和全称
	 * @return cursor
     */
	public Cursor getBibleOldBook(){
		Cursor cursor = db.rawQuery("select * from book where id between 1 and 39",null);
		return cursor;
	}

	/**
	 * 查询新约中所有书名的简称和全称
	 * @return
     */
	public Cursor getBibleNewBook(){
		Cursor cursor = db.rawQuery("select * from book where id between 40 and 66",null);
		return cursor;
	}

	/**
	 * 通过book的id来获取bookid中的全部内容
	 * @param book book 的 id
	 * @param chapter 章节数
	 * @return
	 */
	public Cursor getBibleOldContent(int book, int chapter){
//		Log.i("xing.yw","DBHelper is getBibleOldContent");
//		Cursor cursor = db.rawQuery("select chapter,verse,con from verse where book=? and chapter = ?",new String[] {Integer.toString(book), Integer.toString(chapter)});
		return null;
	}

	public Cursor getChapterName(int positon){
//		Cursor cursor = db.rawQuery("select chapter,verse,con from verse where book=? and verse = 0",new String[] {Integer.toString(positon)});
		return null;
	}
	
//	/**
//	 * 查询新约中所有书名的简称和全称
//	 * @return 书名的简称和全称
//	 */
//	public static ArrayList<Map<String,String>> getNameNew(){
//		ArrayList<Map<String,String>> a3 = new ArrayList<Map<String,String>>();
//		SQLiteDatabase db = SQLiteDatabase.openDatabase(Contents.BIBLE_DB, null,SQLiteDatabase.OPEN_READONLY);
//
//		while (cursor.moveToNext()) {
//			Map<String,String > map = new HashMap<String, String>();
//			map.put("short", cursor.getString(2));
//			map.put("long", cursor.getString(1));
//			a3.add(map);
//		}
//		cursor.close();
//		db.close();
//		return a3;
//	}
	
	/**
	 * 查询搜索用户想要的数据，
	 * @return
	 */
	public static ArrayList<String> getSreach(String book1, String book2, String Onlykey){
//		ArrayList<String> sreachKey = new ArrayList<String>();
//		SQLiteDatabase db = SQLiteDatabase.openDatabase(Config.BIBLE_DB, null, SQLiteDatabase.OPEN_READONLY);
//		Cursor cursor = db
//				.rawQuery(
//						"select con,book  from verse where   (book between ? and  ?) and con like ?",new String[]{book1,book2,Onlykey+"%"});
//		while (cursor.moveToNext()) {
//			sreachKey.add(cursor.getString(0));
//		}
//		cursor.close();
//		db.close();
		return null;
	}



}
