package com.zzh.controller;

import com.zzh.bean.Person;
import com.zzh.service.aspects.limit.Limit;
import com.zzh.service.aspects.log.SysLog;
import com.zzh.utils.JwtUtils;
import com.zzh.utils.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class logController {
    @PostMapping("/login")
    @SysLog(description = "用户登录")
    @Limit(maxLimit = 20)
    public R login(@RequestBody Person person, HttpServletRequest servletRequest) {
        String jwtToken = servletRequest.getHeader("jwtToken");
        if (jwtToken == null) {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                    person.getUsername()
                    , person.getPassword());
            Subject subject = SecurityUtils.getSubject();
            subject.login(usernamePasswordToken);
            jwtToken = JwtUtils.getJwtToken(person.getPassword(), person.getUsername());
            return R.ok().put("jwtToken", jwtToken);
        } else {
            String id = JwtUtils.getMemberIdByJwtToken(servletRequest);
            return R.ok().put("id", id);
        }
    }
}
