package ankypas.miniblog.Tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JwtUtil {
    private static final long EXPIRE_TIME = 15 * 60 * 1000;     //过期时间15分钟
    private static final String TOKEN_SECRET = "mini_blog";     //token私钥

    /**
     * 校验token是否正确
     */
    public static boolean verify(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 获得token中包含的用户id
     */
    public static int getUserId(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("id").asInt();
        }catch (Exception e){
            return -1;
        }
    }

    /**
     *根据用户 id 生成 token
     * @param id
     * @return 加密的token
     */
    public static String sign(int id){
        try{
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HMAC256");
            return JWT.create()
                    .withHeader(header)
                    .withClaim("id",id)
                    .withExpiresAt(date)
                    .sign(algorithm);
        }catch (Exception e){
            return null;
        }
    }
}
