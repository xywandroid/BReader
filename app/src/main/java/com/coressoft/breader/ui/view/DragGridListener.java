package com.coressoft.breader.ui.view;

/**
 * Created by Lxq on 2016/4/12.
 */
public interface DragGridListener {
    /**
     * 重新排列数据
     * @param oldPosition
     * @param newPosition
     */
    public void reorderItems(int oldPosition, int newPosition);


    /**
     * 设置某个item隐藏
     * @param hidePosition
     */
    public void setHideItem(int hidePosition);



}
