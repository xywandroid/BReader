package com.coressoft.breader.utils;

import android.content.ContentValues;
import android.content.Context;
import android.os.Environment;
import android.util.Log;


import com.coressoft.breader.bean.Cache;
import com.coressoft.breader.bean.Verse;
import com.coressoft.breader.db.DBManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class BookUtil {
    private static final String cachedPath = Environment.getExternalStorageDirectory() + "/bible/";
    //存储的字符数
    public static final int cachedSize = 30000;
//    protected final ArrayList<WeakReference<char[]>> myArray = new ArrayList<>();

    protected final ArrayList<Cache> myArray = new ArrayList<>();
    //目录
    private List<Verse> directoryList = new ArrayList<>();

    private String m_strCharsetName;
    private String bookName;
//    private String bookPath;
    private long bookLen;
    private long position;
//    private BookList bookList;



    public BookUtil(){
        File file = new File(cachedPath);
        if (!file.exists()){
            file.mkdir();
        }
    }

    List<Verse> mBookCatalogue;
    File file;
    public synchronized void openBook(int bookNum,int chapterNum, Context mContext) throws IOException {
        DBManager dbManager = DBManager.getInstance(mContext);
        file = dbManager.getBibleOldContent(bookNum,chapterNum);
//        this.bookList = bookList;
        //如果当前缓存不是要打开的书本就缓存书本同时删除缓存

//        if (bookPath == null || !bookPath.equals(bookList.getBookpath())) {
//            cleanCacheFile();
//            this.bookPath = bookList.getBookpath();
//            bookName = FileUtils.getFileName(bookPath);
            cacheBook(bookNum,mContext);
//        }
    }


    private void cleanCacheFile(){
        File file = new File(cachedPath);
        if (!file.exists()){
            file.mkdir();
        }else{
            File[] files = file.listFiles();
            for (int i = 0; i < files.length;i++){
                files[i].delete();
            }
        }
    }

    public int next(boolean back){
        position += 1;
        if (position > bookLen){
            position = bookLen;
            return -1;
        }
        char result = current();
        if (back) {
            position -= 1;
        }
        return result;
    }

    public char[] nextLine(){
        if (position >= bookLen){
            return null;
        }
        String line = "";
        while (position < bookLen){
            int word = next(false);
            if (word == -1){
                break;
            }
            char wordChar = (char) word;
            if ((wordChar + "").equals("\r") && (((char)next(true)) + "").equals("\n")){
                next(false);
                break;
            }
            line += wordChar;
        }
        return line.toCharArray();
    }

    public char[] preLine(){
        if (position <= 0){
            return null;
        }
        String line = "";
        while (position >= 0){
            int word = pre(false);
            if (word == -1){
                break;
            }
            char wordChar = (char) word;
            if ((wordChar + "").equals("\n") && (((char)pre(true)) + "").equals("\r")){
                pre(false);
//                line = "\r\n" + line;
                break;
            }
            line = wordChar + line;
        }
        return line.toCharArray();
    }

    public char current(){
//        int pos = (int) (position % cachedSize);
//        int cachePos = (int) (position / cachedSize);
        int cachePos = 0;
        int pos = 0;
        int len = 0;
        for (int i = 0;i < myArray.size();i++){
            long size = myArray.get(i).getSize();
            if (size + len - 1 >= position){
                cachePos = i;
                pos = (int) (position - len);
                break;
            }
            len += size;
        }

        if(len ==  myArray.get(0).getSize()){
            return " ".charAt(0);
        }else{
            char[] charArray = block(cachePos);
            return charArray[pos];
        }



    }

    public int pre(boolean back){
        position -= 1;
        if (position < 0){
            position = 0;
            return -1;
        }
        char result = current();
        if (back) {
            position += 1;
        }
        return result;
    }

    public long getPosition(){
        return position;
    }

    public void setPostition(long position){
        this.position = position;
    }

    //TODO 暂时用字符串表示，真正的时候是使用数据库进行查询
//    String str = "暂时用字符串表示，真正的时候是使用数据库进行查询.暂时用字符串表示，真正的时候是使用数据库进行查询.暂时用字符串表示，真正的时候是使用数据库进行查询.暂时用字符串表示，真正的时候是使用数据库进行查询.暂时用字符串表示，真正的时候是使用数据库进行查询.暂时用字符串表示，真正的时候是使用数据库进行查询.暂时用字符串表示，真正的时候是使用数据库进行查询.暂时用字符串表示，真正的时候是使用数据库进行查询.暂时用字符串表示，真正的时候是使用数据库进行查询.暂时用字符串表示，真正的时候是使用数据库进行查询.暂时用字符串表示，真正的时候是使用数据库进行查询.123";
    //缓存书本

//    private String getContentStr(){
//        String conStr = null;
//        for(int i = 0;i<mBookCatalogue.size();i++){
//            conStr += mBookCatalogue.get(i).getVersr()+" 、"+mBookCatalogue.get(i).getCon();
//        }
//       return  conStr;
//    }


    private void cacheBook(final int position, final Context mContext) throws IOException {
//        if (TextUtils.isEmpty(bookList.getCharset())) {
//            m_strCharsetName = FileUtils.getCharset(bookPath);
            if (m_strCharsetName == null) {
                m_strCharsetName = "utf-8";
            }
            ContentValues values = new ContentValues();
            values.put("charset",m_strCharsetName);
//        }else{
//            m_strCharsetName = bookList.getCharset();
//        }
        //TODO 查询到当前的内容字符串
//        String str = getContentStr();
//        String str = "查询到当前的内容字符串123456";
//        InputStream in_withcode   =   new ByteArrayInputStream(str.getBytes());
//        InputStreamReader reader = new InputStreamReader(in_withcode);
//        File file = new File(Environment.getExternalStorageDirectory()+"/temp.txt");
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file),m_strCharsetName);
        int index = 0;
        bookLen = 0;
        directoryList.clear();
        myArray.clear();
        while (true){
            char[] buf = new char[cachedSize];
            int result = reader.read(buf);
            if (result == -1){
                reader.close();
                break;
            }

            String bufStr = new String(buf);
//            bufStr = bufStr.replaceAll("\r\n","\r\n\u3000\u3000");
//            bufStr = bufStr.replaceAll("\u3000\u3000+[ ]*","\u3000\u3000");
            bufStr = bufStr.replaceAll("\r\n+\\s*","\r\n\u3000\u3000");
//            bufStr = bufStr.replaceAll("\r\n[ {0,}]","\r\n\u3000\u3000");
//            bufStr = bufStr.replaceAll(" ","");
            bufStr = bufStr.replaceAll("\u0000","");
            buf = bufStr.toCharArray();
            bookLen += buf.length;

            Cache cache = new Cache();
            cache.setSize(buf.length);
            cache.setData(new WeakReference<char[]>(buf));

//            bookLen += result;
            myArray.add(cache);
//            myArray.add(new WeakReference<char[]>(buf));
//            myArray.set(index,);
            try {
                File cacheBook = new File(fileName(index));
                if (!cacheBook.exists()){
                    cacheBook.createNewFile();
                }
                final OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName(index)), "UTF-16LE");
                writer.write(buf);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException("Error during writing " + fileName(index));
            }
            index ++;
        }

        new Thread(){
            @Override
            public void run() {
                getChapter(position,0,mContext);
            }
        }.start();
    }

    //获取章节
