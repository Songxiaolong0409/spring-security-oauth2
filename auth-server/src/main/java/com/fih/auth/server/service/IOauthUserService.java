/**
 * Copyright @2020-03-29 Fih-foxconn All Rights Reserved
 */
package com.fih.auth.server.service;

import com.fih.auth.server.model.OauthUser;

/**
 * <p>
    * 用户表 服务类
    * </p>
 *
 * @author xiaolong.song
 * @since 2020-03-29
 */
public interface IOauthUserService {

    abstract OauthUser getOauthUser(OauthUser user);
}
