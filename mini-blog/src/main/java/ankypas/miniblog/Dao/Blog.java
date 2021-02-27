package ankypas.miniblog.Dao;

import java.sql.Date;

public class Blog {

    private int blogId;
    private String title;
    private String text;
    private int userId;
    private Date date;
    private int star;
    private int collect;
    private int browse;

    public Blog(String title, String text, int userId, int star, int collect, int browse) {
        this.title = title;
        this.text = text;
        this.userId = userId;
        this.star = star;
        this.collect = collect;
        this.browse = browse;
    }

    public Blog() {
    }

    public Blog(int blogId, String title, String text) {
        this.blogId = blogId;
        this.title = title;
        this.text = text;
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
