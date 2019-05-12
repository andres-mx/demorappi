package com.movilmx.demorappi.videos;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.movilmx.core.constants.Constants;
import com.movilmx.core.videos.Videos;
import com.movilmx.demorappi.R;
import com.squareup.picasso.Picasso;

public class VideosHolder extends RecyclerView.ViewHolder {
    private static final String TAG = VideosHolder.class.getSimpleName();

    public VideosHolder(@NonNull View itemView) {
        super(itemView);
        rootView = itemView;
        assignViews();
        rootView.setOnClickListener(v -> {

        });
    }

    public void bind(Videos videos){
        uiVideo = videos;
        tvTitle.setText(videos.getTitle());
        Picasso.with(cvContainer.getContext())
                .load(Constants.getUrlImage(videos.getImage()))
                .resize(Constants.dpToPx(75),Constants.dpToPx(75))
                .into(ivVideoImage);
    }

    private void assignViews(){
        try{
            cvContainer  = rootView.findViewById(R.id.cv_container);
            ivVideoImage = rootView.findViewById(R.id.iv_video_image);
            tvTitle      = rootView.findViewById(R.id.tv_title);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private View      rootView;
    private Videos    uiVideo;
    private CardView  cvContainer;
    private ImageView ivVideoImage;
    private TextView  tvTitle;
}
