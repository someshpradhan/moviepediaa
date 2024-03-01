
package com.example.moviepediaa.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Videos__1 {

    @SerializedName("results")
    @Expose
    private List<results> results = null;

    public List<results> getResults() {
        return results;
    }

    public void setResults(List<results> results) {
        this.results = results;
    }

}
