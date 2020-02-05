package com.coressoft.breader.adapter;

import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.coressoft.breader.R;


/**
 * Created by Eoin on 8/8/16.
 */
public class SplashImageAdapter extends RecyclerView.Adapter<SplashImageAdapter.ViewHolder> {

    private TypedArray images;

    public SplashImageAdapter(TypedArray dataSet) {
        images = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.splash_image_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.image.setImageDrawable(images.getDrawable(position));
    }

    @Override
    public int getItemCount() {
        return images.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;

        public ViewHolder(View view) {
            super(view);
            image = (ImageView) view;
        }
    }
}
