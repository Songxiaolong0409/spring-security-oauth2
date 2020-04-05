package com.fih.auth.server.granter;

import com.fih.auth.server.model.CustomUser;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import java.util.Map;

/**
 * @author songxiaolong
 * @CreateDate: 2020-03-28
 * @Description:
 */
public abstract class AbstractCustomTokenGranter extends AbstractTokenGranter {

    private final OAuth2RequestFactory requestFactory;

    protected AbstractCustomTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.requestFactory = requestFactory;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = tokenRequest.getRequestParameters();
        CustomUser customUser = getCustomUser(parameters);
        if (customUser == null) {
            throw new InvalidGrantException("无法获取用户信息");
        }
        OAuth2Request storedOAuth2Request = this.requestFactory.createOAuth2Request(client, tokenRequest);
        PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(customUser, null, customUser.getAuthorities());
        authentication.setDetails(customUser);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(storedOAuth2Request, authentication);
        return oAuth2Authentication;
    }

    protected abstract CustomUser getCustomUser(Map<String, String> parameters);

}
