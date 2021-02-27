package ankypas.miniblog.Dao;

import java.sql.Date;

public class Comment {
    private int commentId;
    private int blogId;
    private int parentCommentId;
    private int userId;
    private String text;
    private Date createTime;

    public Comment(int blogId, int parentCommentId, int userId, String text) {
        this.blogId = blogId;
        this.parentCommentId = parentCommentId;
        this.userId = userId;
        this.text = text;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(int parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
