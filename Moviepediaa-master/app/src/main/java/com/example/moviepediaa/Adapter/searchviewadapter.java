package com.example.moviepediaa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviepediaa.Listner.Home_recyclerview_clicklistner;
import com.example.moviepediaa.Models.SearchApiResponse;
import com.example.moviepediaa.R;
import com.squareup.picasso.Picasso;

public class searchviewadapter extends RecyclerView.Adapter<search_holder> {
    Context context;
    SearchApiResponse searchApiResponseList;
    Home_recyclerview_clicklistner listner;

    public searchviewadapter(Context context, SearchApiResponse searchApiResponseList,Home_recyclerview_clicklistner listner) {
        this.context = context;
        this.searchApiResponseList = searchApiResponseList;
        this.listner = listner;
    }




    @NonNull
    @Override
    public search_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.searchviewobject, parent, false);
        return new search_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull search_holder holder, int position) {
        String s="https://image.tmdb.org/t/p/w500"+ searchApiResponseList.getResults().get(position).getPosterPath();
        Picasso.get().load(s).into(holder.searchphoto);
        holder.mname.setText(searchApiResponseList.getResults().get(position).getOriginalTitle());
        holder.rating.setText(""+searchApiResponseList.getResults().get(position).getVoteAverage());
        holder.star.setBackgroundResource(R.drawable.ic_baseline_star_rate_24);

        holder.searchphoto.setOnClickListener(new View.OnClickListener() {
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
class search_holder extends RecyclerView.ViewHolder {

    ImageView searchphoto,star;
    TextView mname,rating;
    public search_holder(@NonNull View itemView) {
        super(itemView);
        searchphoto = (ImageView) itemView.findViewById(R.id.search_recyclerobject_image);
        searchphoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mname = (TextView) itemView.findViewById(R.id.movie_name);
        rating = (TextView) itemView.findViewById(R.id.rating_search);
        star = (ImageView)itemView.findViewById(R.id.star_search);
    }

}