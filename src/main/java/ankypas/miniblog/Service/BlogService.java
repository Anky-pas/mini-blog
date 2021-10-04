package ankypas.miniblog.Service;

import ankypas.miniblog.Dao.Blog;

import java.util.List;

public interface BlogService {
    boolean add(Blog blog);
    boolean delete(int blogId, int userId);
    boolean update(Blog blog);
    List<Blog> search(String key, int page, int size);
    Blog searchById(int blogId);
    boolean read(int blogId);
    boolean addStar(int blogId);
    boolean deleteStar(int blogId);
    boolean addCollect(int blogId);
    boolean deleteCollect(int blogId);
}
