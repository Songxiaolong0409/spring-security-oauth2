package com.fih.auth.server.granter;

import com.fih.auth.server.AuthorizedGrantTypes;
import com.fih.auth.server.model.OauthUser;
import com.fih.auth.server.service.CustomUserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

/**
 * @author songxiaolong
 * @CreateDate: 2020-03-28
 * @Description:
 */
public class MobileSmsCustomTokenGranter extends AbstractCustomTokenGranter {

    protected CustomUserDetailsService userDetailsService;

    public MobileSmsCustomTokenGranter(CustomUserDetailsService userDetailsService, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, AuthorizedGrantTypes.SMS.getVal());
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected OauthUser getOauthUser(Map<String, String> parameters) {
        String client_id = parameters.get("client_id");
        String area_code = parameters.get("area_code");
        String mobile = parameters.get("mobile");
        String smscode = parameters.get("smscode");
        return userDetailsService.loadUserByMobileAndSmscode(area_code,mobile, smscode,client_id);
    }

}
