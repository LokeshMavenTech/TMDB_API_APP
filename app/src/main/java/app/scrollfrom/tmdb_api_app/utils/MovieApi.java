package app.scrollfrom.tmdb_api_app.utils;

import app.scrollfrom.tmdb_api_app.models.MovieModel;
import app.scrollfrom.tmdb_api_app.response.MovieSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    //Search For Movies
    @GET("3/search/movie")
    Call<MovieSearchResponse> getMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") String page


    );
    //Making Search with id
    @GET("3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
          @Path("movie_id") int movie_id,
          @Query("api_key") String api_key

    );
}
