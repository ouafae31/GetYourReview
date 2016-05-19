package com.app.getyourreviews.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import com.app.getyourreviews.R;
import com.app.getyourreviews.adapters.ReviewsAdapter;
import com.app.getyourreviews.api.ReviewsAPI;
import com.app.getyourreviews.model.ListReviews;
import com.app.getyourreviews.model.Review;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class   MainActivity extends AppCompatActivity implements Callback<ListReviews> {


    List<Review> myDataset;
    ReviewsAdapter adapter;
    RecyclerView reviewsRecyclerView;
    ArrayList<Review> data;
    Button btnSubmit;
    RatingBar ratingBar;
    EditText title,comment;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSubmit = (Button) findViewById(R.id.button);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar2);
        title = (EditText) findViewById(R.id.comment_title);
        comment = (EditText) findViewById(R.id.comment);
        btnSubmit.setEnabled(false);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        enableSubmit(title);
        enableSubmit(comment);
        //button submit review
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputTitle = title.getText().toString();
                String inputComment= title.getText().toString();
                float rate = ratingBar.getRating();
                String date = new SimpleDateFormat("MMM dd, yyyy").format(new Date());
                Review submittedReview = new Review(inputTitle,rate,date,inputComment,"Ouafae-KENYA");
                myDataset.add(submittedReview);
                adapter.notifyItemInserted(myDataset.size() - 1);
                clearAnswers();

            }
        });
        reviewsRecyclerView = (RecyclerView) findViewById(R.id.reviews_recycler_view);
        myDataset = new ArrayList<>();
        //Review reviewExample = new Review("amazing",4,"May 9, 2016","it was amazing thank you very much","Ouafae-Morocco");
        //myDataset.add(reviewExample);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        reviewsRecyclerView.setLayoutManager(layoutManager);
        loadJSON();

    }



    @Override
    public void onResponse(Response<ListReviews> response, Retrofit retrofit) {
        setProgressBarIndeterminateVisibility(false);
        ListReviews reviews = response.body();
        data = new ArrayList<>(Arrays.asList(reviews.getData()));
        myDataset.addAll(data);
        // specify an adapter
        adapter = new ReviewsAdapter(myDataset,getApplicationContext());
        reviewsRecyclerView.setAdapter(adapter);
        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "failure", Toast.LENGTH_SHORT).show();
    }



    /** Clear answers of the question */
    private void clearAnswers() {
        title.setText(" ");
        comment.setText(" ");
        ratingBar.setRating(0);

    }

    /** Called to validate input before submitting a review */
    private void enableSubmit(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                btnSubmit.setEnabled(true);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    /** Called to load reviews from JSON response */
    private void loadJSON(){
        // specify retrofit call
        setProgressBarIndeterminateVisibility(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.getyourguide.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // prepare call in Retrofit 2.0
        ReviewsAPI reviewsAPI = retrofit.create(ReviewsAPI.class);

        Call<ListReviews> call = reviewsAPI.loadReviews();
        //asynchronous call
        call.enqueue(this);

    }
    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();

    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();

    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();

    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();

    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
