package com.coressoft.breader.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xing.yw on 2017/8/1.
 */
@Entity
public class ChapterName {
    private int id;
    private String chapter;
    private String chapterName;
    @Generated(hash = 668164600)
    public ChapterName(int id, String chapter, String chapterName) {
        this.id = id;
        this.chapter = chapter;
        this.chapterName = chapterName;
    }
    @Generated(hash = 2028464567)
    public ChapterName() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getChapter() {
        return this.chapter;
    }
    public void setChapter(String chapter) {
        this.chapter = chapter;
    }
    public String getChapterName() {
        return this.chapterName;
    }
    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

}
