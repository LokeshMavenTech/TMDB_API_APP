package app.scrollfrom.tmdb_api_app.request;

import android.renderscript.ScriptGroup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import app.scrollfrom.tmdb_api_app.AppExecutors;
import app.scrollfrom.tmdb_api_app.models.MovieModel;

public class MovieApiClient {
    //Live data
    private MutableLiveData<List<MovieModel>> mMovies;

    private static MovieApiClient instance;


    public static MovieApiClient getInstance() {
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
        mMovies = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return mMovies;
    }

    public void searchMovieApi() {
        final Future myHandler = AppExecutors.getInstance().networkIO().submit();

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // cancaling
                myHandler.cancel(true);
            }
        }, 4000, TimeUnit.MICROSECONDS);


    }

    // retriving data from restApi by runnable class
    // wE HAVE 2T TYPE OF QUERIES: ID
    private class RetrieveMoviesRunnable implements Runnable {
        private String query;
        private int pageNumber;
        boolean cancelRequest;


        public RetrieveMoviesRunnable(String query, int pageNumber, boolean cancelRequest) {
            this.query = query;
            this.pageNumber = pageNumber;
            this.cancelRequest = false;
        }

        @Override
        public void run() {
            if (cancelRequest) {
                return;
            }

        }
    }


}
