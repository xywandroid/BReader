package com.coressoft.breader.ui.view;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

/**
 * Created by lgp on 2015/8/2.
 */
public class NewNoteButton extends FloatingActionButton {
    private boolean forceHide = false;
    public NewNoteButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewNoteButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NewNoteButton(Context context) {
        super(context);
    }

    public boolean isForceHide() {
        return forceHide;
    }

    public void setForceHide(boolean forceHide) {
        this.forceHide = forceHide;
        if (!forceHide) {
            setVisibility(VISIBLE);
        }else {
            setVisibility(GONE);
        }
    }

    //if hide，disable animation
    public boolean canAnimation(){
        return !isForceHide();
    }
}
