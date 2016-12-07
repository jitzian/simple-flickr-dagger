package com.example.raian.com.org.simpleflicker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.raian.com.org.simpleflicker.R;
import com.example.raian.com.org.simpleflicker.model.Photo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by raian on 12/6/16.
 */

public class RVFlickerAdapter extends RecyclerView.Adapter<RVFlickerAdapter.ViewHolder>{
    private static final String TAG = RVFlickerAdapter.class.getSimpleName();
    Context context;
    List<Photo>lstRes;

    public RVFlickerAdapter(Context context, List<Photo> lstRes) {
        Log.d(TAG, "RVFlickerAdapter");
        this.context = context;
        this.lstRes = lstRes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextViewTitle.setText(lstRes.get(position).getOwner());
        Glide.with(context)
                .load("https://avatars.githubusercontent.com/u/7937304?v=3")
                .centerCrop()
                .into(holder.mImageViewThumbnail);
    }

    @Override
    public int getItemCount() {
        return lstRes.size() > 0 ? lstRes.size() : 0;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.mImageViewThumbnail)
        ImageView mImageViewThumbnail;

        @BindView(R.id.mTextViewTitle)
        TextView mTextViewTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
