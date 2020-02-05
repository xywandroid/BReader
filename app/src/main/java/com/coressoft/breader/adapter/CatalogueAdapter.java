package com.coressoft.breader.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.coressoft.breader.Config;
import com.coressoft.breader.R;
import com.coressoft.breader.bean.Verse;
import com.coressoft.breader.utils.SPUtils;
import com.coressoft.breader.utils.XLog;

import java.util.List;


public class CatalogueAdapter extends BaseAdapter {
    private Context mContext;
    private List<Verse> bookCatalogueList;
    private Typeface typeface;
    private Config config;
    private int currentCharter = 0;

    public CatalogueAdapter(Context context, List<Verse> bookCatalogueList) {
        mContext = context;
        this.bookCatalogueList = bookCatalogueList;
        typeface = SPUtils.getInstance().getTypeface(mContext);
    }

    @Override
    public int getCount() {
        return bookCatalogueList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookCatalogueList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setCharter(int charter){
        currentCharter = charter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        final ViewHolder viewHolder;
        if(convertView==null) {
            viewHolder= new ViewHolder();
            convertView = inflater.inflate(R.layout.cataloguelistview_item,null);
            viewHolder.catalogue_tv = (TextView)convertView.findViewById(R.id.catalogue_tv);
            viewHolder.catalogue_tv.setTypeface(typeface);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        if (currentCharter == position){
            viewHolder.catalogue_tv.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        }else{
            viewHolder.catalogue_tv.setTextColor(mContext.getResources().getColor(R.color.read_textColor));
        }
        viewHolder.catalogue_tv.setText(bookCatalogueList.get(position).getCon());
        for(int i = 0;i<bookCatalogueList.size();i++){
            XLog.i("bookCatalogueListName------>>"+bookCatalogueList.get(i).getCon());
        }
        return convertView;
    }

    class ViewHolder {
        TextView catalogue_tv;
    }
}
