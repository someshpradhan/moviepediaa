package com.example.moviepediaa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.moviepediaa.Adapter.Home_recyclerview_adapter;
import com.example.moviepediaa.Adapter.searchviewadapter;
import com.example.moviepediaa.Animation.ScaleupCenterItemLayoutManager;
import com.example.moviepediaa.Listner.Home_recyclerview_clicklistner;
import com.example.moviepediaa.Models.CastInfo;
import com.example.moviepediaa.Models.MovieDetailsAndVideos;
import com.example.moviepediaa.Models.Result;
import com.example.moviepediaa.Models.SearchApiResponse;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Home_recyclerview_clicklistner {

    private static final String APP_KEY = "e201992b016020c76ec8d0bae9f9c70e";
    RecyclerView Homeview_recyclerview,nowplayingrecycler,nowplayingrecycler2;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Homeview_recyclerview = (RecyclerView) findViewById(R.id.Homeview_recyclerview);
        searchView = (SearchView) findViewById(R.id.search);
        nowplayingrecycler = (RecyclerView) findViewById(R.id.home_grid);
        nowplayingrecycler2 = (RecyclerView) findViewById(R.id.home_grid_top);
       SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshlayout);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                intent.putExtra("movie",searchView.getQuery().toString());
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                ApiCallForHomeView();
                ApiCallForHomeView2();
                ApiCallForHomeView3();


            }
        });



        ApiCallForHomeView();
        ApiCallForHomeView2();
        ApiCallForHomeView3();



    }

    private void ApiCallForHomeView3() {
        ApiInterface apiInterface = Api.getRetrofitinstance().create(ApiInterface.class);
        Call<SearchApiResponse> call = apiInterface.toprated(APP_KEY);
        call.enqueue(new Callback<SearchApiResponse>() {
            @Override
            public void onResponse(Call<SearchApiResponse> call, Response<SearchApiResponse> response) {

                if(response.isSuccessful()) {
                    SearchApiResponse searchApiResponse = response.body();
                    makingadapter3(searchApiResponse);

                }


            }

            @Override
            public void onFailure(Call<SearchApiResponse> call, Throwable t) {


                Toast.makeText(MainActivity.this,"you are not connected to internet",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ApiCallForHomeView() {
        ApiInterface apiInterface = Api.getRetrofitinstance().create(ApiInterface.class);
        Call<SearchApiResponse> call = apiInterface.getPopular(APP_KEY);
        call.enqueue(new Callback<SearchApiResponse>() {
            @Override
            public void onResponse(Call<SearchApiResponse> call, Response<SearchApiResponse> response) {

                if(response.isSuccessful()) {
                    SearchApiResponse searchApiResponse = response.body();
                    makingadapter(searchApiResponse);

                }


            }

            @Override
            public void onFailure(Call<SearchApiResponse> call, Throwable t) {


                Toast.makeText(MainActivity.this,"you are not connected to internet",Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void ApiCallForHomeView2() {
        ApiInterface apiInterface = Api.getRetrofitinstance().create(ApiInterface.class);
        Call<SearchApiResponse> call = apiInterface.NowPlaying(APP_KEY);
        call.enqueue(new Callback<SearchApiResponse>() {
            @Override
            public void onResponse(Call<SearchApiResponse> call, Response<SearchApiResponse> response) {

                if(response.isSuccessful()) {
                    SearchApiResponse searchApiResponse = response.body();
                    makingadapter2(searchApiResponse);

                }


            }

            @Override
            public void onFailure(Call<SearchApiResponse> call, Throwable t) {


                Toast.makeText(MainActivity.this,"you are not connected to internet",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void makingadapter3(SearchApiResponse searchApiResponse) {
        searchviewadapter adapter
                = new searchviewadapter(getApplicationContext(), searchApiResponse, this);
        nowplayingrecycler2.setAdapter(adapter);
        nowplayingrecycler2.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
        nowplayingrecycler2.setHasFixedSize(true);
        nowplayingrecycler2.setNestedScrollingEnabled(false);
    }

    private void makingadapter2(SearchApiResponse searchApiResponse) {
        searchviewadapter adapter
                = new searchviewadapter(getApplicationContext(), searchApiResponse, this);
        nowplayingrecycler.setAdapter(adapter);
        nowplayingrecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
        nowplayingrecycler.setHasFixedSize(true);
        nowplayingrecycler.setNestedScrollingEnabled(false);
    }


    private void makingadapter(SearchApiResponse searchApiResponse) {
        Home_recyclerview_adapter adapter
                = new Home_recyclerview_adapter(getApplicationContext(), searchApiResponse, this);
        Homeview_recyclerview.setAdapter(adapter);
        Homeview_recyclerview.setLayoutManager(new ScaleupCenterItemLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
        Homeview_recyclerview.setHasFixedSize(true);
        Homeview_recyclerview.setNestedScrollingEnabled(false);
    }


    @Override
    public void click(Result r) {
        Intent intent = new Intent(MainActivity.this,Moviedetails2.class);
        intent.putExtra("movie_details", r);
        startActivity(intent);
    }
}








