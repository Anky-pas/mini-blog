package ankypas.miniblog.Api;

import ankypas.miniblog.Dao.Account;
import ankypas.miniblog.Service.AccountService;
import ankypas.miniblog.Tools.EmailUtil;
import ankypas.miniblog.Tools.JwtUtil;
import ankypas.miniblog.Tools.VerifyUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

@RestController
@RequestMapping("/Account")
@CrossOrigin(origins = "*")
@Api(tags = "账号接口", description = "对账号进行操作")
public class AccountController {

    @Autowired
    private AccountService accountService;

    //邮箱验证码
    private String emailCode;

    //图形验证码
    private String verifyCode;

    //响应结果
    private JSONObject result;

    /**
     * 生成图形验证码
     * @param response
     * @throws Exception
     */
    @GetMapping("/verificationCode")
    @ApiOperation("获取验证码图片")
    public void verificationCode(HttpServletResponse response)throws Exception{
        //生成验证码图片
        Object[] result = VerifyUtil.createImage();

        //接收验证码字符串
        verifyCode = (String)result[0];

        //接收验证码图片
        BufferedImage image = (BufferedImage) result[1];

        //获取输出流
        ServletOutputStream outputStream = response.getOutputStream();
        ByteArrayOutputStream t = new ByteArrayOutputStream();
        ImageIO.write(image,"png",t);
        outputStream.write(Base64.encodeBase64(t.toByteArray()));
    }

    @PostMapping("/register")
    @ApiOperation("注册账号")
    public String register(@RequestBody HashMap<String, String> map){
        result = new JSONObject();
        //校验邮箱
        if(!emailCode.equals(map.get("emailCode"))){
            result.put("code",500);
            result.put("msg","验证码错误！");
            return result.toJSONString();
        }
        Account account = new Account(map.get("name"),map.get("phone"),map.get("email"),map.get("password"));
        int statu = accountService.register(account);
        switch(statu){
            case 0:
                result.put("code",200);
                result.put("msg","注册成功！");
                break;
            case 1:
                result.put("code",500);
                result.put("msg","用户名已被使用！");
                break;
            case 2:
                result.put("code",500);
                result.put("msg","手机号已被绑定！");
                break;
            case 3:
                result.put("code",500);
                result.put("msg","用户名已被使用！手机号已被绑定！");
        }
        return result.toJSONString();
    }

    @PostMapping("/login")
    @ApiOperation("登录账号")
    public String login(@RequestBody HashMap<String, String> map){
        result = new JSONObject();
        //校验图形验证码
        if(verifyCode == null || !verifyCode.equals(map.get("verifyCode"))){
            result.put("code",500);
            result.put("msg","验证码错误！");
            return result.toJSONString();
        }
        //登录
        int id;
        if(map.get("login").equals("name")) id = accountService.loginByName(map.get("name"),map.get("password"));
        else id = accountService.loginByPhone(map.get("phone"),map.get("password"));
        //登录成功，签发签名
        if(id > 0){
            String token = JwtUtil.sign(id);
            result.put("code",200);
            result.put("msg","登录成功！");
            result.put("userName",accountService.getUserNameByUserId(id));
            result.put("headImage","");
            result.put("token",token);
        }
        else{
            result.put("code",500);
            result.put("msg","用户名或密码错误！");
        }
        return result.toJSONString();
    }

    @PostMapping("/editPassword")
    @ApiOperation("修改密码")
    public String editPassword(@RequestBody HashMap<String, String> map){
        result = new JSONObject();
        //校验邮箱验证码
        if(emailCode == null || !emailCode.equals(map.get("emailCode"))){
            result.put("code",500);
            result.put("msg","验证码错误！");
            return result.toJSONString();
        }
        //修改密码
        if(accountService.editPassword(map.get("password"),map.get("name"))){
            result.put("code",200);
            result.put("msg","密码修改成功！");
        }
        else{
            result.put("code",500);
            result.put("msg","密码修改失败！");
        }
        return result.toJSONString();
    }

    @GetMapping("/emailCode")
    @ApiOperation("发送邮箱验证码")
    public String emailCode(@RequestParam(name = "email") String email, @RequestParam(name = "operate") String operate){
        result = new JSONObject();
        if(operate.equals("register")) emailCode = EmailUtil.send(email,"您正在注册“mini-blog”账户");
        else if(operate.equals("editPassword")) emailCode = EmailUtil.send(email,"您正在修改“mini-blog”账户密码");
        else emailCode = null;
        if( emailCode == null){
            result.put("code",500);
            result.put("msg","验证码发送失败，请重试！");
            return result.toJSONString();
        }
        result.put("code",200);
        result.put("msg","验证码发送成功！");
        return result.toJSONString();
    }
}
