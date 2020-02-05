package com.coressoft.breader.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by xing.yw on 2017/8/1.
 */
@Entity
public class Verse  {
    @Property(nameInDb = "id")
    private int id;
    @Property(nameInDb = "book")
    private int book;
    @Property(nameInDb = "chapter")
    private int chapter;
    @Property(nameInDb = "verse")
    private int verse;
    @Property(nameInDb = "con")
    private String con;
    @Property(nameInDb = "conEn")
    private String conEn;
    @Generated(hash = 1375604173)
    public Verse(int id, int book, int chapter, int verse, String con,
            String conEn) {
        this.id = id;
        this.book = book;
        this.chapter = chapter;
        this.verse = verse;
        this.con = con;
        this.conEn = conEn;
    }
    @Generated(hash = 1505125539)
    public Verse() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
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
    public String getCon() {
        return this.con;
    }
    public void setCon(String con) {
        this.con = con;
    }
    public String getConEn() {
        return this.conEn;
    }
    public void setConEn(String conEn) {
        this.conEn = conEn;
    }


}
