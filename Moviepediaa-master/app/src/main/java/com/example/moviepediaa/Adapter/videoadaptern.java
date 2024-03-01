package com.example.moviepediaa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.RecyclerView;
import com.example.moviepediaa.Models.CastInfo;
import com.example.moviepediaa.Models.Videos;
import com.example.moviepediaa.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

public class videoadaptern extends RecyclerView.Adapter<videoHolder1> {
    Context context;
    Videos videos;
    Lifecycle lifecycle;

    public videoadaptern(Context context,Videos videos,Lifecycle lifecycle) {
        this.context = context;
        this.videos = videos;
        this.lifecycle = lifecycle;
    }

    @NonNull
    @Override
    public videoHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nvideo_layout, parent, false);
        return new videoHolder1(view);
    }


    @Override
    public void onBindViewHolder(@NonNull videoHolder1 holder, int position) {

        lifecycle.addObserver(holder.youTubePlayerView);


        holder.youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);

                youTubePlayer.cueVideo(videos.getVideos().getResults().get(position).getKey(), 0);


            }
        });

    }

    @Override
    public int getItemCount() {
        return videos.getVideos().getResults().size();
    }



}

class videoHolder1 extends RecyclerView.ViewHolder{

    YouTubePlayerView youTubePlayerView;
    public videoHolder1(@NonNull View itemView) {
        super(itemView);
    youTubePlayerView = (YouTubePlayerView) itemView.findViewById(R.id.pyt);
    }
}

