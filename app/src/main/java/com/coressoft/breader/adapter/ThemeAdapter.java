package com.coressoft.breader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coressoft.breader.R;
import com.coressoft.breader.ui.view.SquareImageView;



/**
 * Created by pengfengwang on 2017/1/16.
 */
public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ThemeHolder> {
    SquareImageView preview;
    ImageView indicator;
    TextView name;
    TextView description;
    int skin_image[] = {R.drawable.skin_red,R.drawable.skin_white,R.drawable.skin_color};
    String skin_text[] = {"红色","白色","自选"};
    Context context;

    public ThemeAdapter(Context context) {
        this.context = context;
    }
    @Override
    public ThemeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.skin_lib_item, parent, false);
        return new ThemeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ThemeHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ThemeHolder extends RecyclerView.ViewHolder{

        public ThemeHolder(View itemView) {
            super(itemView);
            preview = (SquareImageView) itemView.findViewById(R.id.preview);
            indicator = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);

        }

        public void setData(int position) {
            preview.setImageDrawable(context.getResources().getDrawable(skin_image[position]));
//            indicator.setText(names[position % 10]);
            name.setText(skin_text[position]);
//            description.setText("");
        }
    }


}
