package com.example.moviepediaa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviepediaa.Models.CastInfo;
import com.example.moviepediaa.Models.Videos;
import com.example.moviepediaa.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

public class videoadapter extends RecyclerView.Adapter<videoHolder> {
    Context context;
    CastInfo videos;


    public videoadapter(Context context,CastInfo videos) {
        this.context = context;
        this.videos = videos;
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull videoHolder holder) {
        super.onViewDetachedFromWindow(holder);
    holder.textViewcharacter.setVisibility(View.GONE);
    holder.textViewname.setVisibility(View.GONE);
    }

    @NonNull
    @Override
    public videoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_layout, parent, false);
        return new videoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull videoHolder holder, int position) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + videos.getCast().get(position).getProfilePath()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.textViewname.setVisibility(View.VISIBLE);
                holder.textViewname.setText(videos.getCast().get(position).getOriginalName());
                holder.textViewcharacter.setVisibility(View.VISIBLE);
                holder.textViewcharacter.setText(videos.getCast().get(position).getCharacter());
            }
        });

    }

    @Override
    public int getItemCount() {
        return videos.getCast().size();
    }


}

class videoHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView textViewname;
    TextView textViewcharacter;
    public videoHolder(@NonNull View itemView) {
        super(itemView);
      imageView = itemView.findViewById(R.id.img);
      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      textViewname = (TextView) itemView.findViewById(R.id.name);
      textViewcharacter = (TextView) itemView.findViewById(R.id.character);
    }
}


