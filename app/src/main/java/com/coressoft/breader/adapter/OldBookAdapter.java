package com.coressoft.breader.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.coressoft.breader.R;
import com.coressoft.breader.bean.Book;
import com.coressoft.breader.ui.view.DragGridView;
import com.coressoft.breader.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/12/17.
 */
public class OldBookAdapter extends BaseAdapter {
    private Context mContext;
    private List<Book> bilist;
    private static LayoutInflater inflater = null;
    private int mHidePosition = -1;
    private Typeface typeface;
    protected List<AsyncTask<Void, Void, Boolean>> myAsyncTasks = new ArrayList<>();
    private int[] firstLocation;
    public OldBookAdapter(Context context, List<Book> bilist){
        this.mContext = context;
        this.bilist = bilist;
        typeface = SPUtils.getInstance().getTypeface(mContext);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        //背景书架的draw需要用到item的高度
        if(bilist.size() < 10){
            return 10;
        }else{
            return bilist.size();
        }
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return bilist.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup arg2) {
        // TODO Auto-generated method stub
        final ViewHolder viewHolder;
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.bookitem, null);
            viewHolder = new ViewHolder(contentView);
            viewHolder.name.setTypeface(typeface);
            contentView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) contentView.getTag();
        }

        if(bilist.size() > position){
            //DragGridView  解决复用问题
            if(position == mHidePosition){
                contentView.setVisibility(View.INVISIBLE);
            }else {
                contentView.setVisibility(View.VISIBLE);
            }
            if (DragGridView.getShowDeleteButton()) {
                viewHolder.deleteItem_IB.setVisibility(View.VISIBLE);
            }else {
                viewHolder.deleteItem_IB.setVisibility(View.INVISIBLE);
            }
            viewHolder.name.setVisibility(View.VISIBLE);
            String fileName = bilist.get(position).getName();
            viewHolder.name.setText(fileName);
        }else {
            contentView.setVisibility(View.INVISIBLE);
        }
        return contentView;
    }

    static class ViewHolder {
        @BindView(R.id.ib_close)
        ImageButton deleteItem_IB;
        @BindView(R.id.tv_name)
        TextView name;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }







}
