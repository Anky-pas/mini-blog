package ankypas.miniblog.Api;

import ankypas.miniblog.Dao.Blog;
import ankypas.miniblog.Dao.BlogOverview;
import ankypas.miniblog.Service.AccountService;
import ankypas.miniblog.Service.BlogService;
import ankypas.miniblog.Service.CommentService;
import ankypas.miniblog.Tools.JwtUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/Blog")
@RestController
@Api(tags = "博客接口", description = "对博客进行操作")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CommentService commentService;

    private JSONObject result;

    /**
     * 获取用户id，若未登录，返回-1
     * @param request
     * @return 用户id
     */
    private int getUserId(HttpServletRequest request){
        //获取token
        String token = request.getHeader("Authorization");
        //校验token
        if(JwtUtil.verify(token)) return JwtUtil.getUserId(token);
        else return -1;
    }

    /**
     * 新建博客
     * @param map
     * @param request
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("新建博客")
    @ApiImplicitParam(name = "Authorization",value = "token",paramType = "header", dataType = "String",required = true)
    public String add(@RequestBody HashMap<String, String> map, HttpServletRequest request){
        result = new JSONObject();
        int userId = getUserId(request);
        if( userId < 0){
            result.put("code",400);
            result.put("msg","请先登录！");
        }
        else{
            Blog blog = new Blog(map.get("title"),map.get("text"),userId,0,0,0);
            if(blogService.add(blog)){
                result.put("code",200);
                result.put("msg","发布成功！");
            }
            else{
                result.put("code",500);
                result.put("msg","发布失败！");
            }
            //签发新的token
            result.put("token",JwtUtil.sign(userId));
        }
        return result.toJSONString();
    }

    /**
     * 删除博客
     * @param blogId
     * @param request
     * @return
     */
    @GetMapping("/delete")
    @ApiOperation("删除博客")
    @ApiImplicitParam(name = "Authorization",value = "token",paramType = "header", dataType = "String",required = true)
    public String delete(@RequestParam(name = "blogId") String blogId, HttpServletRequest request){
        result = new JSONObject();
        int userId = getUserId(request);
        if( userId < 0){
            result.put("code",400);
            result.put("msg","请先登录！");
        }
        else{
            if(blogService.delete(Integer.parseInt(blogId),userId)){
                result.put("code",200);
                result.put("msg","删除成功！");
            }
            else{
                result.put("code",500);
                result.put("msg","删除失败！");
            }
            //签发新的token
            result.put("token",JwtUtil.sign(userId));
        }
        return result.toJSONString();
    }

    /**
     * 编辑博客
     * @param map
     * @param request
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("编辑博客")
    @ApiImplicitParam(name = "Authorization", value = "token", paramType = "header", dataType = "String",required = true)
    public String update(@RequestBody HashMap<String, String> map, HttpServletRequest request){
        result = new JSONObject();
        int userId = getUserId(request);
        if( userId < 0){
            result.put("code",400);
            result.put("msg","请先登录！");
        }
        else{
            Blog blog = new Blog(Integer.parseInt(map.get("blogId")),map.get("title"),map.get("text"));
            if(blogService.update(blog)){
                result.put("code",200);
                result.put("msg","编辑成功！");
            }
            else{
                result.put("code",500);
                result.put("msg","编辑失败！");
            }
            //签发新的token
            result.put("token",JwtUtil.sign(userId));
        }
        return result.toJSONString();
    }

    /**
     * 搜索博客
     * @param key
     * @return
     */
    @GetMapping("/search")
    @ApiOperation("通过关键字搜索博客")
    public String search(@RequestParam(name = "key") String key, @RequestParam(name = "page") String page, @RequestParam(name = "size") String size){
        result = new JSONObject();
        List<Blog> data = blogService.search(key,Integer.parseInt(page),Integer.parseInt(size));
        LinkedList<BlogOverview> overview = new LinkedList<>();
        if( data != null){
            for(Blog blog: data){
                BlogOverview blogOverview = new BlogOverview();
                blogOverview.setBlogId(blog.getBlogId());
                blogOverview.setTitle(blog.getTitle());
                blogOverview.setUserName(accountService.getUserNameByUserId(blog.getUserId()));
                blogOverview.setBrowse(blog.getBrowse());
                blogOverview.setCollect(blog.getCollect());
                blogOverview.setDate(blog.getDate());
                blogOverview.setStar(blog.getStar());
                blogOverview.setComment(commentService.countById(blog.getBlogId()));
                if(blog.getText().length() > 50) blogOverview.setText(blog.getText().substring(0,50) + "...");
                else blogOverview.setText(blog.getText());
                overview.addLast(blogOverview);
            }
        }
        result.put("code",200);
        result.put("data",overview);
        result.put("msg","搜索成功！");
        return result.toJSONString();
    }

    @GetMapping("/searchById")
    @ApiOperation("通过博客ID搜索博客")
    public String searchById(@RequestParam(name = "blogId") int blogId){
        result = new JSONObject();
        Blog blog = blogService.searchById(blogId);
        result.put("code",200);
        result.put("data",blog);
        result.put("msg","搜索成功！");
        return result.toJSONString();
    }

    /**
     * 点赞
     * @param blogId
     * @param request
     * @return
     */
    @GetMapping("/addStar")
    @ApiOperation("点赞博客")
    @ApiImplicitParam(name = "Authorization",value = "token",paramType = "header", dataType = "String",required = true)
    public String addStar(@RequestParam(name = "blogId") String blogId, HttpServletRequest request){
        result = new JSONObject();
        int userId = getUserId(request);
        if( userId < 0){
            result.put("code",400);
            result.put("msg","请先登录！");
        }
        else{
            if(blogService.addStar(Integer.parseInt(blogId))){
                result.put("code",200);
                result.put("msg","点赞成功！");
            }
            else{
                result.put("code",500);
                result.put("msg","点赞失败！");
            }
            //签发新的token
            result.put("token",JwtUtil.sign(userId));
        }
        return result.toJSONString();
    }

    /**
     * 取消点赞
     * @param blogId
     * @param request
     * @return
     */
    @GetMapping("/deleteStar")
    @ApiOperation("取消点赞博客")
    @ApiImplicitParam(name = "Authorization",value = "token",paramType = "header", dataType = "String",required = true)
    public String deleteStar(@RequestParam(name = "blogId") String blogId, HttpServletRequest request){
        result = new JSONObject();
        int userId = getUserId(request);
        if( userId < 0){
            result.put("code",400);
            result.put("msg","请先登录！");
        }
        else{
            if(blogService.deleteStar(Integer.parseInt(blogId))){
                result.put("code",200);
                result.put("msg","取消点赞成功！");
            }
            else{
                result.put("code",500);
                result.put("msg","取消点赞失败！");
            }
            //签发新的token
            result.put("token",JwtUtil.sign(userId));
        }
        return result.toJSONString();
    }

    /**
     * 浏览量+1
     * @param blogId
     */
    @GetMapping("/read")
    @ApiOperation("博客浏览量+1")
    public String read(@RequestParam(name = "blogId") String blogId){
        result = new JSONObject();
        if(blogService.read(Integer.parseInt(blogId))){
            result.put("code",200);
            result.put("msg","浏览数+1操作成功！");
        }
        else{
            result.put("code",500);
            result.put("msg","浏览数+1操作失败！");
        }
        return result.toJSONString();
    }
}
