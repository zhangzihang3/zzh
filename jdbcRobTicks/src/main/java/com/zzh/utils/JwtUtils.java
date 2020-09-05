package com.zzh.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author zzh
 *
 * 有效载荷部分，是JWT的主体内容部分，也是一个JSON对象，包含需要传递的数据。 JWT指定七个默认字段供选择。
 * iss：发行人
 * exp：到期时间
 * sub：主题
 * aud：用户
 * nbf：在此之前不可用
 * iat：发布时间
 * jti：JWT ID用于标识该JWT
 *
 * JWT头部分是一个描述JWT元数据的JSON对象
 *
 * jwt 签名哈希
 * @since 2019/10/16
 */
public class JwtUtils {
    //一秒等于1000毫秒，设置过期时间为1天
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    //秘钥
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    public static String getJwtToken(String id, String nickname) {

        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")  //令牌的类型 一般都这样写
                .setHeaderParam("alg", "HS256") //签名使用的算法，名字随便取 一般都这样写
                .setSubject("zzh-user")
                .setIssuedAt(new Date()) //签发时间
                //System.currentTimeMillis()获取当前毫秒
                //设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                // Claim是一些实体（通常指的用户）的状态和额外的元数据
                .claim("id", id)
                .claim("nickname", nickname)
                //添加防伪标志 hs256算法对头与有效载荷加密，加入秘钥
                .signWith(SignatureAlgorithm.HS256, APP_SECRET) //防伪标志
                .compact();

        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     *
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 根据token获取会员id
     *
     * @param request
     * @return
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("jwtToken");
        if (StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("id");
    }
}
