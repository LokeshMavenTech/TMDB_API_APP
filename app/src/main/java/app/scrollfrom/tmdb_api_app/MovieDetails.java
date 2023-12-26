package app.scrollfrom.tmdb_api_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import app.scrollfrom.tmdb_api_app.models.MovieModel;

public class MovieDetails extends AppCompatActivity {

    private ImageView imageViewDetails;
    private TextView titleDetails, overviewDetails, vote,duration,releseDate;
    private RatingBar ratingBarDetails;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        imageViewDetails = findViewById(R.id.imageView_details);
        titleDetails = findViewById(R.id.textView_title_details);
//        overviewDetails = findViewById(R.id.textView_desc_details);
        ratingBarDetails = findViewById(R.id.ratingBar_details);
        vote = findViewById(R.id.textViewVote);
        overviewDetails = findViewById(R.id.movie_overView);
        duration=findViewById(R.id.movieRunTime);
        releseDate = findViewById(R.id.releseDate);
        /////



// Set properties for multiline support

        GetDataFromIntent();
    }

    private void GetDataFromIntent(){

        if (getIntent().hasExtra("movie")){
            MovieModel movieModel = getIntent().getParcelableExtra("movie");

            titleDetails.setText(movieModel.getTitle());

            duration.setText("Runtime : " +movieModel.getRuntime()+" Mins");

            vote.setText("Vote count : "+ movieModel.getVote_count());
//            ratingBarDetails.setRating("rating"+movieModel.getVote_average());

//           OD.setText(" " + movieModel.getMovie_overview());
            releseDate.setText("Release Date : " + movieModel.getRelease_date());
            if (movieModel != null) {
                String overview = movieModel.getMovie_overview();
                if (!TextUtils.isEmpty(overview.trim())) {
                    overviewDetails.setText(" " + overview);
                } else {
                    overviewDetails.setText("No overview available");
                }
            }



//            Log.v("Tag", "X" + movieModel.getMovie_overview());

//            Glide.with(this)
//                    .load("https://image.tmdb.org/t/p/original/"
//                            + movieModel.getBackdrop_path())
//                    .into(imageViewDetails);


            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/original/" + movieModel.getBackdrop_path())
                    .placeholder(R.drawable.defult_movie) // Set the default image resource
                    .into(imageViewDetails);


        }

    }

}