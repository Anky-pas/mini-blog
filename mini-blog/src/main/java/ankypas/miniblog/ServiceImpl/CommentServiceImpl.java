package ankypas.miniblog.ServiceImpl;

import ankypas.miniblog.Dao.Comment;
import ankypas.miniblog.Mapper.CommentMapper;
import ankypas.miniblog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public boolean add(Comment comment) {
        try{
            commentMapper.add(comment);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int commentId, int userId) {
        try{
            if(commentMapper.countByCommentIdAndUserId(commentId,userId) == 0) return false;
            commentMapper.delete(commentId);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public int countById(int commentId) {
        return commentMapper.countByCommentId(commentId);
    }

    @Override
    public List<Comment> find(int blogId) {
        try{
            return commentMapper.find(blogId);
        }catch (Exception e){
            return null;
        }
    }
}
