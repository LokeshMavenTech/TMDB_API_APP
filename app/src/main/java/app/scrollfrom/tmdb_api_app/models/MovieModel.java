package app.scrollfrom.tmdb_api_app.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class MovieModel implements Parcelable {

    //    Model class for movies
    private String title;
    private String backdrop_path;
    private String release_date;
    @SerializedName("id")
    private int movie_id;
    private float vote_average;
    private String movie_overview;
    private String original_language;

    public MovieModel(String title, String backdrop_path, String release_date, int movie_id, float vote_average, String movie_overview, String original_language) {
        this.title = title;
        this.backdrop_path = backdrop_path;
        this.release_date = release_date;
        this.movie_id = movie_id;
        this.vote_average = vote_average;
        this.movie_overview = movie_overview;
        this.original_language = original_language;
    }

    //    Constructor
//    public MovieModel(String title, String backdrop_path, String release_date, int movie_id, float vote_average, String movie_overview,
//                      int runtime) {
//        this.title = title;
//        this.backdrop_path = backdrop_path;
//        this.release_date = release_date;
//        this.movie_id = movie_id;
//        this.vote_average = vote_average;
//        this.movie_overview = movie_overview;
//        this.runtime = runtime;
//    }

    protected MovieModel(Parcel in) {
        title = in.readString();
        backdrop_path = in.readString();
        release_date = in.readString();
        movie_id = in.readInt();
        vote_average = in.readFloat();
        movie_overview = in.readString();
       original_language = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    //    Getters
    public String getTitle() {
        return title;
    }

    public String getbackdrop_path() {
        return backdrop_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getMovie_overview() {
        return movie_overview;
    }

//    public int getRuntime() {return runtime;}


    public String getOriginal_language() {
        return original_language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(backdrop_path);
        dest.writeString(release_date);
        dest.writeInt(movie_id);
        dest.writeFloat(vote_average);
        dest.writeString(movie_overview);
        dest.writeString(original_language);

    }

    @NonNull
    @Override
    public String toString() {
        return getTitle()+"\n" + getMovie_id()+"\n" + getMovie_overview()+"\n";
    }
}