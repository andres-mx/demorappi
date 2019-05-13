package com.movilmx.demorappi.videos;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.movilmx.core.constants.Constants;
import com.movilmx.core.ui.UIEvent;
import com.movilmx.core.ui.UIEventType;
import com.movilmx.core.ui.UIObject;
import com.movilmx.core.videos.Videos;
import com.movilmx.demorappi.R;
import com.squareup.picasso.Picasso;

public class VideosHolder extends RecyclerView.ViewHolder {
    private static final String TAG = VideosHolder.class.getSimpleName();

    public VideosHolder(@NonNull View itemView, UIEvent event) {
        super(itemView);
        rootView = itemView;
        uiEvent  = event;
        assignViews();
        rootView.setOnClickListener(v -> {
            uiEvent.event(
                    UIEventType.HOLDERCLICK,
                    new UIObject()
                            .setHolderPosition(getAdapterPosition()));
        });
    }

    public void bind(Videos videos){
        uiVideo = videos;
        tvTitle.setText(videos.getTitle());
        Picasso.with(cvContainer.getContext())
                .load(Constants.getUrlImage(videos.getImage()))
                .resize(Constants.dpToPx(75),Constants.dpToPx(75))
                .into(ivVideoImage);
        tvDate.setText(videos.getDate());
    }

    private void assignViews(){
        try{
            cvContainer  = rootView.findViewById(R.id.cv_container);
            ivVideoImage = rootView.findViewById(R.id.iv_video_image);
            tvTitle      = rootView.findViewById(R.id.tv_title);
            tvDate       = rootView.findViewById(R.id.tv_date);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private View      rootView;
    private Videos    uiVideo;
    private CardView  cvContainer;
    private ImageView ivVideoImage;
    private TextView  tvTitle;
    private TextView  tvDate;
    private UIEvent   uiEvent;
}
