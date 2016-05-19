package com.app.getyourreviews.api;

import com.app.getyourreviews.model.ListReviews;
import com.app.getyourreviews.model.Review;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by ouafaebenelkadi on 5/18/16.
 */
public interface ReviewsAPI {

        @GET("berlin-l17/tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776/reviews.json?page=0&rating=0&sortBy=date_of_review&direction=DESC")
        Call<ListReviews> loadReviews();

        @POST("berlin-l17/tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776/")
        Call<Review> createTask(@Body Review review);


}
