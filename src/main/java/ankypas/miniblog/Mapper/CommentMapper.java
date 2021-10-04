package ankypas.miniblog.Mapper;

import ankypas.miniblog.Dao.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    void add(Comment comment);
    int countByCommentIdAndUserId(int commentId, int userId);
    int countByCommentId(int commentId);
    void delete(int commentId);
    void update();
    List<Comment> find(int blogId);
}
