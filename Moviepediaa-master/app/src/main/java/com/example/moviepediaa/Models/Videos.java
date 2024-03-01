
package com.example.moviepediaa.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Videos {

    @SerializedName("videos")
    @Expose
    private Videos__1 videos;

    public Videos__1 getVideos() {
        return videos;
    }

    public void setVideos(Videos__1 videos) {
        this.videos = videos;
    }

}
