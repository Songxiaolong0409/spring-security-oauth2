package com.fih.auth.server.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fih.auth.server.model.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 自定义token异常信息
 *
 * @author songxiaolong
 * @since 2020-03-28
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws  ServletException {

        Result result=new Result();
        result.setCode(HttpServletResponse.SC_UNAUTHORIZED);
        result.setMsg(authException.getMessage());
        result.setData(String.valueOf(new Date().getTime()));

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), result);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}
