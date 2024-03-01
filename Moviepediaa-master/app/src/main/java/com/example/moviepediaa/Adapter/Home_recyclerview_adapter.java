package com.example.moviepediaa.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviepediaa.Listner.Home_recyclerview_clicklistner;
import com.example.moviepediaa.MainActivity;
import com.example.moviepediaa.Models.SearchApiResponse;
import com.example.moviepediaa.Moviedetails2;
import com.example.moviepediaa.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Home_recyclerview_adapter extends RecyclerView.Adapter<Home_recyclerview_holder> {
    Context context;
    SearchApiResponse searchApiResponseList;
    Home_recyclerview_clicklistner listner;

    public Home_recyclerview_adapter(Context context, SearchApiResponse searchApiResponseList,Home_recyclerview_clicklistner listner) {
        this.context = context;
        this.searchApiResponseList = searchApiResponseList;
        this.listner = listner;
    }




    @NonNull
    @Override
    public Home_recyclerview_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.homeview_recyclerobject, parent, false);
        return new Home_recyclerview_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Home_recyclerview_holder holder, int position) {
      String s="https://image.tmdb.org/t/p/w500"+ searchApiResponseList.getResults().get(position).getPosterPath();
        Picasso.get().load(s).into(holder.Homeview_recyclerview_photo);
        holder.name.setText(searchApiResponseList.getResults().get(position).getTitle());
        holder.lang.setText(searchApiResponseList.getResults().get(position).getOriginalLanguage());
        holder.rating.setText("" + searchApiResponseList.getResults().get(position).getVoteAverage());
        holder.starhome.setBackgroundResource(R.drawable.ic_baseline_star_rate_24);


     holder.Homeview_recyclerview_photo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listner.click(searchApiResponseList.getResults().get(position));
        }
    });



    }

    @Override
    public int getItemCount() {
        return searchApiResponseList.getResults().size();
    }
}
class Home_recyclerview_holder extends RecyclerView.ViewHolder {

    ImageView Homeview_recyclerview_photo,starhome;
    TextView name,lang,rating;
    public Home_recyclerview_holder(@NonNull View itemView) {
        super(itemView);
        Homeview_recyclerview_photo = (ImageView) itemView.findViewById(R.id.Homeview_recyclerobject_image);
        Homeview_recyclerview_photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
        rating = itemView.findViewById(R.id.duration_home);
        lang = itemView.findViewById(R.id.langusge_home);
        name = itemView.findViewById(R.id.moviename_home);
        starhome = itemView.findViewById(R.id.star_home);

    }

}