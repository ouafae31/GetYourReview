package com.app.getyourreviews.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.app.getyourreviews.R;
import com.app.getyourreviews.model.Review;

import java.util.Date;
import java.util.List;

/**
 * Created by ouafaebenelkadi on 5/18/16.
 */
public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {

    private List<Review> reviewsList;

    private Context context;

    public ReviewsAdapter(List<Review> reviewsList, Context context) {
        this.reviewsList = reviewsList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    @Override
    public void onBindViewHolder(final ReviewsViewHolder reviewsViewHolder, int i) {
        Review review = reviewsList.get(i);
        reviewsViewHolder.title.setText(review.getTitle());
        // convert date into textView
        reviewsViewHolder.date.setText(review.getDate().toString());
        reviewsViewHolder.author.setText(review.getAuthor());
        reviewsViewHolder.message.setText(review.getMessage());
        reviewsViewHolder.ratingBar.setRating(review.getRating());

    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    /** Called to add a review to the recycler view. */

    public void addReview(Review review) {
        reviewsList.add(review);
        notifyItemInserted(reviewsList.size() - 1);
    }


    @Override
    public ReviewsViewHolder onCreateViewHolder(ViewGroup viewGroup,int viewType) {
        ReviewsViewHolder viewHolder;
        View reviewsView;
        context = viewGroup.getContext();
        Log.w("myApp", "view is empty" + reviewsList.size());
        reviewsView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review, viewGroup, false);
        viewHolder = new ReviewsViewHolder(reviewsView);

        return viewHolder;
    }


    public static class ReviewsViewHolder extends RecyclerView.ViewHolder {

        protected TextView title, author, date, message;

        protected RatingBar ratingBar;


        public ReviewsViewHolder(View v) {
            super(v);

            title = (TextView) v.findViewById(R.id.title);
            date = (TextView) v.findViewById(R.id.date);
            author = (TextView) v.findViewById(R.id.author);
            message = (TextView) v.findViewById(R.id.review);
            ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);


        }


    }

}



