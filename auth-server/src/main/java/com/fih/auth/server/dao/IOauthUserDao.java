package com.fih.auth.server.dao;

import com.fih.auth.server.model.OauthUser;

public interface IOauthUserDao {

    abstract OauthUser getCustomUserByMobile(String areaCode,String mobile,String clientId);

    abstract OauthUser getOauthUser(OauthUser user);
}
