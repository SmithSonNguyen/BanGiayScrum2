package entity;

import java.util.Date;

public class Review {
    private int maReview;      // Thêm trường maReview
    private int accountID;
    private int productID;
    private String contentReview;
    private Date dateReview;
    private int rating;        // Thêm trường rating cho đánh giá sao

    // Constructor đầy đủ các tham số
    public Review(int maReview, int accountID, int productID, String contentReview, Date dateReview, int rating) {
        this.maReview = maReview;
        this.accountID = accountID;
        this.productID = productID;
        this.contentReview = contentReview;
        this.dateReview = dateReview;
        this.rating = rating;  // Gán giá trị rating
    }

    // Constructor không bao gồm maReview (khi thêm mới vào DB)
    public Review(int accountID, int productID, String contentReview, Date dateReview, int rating) {
        this.accountID = accountID;
        this.productID = productID;
        this.contentReview = contentReview;
        this.dateReview = dateReview;
        this.rating = rating;  // Gán giá trị rating
    }

    public Review() {
    }

    // Getter và setter cho các thuộc tính
    public int getMaReview() {
        return maReview;
    }

    public void setMaReview(int maReview) {
        this.maReview = maReview;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getContentReview() {
        return contentReview;
    }

    public void setContentReview(String contentReview) {
        this.contentReview = contentReview;
    }

    public Date getDateReview() {
        return dateReview;
    }

    public void setDateReview(Date dateReview) {
        this.dateReview = dateReview;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;  // Cập nhật giá trị rating
    }

    @Override
    public String toString() {
        return "Review [maReview=" + maReview + ", accountID=" + accountID + ", productID=" + productID
                + ", contentReview=" + contentReview + ", dateReview=" + dateReview + ", rating=" + rating + "]";
    }
}
