package com.fih.auth.server.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author songxiaolong
 * @CreateDate: 2020-03-28
 * @Description: client_id 参数必须，否则抛出异常
 */
@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final String PARAM="client_id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().equals("/oauth/token")) {
            if(!request.getParameterMap().containsKey(PARAM)||
                    !StringUtils.hasLength(request.getParameter(PARAM))){
                boolean hasClientBasicAuth = StringUtils.hasLength(request.getHeader(HttpHeaders.AUTHORIZATION));
                 if(!hasClientBasicAuth) {
                     throw new CustomOauthException("Client information is not included in the request:"+PARAM +" or basic auth info");
                 }
            }
        }

        filterChain.doFilter(request, response);
    }
}