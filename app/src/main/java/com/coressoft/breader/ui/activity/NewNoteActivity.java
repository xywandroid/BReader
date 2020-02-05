package com.coressoft.breader.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.coressoft.breader.R;
import com.coressoft.breader.base.BaseActivity;
import com.coressoft.breader.bean.Note;
import com.coressoft.breader.db.DBManager;

import butterknife.BindView;


/**
 * Created by xingyw on 18-3-13.
 */

public class NewNoteActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.tool_bar_note)
    Toolbar tool_bar_note;
    @BindView(R.id.ok_btn)
    TextView ok_btn;


    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        initToolbar(tool_bar_note);
    }

    public void initToolbar(Toolbar toolBar) {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolBar.setNavigationIcon(R.drawable.back_up);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initListener() {
        ok_btn.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_note;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok_btn:
                Note note = new Note();
                note.setChapter("1");
                note.setTime("11");
                DBManager.getInstance(this).insertData(note);
                break;
        }
    }
}
