package com.coressoft.breader.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by xingyw on 18-3-13.
 */
@Entity
public class Note {
    
    private String time;
    private String title;
    private String relevant_content;
    private String content;
    private String name;
    private String chapter;
    private String section;
    private String label;
    private long lastOprTime;
    @Generated(hash = 381995465)
    public Note(String time, String title, String relevant_content, String content,
            String name, String chapter, String section, String label,
            long lastOprTime) {
        this.time = time;
        this.title = title;
        this.relevant_content = relevant_content;
        this.content = content;
        this.name = name;
        this.chapter = chapter;
        this.section = section;
        this.label = label;
        this.lastOprTime = lastOprTime;
    }
    @Generated(hash = 1272611929)
    public Note() {
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getRelevant_content() {
        return this.relevant_content;
    }
    public void setRelevant_content(String relevant_content) {
        this.relevant_content = relevant_content;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getChapter() {
        return this.chapter;
    }
    public void setChapter(String chapter) {
        this.chapter = chapter;
    }
    public String getSection() {
        return this.section;
    }
    public void setSection(String section) {
        this.section = section;
    }
    public String getLabel() {
        return this.label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public long getLastOprTime() {
        return this.lastOprTime;
    }
    public void setLastOprTime(long lastOprTime) {
        this.lastOprTime = lastOprTime;
    }

   


}
