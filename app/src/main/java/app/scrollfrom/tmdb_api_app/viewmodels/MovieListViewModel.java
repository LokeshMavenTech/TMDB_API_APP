package app.scrollfrom.tmdb_api_app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import app.scrollfrom.tmdb_api_app.models.MovieModel;
import app.scrollfrom.tmdb_api_app.repositories.MovieRepository;

public class MovieListViewModel extends ViewModel {

    private MovieRepository movieRepository;


    //    Constructor
    public MovieListViewModel(){
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieRepository.getMovies();
    }

    public LiveData<List<MovieModel>> getPop(){
        return movieRepository.getPop();
    }
    public LiveData<List<MovieModel>> getTopRated(){
        return movieRepository.getTopRated();
    }
    public void searchMovieTopRated(int pageNumber){
        movieRepository.searchMovieTopRated(pageNumber);
    }


    public void searchMovieApi(String query, int pageNumber){
        movieRepository.searchMovieApi(query, pageNumber);
    }

    public void searchMoviePop(int pageNumber){
        movieRepository.searchMoviePop(pageNumber);
    }

    public void searchNextPage(){

        movieRepository.searchNextPage();
    }

}