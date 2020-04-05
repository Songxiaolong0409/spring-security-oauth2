package com.fih.auth.server.configurer;

import com.fih.auth.server.exception.CustomWebResponseExceptionTranslator;
import com.fih.auth.server.granter.CustomRefreshTokenGranter;
import com.fih.auth.server.granter.MobilePasswordCustomTokenGranter;
import com.fih.auth.server.granter.MobileSmsCustomTokenGranter;
import com.fih.auth.server.model.OauthUser;
import com.fih.auth.server.service.CustomUserDetailsService;
import com.fih.auth.server.service.IOauthUserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.*;

/**
 * @author songxiaolong
 * @CreateDate: 2020-03-28
 * @Description: 授权服务配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private CustomWebResponseExceptionTranslator webResponseExceptionTranslator;

    @Autowired
    private IOauthUserService oauthUserService;

    //认证管理 需要在WebSecurityConfig 中声明authenticationManager bean
    @Autowired
    private AuthenticationManager authenticationManager;


    //这个是定义授权的请求的路径的Bean
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore jwtStore() {
//        TokenStore tokenStore = new JwtTokenStore(accessTokenConverter());
        TokenStore tokenStore=new RedisTokenStore(connectionFactory);
        return tokenStore;
    }

    /**
     * password 方式认证需要
     *
     * @return
     */
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(daoAuthenticationProvider());
        return new ProviderManager(providers, authenticationManager);
    }

    /**
     * password 方式认证需要
     *
     * @return
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * 定义令牌端点上的安全约束。
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("isAuthenticated()")
                //允许 check_token 访问
                .checkTokenAccess("permitAll()");
    }

    /**
     * 将ClientDetailsServiceConfigurer（从您的回调AuthorizationServerConfigurer）可以用来在内存或JDBC实现客户的细节服务来定义的。客户端的重要属性是
     * clientId：（必填）客户端ID。
     * secret:(可信客户端需要）客户机密码（如果有）。没有可不填
     * scope：客户受限的范围。如果范围未定义或为空（默认值），客户端不受范围限制。read write all
     * authorizedGrantTypes：授予客户端使用授权的类型。默认值为空。
     * authorities授予客户的授权机构（普通的Spring Security权威机构）。
     * 客户端的详细信息可以通过直接访问底层商店（例如，在数据库表中JdbcClientDetailsService）或通过ClientDetailsManager接口（这两种实现ClientDetailsService也实现）来更新运行的应用程序。
     * 注意：JDBC服务的架构未与库一起打包（因为在实践中可能需要使用太多变体）
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /*
        内存方式
        clients.inMemory()
                .withClient("personality")
                .secret(passwordEncoder().encode("personality"))
                .scopes("all")
                .authorizedGrantTypes("pwd", "sms", "refresh_token","authorization_code","password");
        */
        //数据库方式
        clients.withClientDetails(clientDetails());
    }

    /**
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(this.authenticationManager);

        endpoints.accessTokenConverter(accessTokenConverter());//jwt
        //从jwt来数据
        endpoints.tokenStore(jwtStore());
        List<TokenGranter> tokenGranters = getTokenGranters(endpoints.getAuthorizationCodeServices(),
                endpoints.getTokenStore(), endpoints.getTokenServices(), endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory());

        endpoints.exceptionTranslator(webResponseExceptionTranslator);
        endpoints.tokenGranter(new CompositeTokenGranter(tokenGranters));
        /*endpoints.tokenEnhancer(new TokenEnhancer() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
                DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) oAuth2AccessToken;
                CustomUser user = (CustomUser) oAuth2Authentication.getPrincipal();
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("nickname", user.getNickname());
                map.put("mobile", user.getMobile());
                map.put("avatar",user.getAvatar());
                token.setAdditionalInformation(map);
                return oAuth2AccessToken;
            }
        });*/
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    private List<TokenGranter> getTokenGranters(AuthorizationCodeServices authorizationCodeServices,
                                                TokenStore tokenStore, AuthorizationServerTokenServices tokenServices,
                                                ClientDetailsService clientDetailsService,
                                                OAuth2RequestFactory requestFactory) {

        return new ArrayList<>(Arrays.asList(
                new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetailsService, requestFactory),
                new CustomRefreshTokenGranter(true,tokenStore, tokenServices,clientDetailsService,requestFactory),
                new MobilePasswordCustomTokenGranter(userDetailsService, tokenServices, clientDetailsService, requestFactory),
                new MobileSmsCustomTokenGranter(userDetailsService, tokenServices, clientDetailsService, requestFactory),
                new ResourceOwnerPasswordTokenGranter(authenticationManager(),tokenServices,clientDetails(),requestFactory)
        ));
    }

    //定义jwttoken的某些属性
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {

        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter() {
            /**
             * 重写增强token的方法
             * 自定义返回相应的信息
             *
             */

            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

//                String userName = authentication.getUserAuthentication().getName();
                // 与登录时候放进去的UserDetail实现类一直查看link{SecurityConfiguration}
                OauthUser oauthUser=null;
                Object object=authentication.getUserAuthentication().getPrincipal();
                if(object instanceof OauthUser){
                    oauthUser = (OauthUser)object;
                }else{
                    oauthUser= (OauthUser) JSONObject.toBean(JSONObject.fromObject(object),OauthUser.class);
                }
                if(oauthUser==null){
                    oauthUser=new OauthUser();
                    oauthUser.setEmail(oauthUser.getEmail());
                    oauthUser.setAreaCode(oauthUser.getAreaCode());
                    oauthUser.setMobile(oauthUser.getMobile());
                    oauthUser.setUsername(oauthUser.getUsername());
                    oauthUser=oauthUserService.getOauthUser(oauthUser);
                }
                /** 自定义一些token属性 ***/
                oauthUser.setPassword(null);
                Map<String, Object> additionalInformation = new HashMap<>();
                additionalInformation.put("user_id",oauthUser.getUserId());
                additionalInformation.put("user_name", oauthUser);//必须返回user_name字段
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
                return enhancedToken;
            }

        };
        // 测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
        accessTokenConverter.setSigningKey("JwtR_Distributed_Framework_Token");
        return accessTokenConverter;

    }

    public static void main(String[] args){

        String token1="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJDdXN0b21Vc2VyKG5pY2tuYW1lPeWMv-WQjeiAhSwgbW9iaWxlPWFkbWluLCBhdmF0YXI9aHR0cHM6Ly93d3cuZ29vZ2xlLmNuL2Zhdmljb24uaWNvLCBhdXRob3JpdGllcz1bYWRtaW4sIHVzZXIsIHJvb3RdKSIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE1ODU0MTgyNzcsInVzZXJOYW1lIjoiYWRtaW4iLCJhdXRob3JpdGllcyI6WyJhZG1pbiIsInVzZXIiLCJyb290Il0sImp0aSI6ImVmOThmYTA1LWUxYzYtNDc4Yi04YzU5LTZkYTEzMWUxNTc0YiIsImNsaWVudF9pZCI6ImRyaXZlcl9pb3MifQ.CT__yRcK7Y1LbO-VXJllbW_3ZRS_TYtxecwu8kLeN_c";
        String token2="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJDdXN0b21Vc2VyKG5pY2tuYW1lPeWMv-WQjeiAhSwgbW9iaWxlPWFkbWluLCBhdmF0YXI9aHR0cHM6Ly93d3cuZ29vZ2xlLmNuL2Zhdmljb24uaWNvLCBhdXRob3JpdGllcz1bYWRtaW4sIHVzZXIsIHJvb3RdKSIsInNjb3BlIjpbImFsbCJdLCJhdGkiOiJlZjk4ZmEwNS1lMWM2LTQ3OGItOGM1OS02ZGExMzFlMTU3NGIiLCJleHAiOjE1ODU0MzYyNzcsInVzZXJOYW1lIjoiYWRtaW4iLCJhdXRob3JpdGllcyI6WyJhZG1pbiIsInVzZXIiLCJyb290Il0sImp0aSI6IjdjMjM2YjA0LWJkNDItNDM1Yi1iY2Y2LWYzNjlkYjQwMWY5NCIsImNsaWVudF9pZCI6ImRyaXZlcl9pb3MifQ.1UMNrMprJDuIw7d67Aqlc66Rv4YEOtmJRpwxF_5OZEc";
        JwtHelper.decode(token1).getClaims();
    }

}
