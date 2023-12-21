package app.scrollfrom.tmdb_api_app.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import app.scrollfrom.tmdb_api_app.models.MovieModel;

public class MovieResponse {

    //  Finding the Movie Object
    @SerializedName("results")
    @Expose
    private MovieModel movie;

    public MovieModel getMovie(){
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
