package ankypas.miniblog.Service;

import ankypas.miniblog.Dao.Comment;

import java.util.List;

public interface CommentService {
    boolean add(Comment comment);
    boolean delete(int commentId, int userId);
    int countById(int commentId);
    boolean update();
    List<Comment> find(int blogId);
}
