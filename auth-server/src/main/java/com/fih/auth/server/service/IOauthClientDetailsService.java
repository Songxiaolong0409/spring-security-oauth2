/**
 * Copyright @2020-03-29 Fih-foxconn All Rights Reserved
 */
package com.fih.auth.server.service;

import com.fih.auth.server.model.CustomRequest;
import com.fih.auth.server.model.Result;

/**
 * <p>
    * 认证客户端信息 服务类
    * </p>
 *
 * @author xiaolong.song
 * @since 2020-03-29
 */
public interface IOauthClientDetailsService {

    public Result getSmsCode(CustomRequest customRequest);
}
