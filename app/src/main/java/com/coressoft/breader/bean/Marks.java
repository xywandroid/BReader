package com.coressoft.breader.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.litepal.crud.DataSupport;

/**
 * Created by xing.yw on 2017/8/1.
 */
@Entity
public class Marks extends DataSupport {
    private int id ;
    private long begin;
    private String text;
    private String time;
    private int book;
    private int chapter;
    private int verse;
    @Generated(hash = 1288742278)
    public Marks(int id, long begin, String text, String time, int book,
            int chapter, int verse) {
        this.id = id;
        this.begin = begin;
        this.text = text;
        this.time = time;
        this.book = book;
        this.chapter = chapter;
        this.verse = verse;
    }
    @Generated(hash = 662280675)
    public Marks() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public long getBegin() {
        return this.begin;
    }
    public void setBegin(long begin) {
        this.begin = begin;
    }
    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public int getBook() {
        return this.book;
    }
    public void setBook(int book) {
        this.book = book;
    }
    public int getChapter() {
        return this.chapter;
    }
    public void setChapter(int chapter) {
        this.chapter = chapter;
    }
    public int getVerse() {
        return this.verse;
    }
    public void setVerse(int verse) {
        this.verse = verse;
    }


}
