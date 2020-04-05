package com.fih.auth.server.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fih.auth.server.model.Result;
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
        Result result=new Result();
        result.setCode(HttpServletResponse.SC_UNAUTHORIZED);
        result.setMsg(accessDeniedException.getMessage());
        result.setData(String.valueOf(new Date().getTime()));

        response.setContentType("application/json;charset=UTF-8");
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
