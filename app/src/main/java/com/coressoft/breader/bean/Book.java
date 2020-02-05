package com.coressoft.breader.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by xing.yw on 2017/6/15.
 */
@Entity
public class Book {
    @Property(nameInDb = "id")
    private int id;
    @Property(nameInDb = "name")
    private String name;
    @Property(nameInDb = "abbr")
    private String abbr;
    @Property(nameInDb = "nameEn")
    private String nameEn;
    @Property(nameInDb = "abbrEn")
    private String abbrEn;
    @Property(nameInDb = "bookChapterCount")
    private int bookChapterCount;
    @Generated(hash = 312773450)
    public Book(int id, String name, String abbr, String nameEn, String abbrEn,
            int bookChapterCount) {
        this.id = id;
        this.name = name;
        this.abbr = abbr;
        this.nameEn = nameEn;
        this.abbrEn = abbrEn;
        this.bookChapterCount = bookChapterCount;
    }
    @Generated(hash = 1839243756)
    public Book() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAbbr() {
        return this.abbr;
    }
    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }
    public String getNameEn() {
        return this.nameEn;
    }
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
    public String getAbbrEn() {
        return this.abbrEn;
    }
    public void setAbbrEn(String abbrEn) {
        this.abbrEn = abbrEn;
    }
    public int getBookChapterCount() {
        return this.bookChapterCount;
    }
    public void setBookChapterCount(int bookChapterCount) {
        this.bookChapterCount = bookChapterCount;
    }

}
