package com.fih.auth.server.granter;

import com.fih.auth.server.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出清空accessToken
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private static String BEARER_AUTHENTICATION = "Bearer";

    private static String HEADER_AUTHENTICATION = "authorization";

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String auth = httpServletRequest.getHeader(HEADER_AUTHENTICATION);
        String token = httpServletRequest.getParameter("access_token");
        if (auth != null && auth.startsWith(BEARER_AUTHENTICATION)) {
            token = auth.split(" ")[1];
        }

        if (token != null) {
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
            if (accessToken != null) {
                tokenStore.removeAccessToken(accessToken);

                Result.print200(httpServletResponse,"Successfully cleared token");
            }
            Result.print401(httpServletResponse,"Failed to clear token,Token invalid");
        }

        Result.print401(httpServletResponse,"Failed to clear token,Parameter in header does not exist");
    }
}
