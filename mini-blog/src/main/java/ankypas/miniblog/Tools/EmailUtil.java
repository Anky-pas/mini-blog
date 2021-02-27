package ankypas.miniblog.Tools;

import org.apache.commons.mail.HtmlEmail;

import java.util.Random;

public class EmailUtil {
    public static String send(String email, String msg){
        String code = createCode();
        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setHostName("smtp.qq.com");
        htmlEmail.setCharset("utf-8");
        try {
            htmlEmail.addTo(email);
            htmlEmail.setFrom("e_mail.auto@foxmail.com","admin");
            htmlEmail.setAuthentication("e_mail.auto@foxmail.com", "hhzteqmuppdwcjfd");
            htmlEmail.setSubject("验证码");
            htmlEmail.setMsg(msg + "，本次注册的验证码是：" + code);
            htmlEmail.send();
        }catch (Exception e){
            return null;
        }
        return code;
    }
    public static String createCode(){
        //预定义验证码字符集
        String codeList = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";
        String code = "";
        int len = codeList.length();
        Random random = new Random();
        for(int i=0;i<6;i++){
            code = code + codeList.charAt(random.nextInt(len));
        }
        return code;
    }
}
