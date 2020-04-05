package com.fih.auth.server.dao;

import com.fih.auth.server.model.CustomUser;
import com.fih.auth.server.model.OauthUser;

public interface IOauthUserDao {

    abstract CustomUser getCustomUserByMobile(String areaCode,String mobile,String clientId);

    abstract OauthUser getOauthUser(OauthUser user);
}
