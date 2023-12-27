package app.scrollfrom.tmdb_api_app.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import app.scrollfrom.tmdb_api_app.AppExecutors;
import app.scrollfrom.tmdb_api_app.models.MovieModel;
import app.scrollfrom.tmdb_api_app.response.MovieSearchResponse;
import app.scrollfrom.tmdb_api_app.utils.Credentials;
import retrofit2.Call;
import retrofit2.Response;


public class MovieApiClient {
    private MutableLiveData<List<MovieModel>> mMovies;

    private static MovieApiClient instance;

    //making  Global runnable
    private RetriveMoviesRunnable retriveMoviesRunnable;

    //    Live data for popular movies
    private MutableLiveData<List<MovieModel>> mMoviesPop;


    private RetriveMoviesRunnablePop retriveMoviesRunnablePop;

    ///i add some data
    private MutableLiveData<List<MovieModel>> mMoviesTopRated;

    private RetriveMoviesRunnableTopRated retriveMoviesRunnableTopRated;



    public static MovieApiClient getInstance() {
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
        mMovies = new MutableLiveData<>();

        ///// i add
        mMoviesTopRated=new MutableLiveData<>();

        mMoviesPop = new MutableLiveData<>();
    }



    public LiveData<List<MovieModel>> getMovies() {
        return mMovies;
    }

    public LiveData<List<MovieModel>> getMoviesPop() {
        return mMoviesPop;
    }
//// i add some code
public LiveData<List<MovieModel>> getMovieTopRated() {
    return mMoviesTopRated;
}
/////////

    public void searchMoviesApi(String query, int pageNumber) {

        if (retriveMoviesRunnable!=null){
            retriveMoviesRunnable=null;
        }

        retriveMoviesRunnable = new RetriveMoviesRunnable(query, pageNumber);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retriveMoviesRunnable);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }

    public void searchMoviesPop(int pageNumber) {

        if (retriveMoviesRunnablePop!=null){
            retriveMoviesRunnablePop=null;
        }

        retriveMoviesRunnablePop = new RetriveMoviesRunnablePop(pageNumber);
        final Future myHandler2 = AppExecutors.getInstance().networkIO().submit(retriveMoviesRunnablePop);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler2.cancel(true);
            }
        }, 1000, TimeUnit.MILLISECONDS);
    }
    public void searchMoviesTopRated(int pageNumber) {

        if (retriveMoviesRunnableTopRated != null) {
            retriveMoviesRunnableTopRated = null;
        }

            retriveMoviesRunnableTopRated = new RetriveMoviesRunnableTopRated(pageNumber);
            final Future myHandler3 = AppExecutors.getInstance().networkIO().submit(retriveMoviesRunnableTopRated);
            AppExecutors.getInstance().networkIO().schedule(new Runnable() {
                @Override
                public void run() {
                    myHandler3.cancel(true);
                }
            }, 1000, TimeUnit.MILLISECONDS);

    }
    //Retrieving data from RestApi by runnable class
    private class RetriveMoviesRunnable implements Runnable {

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetriveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            this.cancelRequest = cancelRequest;
        }

        @Override
        public void run() {
            try{
                Response response=getMovies(query,pageNumber).execute();
                if(cancelRequest){
                    return;
                }
                if(response.code()==200){
                    List<MovieModel>list=new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
//                    Sending data to live data
//                    Post value for background thread
//                    Set value not for background thread
                    if(pageNumber==1){
                        mMovies.postValue(list);
                    }
                    else{
                        List<MovieModel>currentMovies=mMovies.getValue();
                        currentMovies.addAll(list);
                        mMovies.postValue(currentMovies);
                    }

                }
                else{
                    String  error=response.errorBody().string();
                    Log.v("Tag","Error"+error);
                    mMovies.postValue(null);
                }

            }
            catch (IOException e){
                e.printStackTrace();
                mMovies.postValue(null);
            }
        }

        private Call<MovieSearchResponse> getMovies(String query,int pageNumber){
            Servicey service=new Servicey();
            return service.getMovieApi().searchMovie(
                    Credentials.API_KEY,
                    query,
                    pageNumber
            );

        }
        private void CancelRequest(){
            Log.v("TAg","Cancelling Search Request");
            cancelRequest=true;
        }
    }

    private class RetriveMoviesRunnablePop implements Runnable {

        private int pageNumber;
        boolean cancelRequest;

        public RetriveMoviesRunnablePop(int pageNumber) {
            this.pageNumber = pageNumber;
            this.cancelRequest = cancelRequest;
        }

        @Override
        public void run() {
            try{
                Response response2=getPop(pageNumber).execute();
                if(cancelRequest){
                    return;
                }
                if(response2.code()==200){
                    List<MovieModel>list=new ArrayList<>(((MovieSearchResponse)response2.body()).getMovies());
//                    Sending data to live data
//                    Post value for background thread
//                    Set value not for background thread
                    if(pageNumber==1){
                        mMoviesPop.postValue(list);
                    }
                    else{
                        List<MovieModel>currentMovies=mMoviesPop.getValue();
                        currentMovies.addAll(list);
                        mMoviesPop.postValue(currentMovies);
                    }

                }
                else{
                    String  error=response2.errorBody().string();
                    Log.v("Tag","Error"+error);
                    mMoviesPop.postValue(null);
                }

            }
            catch (IOException e){
                e.printStackTrace();
                mMoviesPop.postValue(null);
            }
        }

        private Call<MovieSearchResponse> getPop(int pageNumber){
            Servicey service=new Servicey();
            return service.getMovieApi().getPopular(
                    Credentials.API_KEY,
                    pageNumber
            );

        }
        private void CancelRequest(){
            Log.v("Tag","Cancelling Search Request");
            cancelRequest=true;
        }
    }
    /////// i addd some code
    private class RetriveMoviesRunnableTopRated implements Runnable {

        private int pageNumber;
        boolean cancelRequest;

        public RetriveMoviesRunnableTopRated(int pageNumber) {
            this.pageNumber = pageNumber;
            this.cancelRequest = cancelRequest;
        }


        @Override
        public void run() {
            try{
                Response response3 = getTopRated(pageNumber).execute();
                if(cancelRequest){
                    return;
                }
                if(response3.code()==200){
                    List<MovieModel>list=new ArrayList<>(((MovieSearchResponse)response3.body()).getMovies());
//                    Sending data to live data
//                    Post value for background thread
//                    Set value not for background thread
                    if(pageNumber==1){
                        mMoviesTopRated.postValue(list);
                    }
                    else{
                        List<MovieModel>currentMovies=mMoviesTopRated.getValue();
                        currentMovies.addAll(list);
                        mMoviesTopRated.postValue(currentMovies);
                    }

                }
                else{
                    String  error=response3.errorBody().string();
                    Log.v("Tag","Error"+error);
                    mMoviesTopRated.postValue(null);
                }

            }
            catch (IOException e){
                e.printStackTrace();
                mMoviesTopRated.postValue(null);
            }
        }

        private Call<MovieSearchResponse> getTopRated(int pageNumber) {
            Servicey service = new Servicey();
            return service.getMovieApi().getTopRated(
                    Credentials.API_KEY,
                    pageNumber
            );
        }
        private void CancelRequest(){
            Log.v("TAg","Cancelling Search Request");
            cancelRequest=true;
        }
    }


}