package com.fih.auth.server.dao;

import com.fih.auth.server.model.OauthUserType;

import java.util.List;

public interface IOauthUserTypeDao {

    abstract boolean getUserTypeExist(String clientId,String userType);
}
