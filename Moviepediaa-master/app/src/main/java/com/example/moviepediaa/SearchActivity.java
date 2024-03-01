package com.example.moviepediaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviepediaa.Adapter.Home_recyclerview_adapter;
import com.example.moviepediaa.Adapter.searchviewadapter;
import com.example.moviepediaa.Animation.ScaleupCenterItemLayoutManager;
import com.example.moviepediaa.Listner.Home_recyclerview_clicklistner;
import com.example.moviepediaa.Models.Result;
import com.example.moviepediaa.Models.SearchApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements Home_recyclerview_clicklistner {
    private static final String APP_KEY = "e201992b016020c76ec8d0bae9f9c70e";
    RecyclerView search_recyclerview;
SearchView searchView;
    TextView textView12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_recyclerview = (RecyclerView) findViewById(R.id.searchactivityrecycler);
        searchView = (SearchView)findViewById(R.id.search2);
        textView12 = (TextView) findViewById(R.id.noresultavailablesearchactivity);

            ApicallSearchView(getIntent().getExtras().getString("movie"));


            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    textView12.setVisibility(View.GONE);
                    searchView.clearFocus();
                    ApicallSearchView(s);

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });



    }
    private void ApicallSearchView(String s) {
        ApiInterface apiInterface = Api.getRetrofitinstance().create(ApiInterface.class);
        Call<SearchApiResponse> call = apiInterface.searchMovie(APP_KEY,s);
        call.enqueue(new Callback<SearchApiResponse>() {
            @Override
            public void onResponse(Call<SearchApiResponse> call, Response<SearchApiResponse> response) {

                if(response.isSuccessful()) {
                    SearchApiResponse searchApiResponse = response.body();
                    if(searchApiResponse.getResults().size() == 0){
                        textView12.setVisibility(View.VISIBLE);
                        textView12.setText("No Result Available");}

                    makingadapter(searchApiResponse);

                }


            }

            @Override
            public void onFailure(Call<SearchApiResponse> call, Throwable t) {


                Toast.makeText(SearchActivity.this,"you are not connected to internet",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void makingadapter(SearchApiResponse searchApiResponse) {
        searchviewadapter adapter
                = new searchviewadapter(getApplicationContext(), searchApiResponse, this);
        search_recyclerview.setAdapter(adapter);
        search_recyclerview.setLayoutManager(new GridLayoutManager(SearchActivity.this,2));

    }

    @Override
    public void click(Result index) {
        Intent intent = new Intent(SearchActivity.this,Moviedetails2.class);
        intent.putExtra("movie_details", index);
        startActivity(intent);
    }
}