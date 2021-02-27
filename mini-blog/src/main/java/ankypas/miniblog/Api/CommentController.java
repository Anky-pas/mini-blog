package ankypas.miniblog.Api;

import ankypas.miniblog.Dao.Comment;
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
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/Comment")
@RestController
@Api(tags = "评论接口", description = "对评论进行操作")
public class CommentController {

    @Autowired
    CommentService commentService;

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

    @PostMapping("/add")
    @ApiOperation("新增评论")
    @ApiImplicitParam(name = "Authorization",value = "token",paramType = "header", dataType = "String",required = true)
    public String add(@RequestBody HashMap<String, String> map, HttpServletRequest request){
        result = new JSONObject();
        int userId = getUserId(request);
        if(userId > 0){
            Comment comment = new Comment(Integer.parseInt(map.get("blogId")),Integer.parseInt(map.get("parentCommentId")),userId,map.get("text"));
            if(commentService.add(comment)){
                result.put("code",200);
                result.put("msg","发布成功！");
            }
            else {
                result.put("code",500);
                result.put("msg","发布失败！");
            }
        }
        else{
            result.put("code",400);
            result.put("msg","请先登录！");
        }
        //签发新的token
        result.put("token",JwtUtil.sign(userId));
        return result.toJSONString();
    }

    @GetMapping("/delete")
    @ApiOperation("删除评论")
    @ApiImplicitParam(name = "Authorization",value = "token",paramType = "header", dataType = "String",required = true)
    public String delete(@RequestParam(name = "commentId") String commentId, HttpServletRequest request){
        result = new JSONObject();
        int userId = getUserId(request);
        if(userId > 0){
            if(commentService.delete(Integer.parseInt(commentId),userId)){
                result.put("code",200);
                result.put("msg","删除成功！");
            }
            else {
                result.put("code",500);
                result.put("msg","删除失败！");
            }
        }
        else{
            result.put("code",400);
            result.put("msg","请先登录！");
        }
        //签发新的token
        result.put("token",JwtUtil.sign(userId));
        return result.toJSONString();
    }

    @GetMapping("/find")
    @ApiOperation("查找评论")
    public String find(@RequestParam(name = "blogId") String blogId){
        result = new JSONObject();
        List<Comment> data = commentService.find(Integer.parseInt(blogId));
        result.put("code",200);
        result.put("msg","查找成功！");
        result.put("data",data);
        return result.toJSONString();
    }
}
