/**
 * Copyright @2020-03-29 Fih-foxconn All Rights Reserved
 */
package com.fih.auth.server.service.impl;
import com.fih.auth.server.dao.IOauthUserDao;
import com.fih.auth.server.model.OauthUser;
import com.fih.auth.server.service.IOauthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
    * 用户表 服务实现类
    * </p>
 *
 * @author xiaolong.song
 * @since 2020-03-29
 */
@Service
public class OauthUserServiceImpl implements IOauthUserService {

    @Autowired
    private IOauthUserDao iOauthUserDao;

    @Override
    public OauthUser getOauthUser(OauthUser user) {
        return iOauthUserDao.getOauthUser(user);
    }
}
