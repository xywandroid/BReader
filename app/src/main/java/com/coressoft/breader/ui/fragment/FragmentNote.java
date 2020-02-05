package com.coressoft.breader.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.coressoft.breader.R;
import com.coressoft.breader.adapter.NotesAdapter;
import com.coressoft.breader.base.BaseFragment;
import com.coressoft.breader.bean.Note;
import com.coressoft.breader.db.DBManager;
import com.coressoft.breader.ui.activity.NewNoteActivity;
import com.coressoft.breader.ui.view.FixedRecyclerView;
import com.coressoft.breader.utils.XLog;
import com.shamanland.fab.FloatingActionButton;

import java.util.List;

import butterknife.BindView;


/**
 * Created by xingyw on 18-1-31.
 */

public class FragmentNote extends BaseFragment {
    @BindView(R.id.recyclerView)
    FixedRecyclerView recyclerView;
    @BindView(R.id.refresher)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.new_note_btn)
    FloatingActionButton new_note_btn;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_note;
    }

    @Override
    protected void loadData() {
        List<Note> list = DBManager.getInstance(getActivity()).getNoteData();
        XLog.i("list----->>"+list.size());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        new_note_btn.setSize(FloatingActionButton.SIZE_MINI);
        new_note_btn.setColor(Color.RED);
        new_note_btn.initBackground();

        new_note_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),NewNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData(View view) {

    }

    NotesAdapter recyclerAdapter;




//    public void initRecyclerView(List<Note> notes){
//        recyclerAdapter = new NotesAdapter(getActivity(), notes);
//        recyclerView.setHasFixedSize(true);
//        recyclerAdapter.setOnInViewClickListener(R.id.notes_item_root,
//                new BaseRecyclerViewAdapter.onInternalClickListenerImpl<Note>() {
//                    @Override
//                    public void OnClickListener(View parentV, View v, Integer position, Note values) {
//                        super.OnClickListener(parentV, v, position, values);
//                        mainPresenter.onRecyclerViewItemClick(position, values);
//                    }
//                });
//        recyclerAdapter.setOnInViewClickListener(R.id.note_more,
//                new BaseRecyclerViewAdapter.onInternalClickListenerImpl<SNote>() {
//                    @Override
//                    public void OnClickListener(View parentV, View v, Integer position, SNote values) {
//                        super.OnClickListener(parentV, v, position, values);
//                        mainPresenter.showPopMenu(v, position, values);
//                    }
//                });
//        recyclerAdapter.setFirstOnly(false);
//        recyclerAdapter.setDuration(300);
//        recyclerView.setAdapter(recyclerAdapter);
//        refreshLayout.setColorSchemeColors(getColorPrimary());
//        refreshLayout.setOnRefreshListener(mainPresenter);
//    }

    @Override
    protected void initListener() {

    }

}
