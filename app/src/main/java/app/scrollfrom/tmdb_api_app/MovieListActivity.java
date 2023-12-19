package app.scrollfrom.tmdb_api_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.scrollfrom.tmdb_api_app.models.MovieModel;
import app.scrollfrom.tmdb_api_app.request.Servicey;
import app.scrollfrom.tmdb_api_app.response.MovieSearchResponse;
import app.scrollfrom.tmdb_api_app.utils.Credentials;
import app.scrollfrom.tmdb_api_app.utils.MovieApi;
import app.scrollfrom.tmdb_api_app.viewmodels.MovieListViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity {
     Button btn;
     private MovieListViewModel movieListViewModel;
     // view model

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);

     movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

    }

  // Observing Any data Change
  private  void ObserveAnyChange(){


        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // Observing for any data




            }
        });
  }




    private void GetRetroFitResponse() {

        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi
                .getMovie(
                        Credentials.API_KEY,
                        "Action",
                        "1"
                );

            responseCall.enqueue(new Callback<MovieSearchResponse>() {
                @Override
                public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                    if(response.code()==200){
                        Log.v("tag","the response"+ response.body().toString());
                        List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                        for(MovieModel movie:movies ){
                            Log.v("tag","The List"+movie.getRelease_date());
                        }
                    }
                    else{
                        try{
                            Log.v("tag","Error"+response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

                }
            });

    }


    private void GetRetrofitResponseAccordingToID(){
        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieModel> responseCall = movieApi
                .getMovie(
                        343611,
                        Credentials.API_KEY);
        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.code()==200){
                    MovieModel movie = response.body();
                    Log.v("tag","The Response"+movie.getTitle());
                }
                else{
                    try{
                        Log.v("Tag","Error"+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }



}