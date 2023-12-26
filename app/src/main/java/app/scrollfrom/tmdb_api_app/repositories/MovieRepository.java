package app.scrollfrom.tmdb_api_app.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import app.scrollfrom.tmdb_api_app.models.MovieModel;
import app.scrollfrom.tmdb_api_app.request.MovieApiClient;

public class MovieRepository {
//    This class is acting as repositories

    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    private String mQuery;
    private int mPageNumber;
    //// i add some code
    private String hQuery;
    private int hPageNumber;
    ///////////

    public static MovieRepository getInstance(){
        if(instance == null){
            instance = new MovieRepository();
        }
        return instance;

    }
    private MovieRepository(){
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieApiClient.getMovies();
    }

    public LiveData<List<MovieModel>> getPop(){
        return movieApiClient.getMoviesPop();
    }
    public LiveData<List<MovieModel>> getTopRated(){
        return movieApiClient.getMovieTopRated();
    }

    public void searchMovieApi(String query, int pageNumber){

        mQuery = query;
        mPageNumber = pageNumber;
        movieApiClient.searchMoviesApi(query, pageNumber);

    }
    public void searchMoviePop(int pageNumber){

        mPageNumber = pageNumber;
        movieApiClient.searchMoviesPop(pageNumber);

    }
    public void searchMovieTopRated(int pageNumber){
        mPageNumber = pageNumber;
        movieApiClient.searchMoviesTopRated(pageNumber);
    }
//// i add some code
//    public void homeSearchMovieApi(String query, int pageNumber){
//
//        hQuery = query;
//        hPageNumber = pageNumber;
//        movieApiClient.s
//
//    }
//    //////////

    public void searchNextPage(){
        searchMovieApi(mQuery, mPageNumber+1);

    }



}