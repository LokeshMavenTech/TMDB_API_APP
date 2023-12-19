package app.scrollfrom.tmdb_api_app.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import app.scrollfrom.tmdb_api_app.models.MovieModel;
import app.scrollfrom.tmdb_api_app.request.MovieApiClient;

public class MovieRepository {
    //this Class is acting as reposotiry
    private static MovieRepository instance;

    private MovieApiClient movieApiClient;



    public static MovieRepository getInstance(){
        if( instance== null){
            instance= new MovieRepository();
        }
        return instance;
    }

    private MovieRepository(){
    movieApiClient = MovieApiClient.getInstance();
    }

  public  LiveData<List<MovieModel>> getMovies(){
        return movieApiClient.getMovies();}
}
