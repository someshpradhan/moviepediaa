package com.example.moviepediaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.moviepediaa.Adapter.Home_recyclerview_adapter;
import com.example.moviepediaa.Adapter.searchviewadapter;
import com.example.moviepediaa.Adapter.videoadapter;
import com.example.moviepediaa.Adapter.videoadaptern;
import com.example.moviepediaa.Animation.ScaleupCenterItemLayoutManager;
import com.example.moviepediaa.Animation.ScaleupCenterItemLayoutManager2;
import com.example.moviepediaa.Listner.Home_recyclerview_clicklistner;
import com.example.moviepediaa.Models.CastInfo;
import com.example.moviepediaa.Models.Result;
import com.example.moviepediaa.Models.SearchApiResponse;
import com.example.moviepediaa.Models.Videos;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Moviedetails2 extends AppCompatActivity implements Home_recyclerview_clicklistner {

    ImageView imageView;
    TextView textView,name,lang,dura,new1,new2,cast,trailer,similardetails;
    YouTubePlayerView youTubePlayerView;
    RecyclerView videorecyclerview,similarrecycle;
    CardView cardView;
    SwipeRefreshLayout swipeRefreshLayout;

    private Result details;
    public static final String api_key="AIzaSyBz7H90tUbcyIaFSYJgvQIGacvM-9u_Rzs";
    RecyclerView   detailsrecyclerview;
    private static final String APP_KEY = "e201992b016020c76ec8d0bae9f9c70e";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetails2);


     details = (Result) getIntent().getExtras().getSerializable("movie_details");

       imageView = findViewById(R.id.imagedetails);
       textView = findViewById(R.id.overviewdetails);
       cardView = (CardView) findViewById(R.id.cardview);
        name = findViewById(R.id.namedetails);
        lang = findViewById(R.id.languagedetails);
        dura = findViewById(R.id.durationdetails);
        new1 = findViewById(R.id.new1tails);
        new2 = findViewById(R.id.new2details);
        cast = findViewById(R.id.castdetails);
        trailer = findViewById(R.id.videodetails);
        similardetails = findViewById(R.id.similardetails);
       detailsrecyclerview = (RecyclerView) findViewById(R.id.ytplayer);
       similarrecycle = (RecyclerView) findViewById(R.id.similarrecycle);
        videorecyclerview = (RecyclerView) findViewById(R.id.videorecycle);
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + details.getBackdropPath()).into(imageView);
        cardView.setBackgroundResource(R.drawable.bg_round1);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        name.setText(details.getOriginalTitle());
        lang.setText(details.getOriginalLanguage());
        dura.setText("Release date: "+details.getReleaseDate());
        new1.setText("Popularity: "+details.getPopularity());
        new2.setText("Rating:" + details.getVoteAverage());
        textView.setText(details.getOverview());
        cast.setText("Cast");
        trailer.setText("Trailer");
        similardetails.setText("Similar Movies");

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refreshdetails);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + details.getBackdropPath()).into(imageView);
                Api_video(details);
                Api_call(details);
                Api_similar(details);
            }
        });

        Api_video(details);
        Api_call(details);
        Api_similar(details);












    }


    public void Api_call(Result details){


        ApiInterface apiInterface = Api.getRetrofitinstance().create(ApiInterface.class);
        Call<CastInfo> call = apiInterface.getCast(details.getId(),APP_KEY);
        call.enqueue(new Callback<CastInfo>() {
            @Override
            public void onResponse(Call<CastInfo> call, Response<CastInfo> response) {
                if(response.isSuccessful()){
                    creatingadapter(response.body());
                }
                return;
            }

            @Override
            public void onFailure(Call<CastInfo> call, Throwable t) {
                System.out.println("failed yfutfytdytdtreetrdfcvhmbkf6e4aagfgvbk87trefghjkhgfdsdfgjlgflgfgjjhgf");
            }
        });


    }
    public void Api_video(Result details){


        ApiInterface apiInterface = Api.getRetrofitinstance().create(ApiInterface.class);
        Call<Videos> call = apiInterface.getVideos(details.getId(),APP_KEY,"videos");
        call.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                if(response.isSuccessful()){
                    makingadapter(response.body());
                }
                return;
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {
                System.out.println("failed yfutfytdytdtreetrdfcvhmbkf6e4aagfgvbk87trefghjkhgfdsdfgjlgflgfgjjhgf");
            }
        });


    }


    public void Api_similar(Result details){


        ApiInterface apiInterface = Api.getRetrofitinstance().create(ApiInterface.class);
        Call<SearchApiResponse> call = apiInterface.getSimilar(details.getId(),APP_KEY);
        call.enqueue(new Callback<SearchApiResponse>() {
            @Override
            public void onResponse(Call<SearchApiResponse> call, Response<SearchApiResponse> response) {
                if(response.isSuccessful()){
                    similaradapter(response.body());
                }
                return;
            }

            @Override
            public void onFailure(Call<SearchApiResponse> call, Throwable t) {
                System.out.println("failed yfutfytdytdtreetrdfcvhmbkf6e4aagfgvbk87trefghjkhgfdsdfgjlgflgfgjjhgf");
            }
        });


    }

    private void similaradapter(SearchApiResponse movies) {

        searchviewadapter adapter = new searchviewadapter(getApplicationContext(),movies,this);
        similarrecycle.setAdapter(adapter);
        similarrecycle.setLayoutManager(new LinearLayoutManager(Moviedetails2.this,LinearLayoutManager.HORIZONTAL,false));

    }

    private void creatingadapter(CastInfo videos) {
        videoadapter adapter
                = new videoadapter(getApplicationContext(),videos);
        detailsrecyclerview.setAdapter(adapter);
        detailsrecyclerview.setLayoutManager(new ScaleupCenterItemLayoutManager2(Moviedetails2.this, LinearLayoutManager.HORIZONTAL,false));

    }
    private void makingadapter(Videos videos) {
        videoadaptern adapter
                = new videoadaptern(getApplicationContext(),videos,getLifecycle());
        videorecyclerview.setAdapter(adapter);
        videorecyclerview.setLayoutManager(new ScaleupCenterItemLayoutManager(Moviedetails2.this, LinearLayoutManager.HORIZONTAL,false));

    }


    @Override
    public void click(Result index) {
        Intent intent = new Intent(Moviedetails2.this,Moviedetails2.class);
        intent.putExtra("movie_details", index);
        startActivity(intent);
    }
}