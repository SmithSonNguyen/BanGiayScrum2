package entity;

import java.util.Date;

public class Reply {
    private int replyID;        // ID của phản hồi
    private int reviewID;       // ID của đánh giá mà phản hồi này liên quan
    private int accountID;      // ID của tài khoản người phản hồi
    private String contentReply; // Nội dung phản hồi
    private Date dateReply;     // Ngày phản hồi

    // Constructor không tham số
    public Reply() {
    }

    // Constructor với tất cả các tham số
    public Reply(int replyID, int reviewID, int accountID, String contentReply, Date dateReply) {
        this.replyID = replyID;
        this.reviewID = reviewID;
        this.accountID = accountID;
        this.contentReply = contentReply;
        this.dateReply = dateReply;
    }

    // Getter và Setter cho các thuộc tính
    public int getReplyID() {
        return replyID;
    }

    public void setReplyID(int replyID) {
        this.replyID = replyID;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getContentReply() {
        return contentReply;
    }

    public void setContentReply(String contentReply) {
        this.contentReply = contentReply;
    }

    public Date getDateReply() {
        return dateReply;
    }

    public void setDateReply(Date dateReply) {
        this.dateReply = dateReply;
    }

    // Phương thức toString() để hiển thị thông tin phản hồi
    @Override
    public String toString() {
        return "Reply [replyID=" + replyID + ", reviewID=" + reviewID + ", accountID=" + accountID
                + ", contentReply=" + contentReply + ", dateReply=" + dateReply + "]";
    }
}
