package ankypas.miniblog.Dao;

import java.sql.Date;

public class BlogOverview {
    private int blogId;
    private String title;
    private String text;
    private String userName;
    private Date date;
    private int star;
    private int collect;
    private int browse;
    private int comment;

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(int browse) {
        this.browse = browse;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }
}
