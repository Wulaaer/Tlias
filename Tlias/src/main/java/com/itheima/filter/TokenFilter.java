package com.itheima.filter;

import com.itheima.utils.CurrentHolder;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // 1. 获取请求路径
        String url = request.getRequestURL().toString();

        // 2. 访问login则放行
        if (url.contains("login")) {
            log.info("访问login 放行");
            filterChain.doFilter(req, resp);
        }

        // 3. 获取token 判断是否为空
        String jwt = request.getHeader("token");
        if (!StringUtils.hasLength(jwt)) {
            // 4. 没有则拦截 有则校验
            log.info("令牌为空");
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return;
        }

        try {
            Claims claims = JwtUtils.parseJWT(jwt);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("Token解析成功, 放行");
        } catch (Exception e) {
            // 5. 校验失败则拦截
            e.printStackTrace();
            log.info("登录失败");
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return;
        }

        // 6. 校验通过则放行
        log.info("校验通过 放行");
        filterChain.doFilter(request, response);

        // 7. 清空ThreadLocal中存储的值（empId
        CurrentHolder.remove();
    }
}
