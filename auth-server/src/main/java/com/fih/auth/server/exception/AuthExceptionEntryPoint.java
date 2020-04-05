package com.fih.auth.server.exception;

import com.fih.auth.server.common.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        Result.print401(response,authException.getMessage());
    }
}
