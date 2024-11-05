package com.aichat.aiweb.Interceptor;

import com.aichat.aiweb.Proc.Result;
import com.aichat.aiweb.Utils.JwtUtils;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String Url = request.getRequestURL().toString();
        log.info("请求路径{}",Url);
        if(Url.contains("login")){
            log.info("登陆成功");
            return true;
        }
        String jwt = request.getHeader("token");
        if(!StringUtils.hasLength(jwt)){
            log.info("令牌为空");
            String re = JSONObject.toJSONString(Result.error("NOT_LOGIN"));
            response.getWriter().write(re);
            return false;
        }
        try{
            JwtUtils.parseClaim(jwt);
        }
        catch (Exception e){
            e.printStackTrace();
            log.info("令牌解析失败");
            String re = JSONObject.toJSONString(Result.error("ILLEGAL_TOKEN"));
            response.getWriter().write(re);
            return false;
        }
        log.info("令牌合法");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
