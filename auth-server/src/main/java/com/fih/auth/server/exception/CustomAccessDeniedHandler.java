package com.fih.auth.server.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fih.auth.server.common.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


/**
 * @author songxiaolong
 * @CreateDate: 2020-03-28
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result.print401(response,accessDeniedException.getMessage());
    }
}
