package app.scrollfrom.tmdb_api_app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import app.scrollfrom.tmdb_api_app.models.MovieModel;
import app.scrollfrom.tmdb_api_app.repositories.MovieRepository;

public class MovieListViewModel extends ViewModel {

    // this class is used for VIEWMODEL



    private MovieRepository movieRepository;





    // constructor


    public MovieListViewModel() {
        movieRepository= MovieRepository.getInstance();

    }
    public LiveData<List<MovieModel>> getMovies(){
        return movieRepository.getMovies();
    }
}
