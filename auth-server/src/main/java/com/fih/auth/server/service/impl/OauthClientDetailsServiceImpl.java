/**
 * Copyright @2020-03-29 Fih-foxconn All Rights Reserved
 */
package com.fih.auth.server.service.impl;
import com.fih.auth.server.AuthorizedGrantTypes;
import com.fih.auth.server.dao.IOauthClientDetailsDao;
import com.fih.auth.server.exception.CustomOauthException;
import com.fih.auth.server.model.CustomRequest;
import com.fih.auth.server.model.OauthClientDetails;
import com.fih.auth.server.model.Result;
import com.fih.auth.server.service.IOauthClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * <p>
    * 认证客户端信息 服务实现类
    * </p>
 *
 * @author xiaolong.song
 * @since 2020-03-29
 */
@Slf4j
@Service
public class OauthClientDetailsServiceImpl implements IOauthClientDetailsService {

    @Autowired
    private IOauthClientDetailsDao iOauthClientDetailsDao;

    @Override
    public Result getSmsCode(CustomRequest customRequest) {
        if(AuthorizedGrantTypes.SMS.getVal().equals(customRequest.getGrant_type())){
            OauthClientDetails oauthClientDetails=iOauthClientDetailsDao.getOauthClientDetailsByClientId(customRequest.getClient_id());
            Assert.notNull(oauthClientDetails);

            if(new BCryptPasswordEncoder().matches(customRequest.getClient_secret(),oauthClientDetails.getClientSecret())){
                String smsCode=RandomStringUtils.randomNumeric(6);
                log.info("短信验证码：{}",smsCode);

                //TODO 发短信

                return Result.build();
            }else{
                throw new CustomOauthException("Send SMS Code Error: client_secret is not found.");
            }
        }
        throw new CustomOauthException("Send SMS Code Error: AuthorizedGrantTypes is not found.");
    }
}
