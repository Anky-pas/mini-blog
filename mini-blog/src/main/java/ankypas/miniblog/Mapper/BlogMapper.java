package ankypas.miniblog.Mapper;

import ankypas.miniblog.Dao.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {
    void add(Blog blog);
    void delete(int blogId);
    void update(Blog blog);
    List<Blog> search(@Param("key")String key, @Param("start")int start, @Param("size")int size);
    Blog searchById(int blogId);
    void addStar(int blogId);
    void deleteStar(int blogId);
    void addCollect(int blogId);
    void deleteCollect(int blogId);
    void addBrowse(int blogId);
    int getUserIdByBlogId(int blogId);
}
