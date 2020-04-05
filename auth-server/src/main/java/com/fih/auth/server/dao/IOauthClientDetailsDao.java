package com.fih.auth.server.dao;

import com.fih.auth.server.model.OauthClientDetails;

public interface IOauthClientDetailsDao {

    abstract OauthClientDetails getOauthClientDetailsByClientId(String clientId);

    abstract int addOauthClientDetails(OauthClientDetails oauthClientDetails);
}
