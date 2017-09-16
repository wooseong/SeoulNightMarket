package seoulnightmarket.seoulnightmarket.data;

import android.graphics.drawable.Drawable;

/**
 * Created by Yookmoonsu on 2017-09-16.
 */

public class ReviewListViewItem {
    private Drawable userImage;
    private String userName;
    private Drawable starImage;
    private String reviewDate;
    private String reviewText;

    public ReviewListViewItem() {

    }

    public Drawable getUserImage() {
        return this.userImage;
    }

    public String getUserName() {
        return this.userName;
    }

    public Drawable getStarImage() {
        return this.starImage;
    }

    public String getReviewDate() {
        return this.reviewDate;
    }

    public String getReview() {
        return this.reviewText;
    }

    public void setUserImage(Drawable image) {
        userImage = image;
    }

    public void setUserName(String name) {
        userName = name;
    }

    public void setStarImage(Drawable star) {
        starImage = star;
    }

    public void setReviewDate(String date) {
        reviewDate = date;
    }

    public void setReview(String review) {
        reviewText = review;
    }
}