package ankypas.miniblog.ServiceImpl;

import ankypas.miniblog.Dao.Blog;
import ankypas.miniblog.Mapper.BlogMapper;
import ankypas.miniblog.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public boolean add(Blog blog) {
        try{
            blogMapper.add(blog);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int blogId, int userId) {
        try{
            if( userId != blogMapper.getUserIdByBlogId(blogId)) return false;
            blogMapper.delete(blogId);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Blog blog) {
        try{
            blogMapper.update(blog);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Blog searchById(int blogId) {
        try{
            Blog blog = blogMapper.searchById(blogId);
            return blog;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Blog> search(String key, int page, int size) {
        if(key == null || key.length() == 0) return null;
        key = "%" + key + "%";
        try{
            return blogMapper.search(key,(page-1)*size,size);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean read(int blogId) {
        try{
            blogMapper.addBrowse(blogId);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean addStar(int blogId) {
        try{
            blogMapper.addStar(blogId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteStar(int blogId) {
        try{
            blogMapper.deleteStar(blogId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean addCollect(int blogId) {
        try{
            blogMapper.addCollect(blogId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteCollect(int blogId) {
        try{
            blogMapper.deleteCollect(blogId);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