//    public synchronized void getChapter(){
//        try {
//            long size = 0;
//            for (int i = 0; i < myArray.size(); i++) {
//                char[] buf = block(i);
//                String bufStr = new String(buf);
//                String[] paragraphs = bufStr.split("\r\n");
//                for (String str : paragraphs) {
//                    if (str.length() <= 30 && (str.matches(".*第.{1,8}章.*") || str.matches(".*第.{1,8}节.*"))) {
//                        BookCatalogue bookCatalogue = new BookCatalogue();
//                        bookCatalogue.setBookCatalogueStartPos(size);
////                        bookCatalogue.setBookCatalogue(str);
////                        bookCatalogue.setBookpath(bookPath);
//                        directoryList.add(bookCatalogue);
//                    }
//                    if (str.contains("\u3000\u3000")) {
//                        size += str.length() + 2;
//                    }else if (str.contains("\u3000")){
//                        size += str.length() + 1;
//                    }else {
//                        size += str.length();
//                    }
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public synchronized List<Verse> getChapter(int book, int verse, Context mContext){
        Log.i("xing.yw","position----->>"+position);
        DBManager dbManager = DBManager.getInstance(mContext);
        directoryList = dbManager.getChapterName(book,verse);
        return directoryList;
    }

    public List<Verse> getDirectoryList(){
        return directoryList;
    }

    public long getBookLen(){
        return bookLen;
    }

    protected String fileName(int index) {
        return cachedPath + bookName + index ;
    }

    //获取书本缓存
    public char[] block(int index) {
        if (myArray.size() == 0){
            return new char[1];
        }
        char[] block = myArray.get(index).getData().get();
        if (block == null) {
            try {
                File file = new File(fileName(index));
                int size = (int)file.length();
                if (size < 0) {
                    throw new RuntimeException("Error during reading " + fileName(index));
                }
                block = new char[size / 2];
                InputStreamReader reader =
                        new InputStreamReader(
                                new FileInputStream(file),
                                "UTF-16LE"
                        );
                if (reader.read(block) != block.length) {
                    throw new RuntimeException("Error during reading " + fileName(index));
                }
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException("Error during reading " + fileName(index));
            }
            Cache cache = myArray.get(index);
            cache.setData(new WeakReference<char[]>(block));
//            myArray.set(index, new WeakReference<char[]>(block));
        }
        return block;
    }

}
