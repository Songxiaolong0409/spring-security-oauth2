package com.fih.auth.server.dao.Impl;

import com.fih.auth.server.dao.BaseDao;
import com.fih.auth.server.dao.IOauthClientDetailsDao;
import com.fih.auth.server.model.OauthClientDetails;
import org.springframework.stereotype.Repository;

@Repository
public class OauthClientDetailsDaoImpl extends BaseDao<OauthClientDetails> implements IOauthClientDetailsDao {

    public OauthClientDetails getOauthClientDetailsByClientId(String clientId){
        String sql="select * from oauth_client_details where client_id=?";
        return queryForObject(sql,new Object[]{clientId},OauthClientDetails.class);
    }

    public int addOauthClientDetails(OauthClientDetails oauthClientDetails){
        String sql="INSERT INTO ids.oauth_client_details " +
                "(client_id, resource_ids, client_secret, `scope`, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,oauthClientDetails.getClientId(),
                oauthClientDetails.getResourceIds(),
                oauthClientDetails.getClientSecret(),
                oauthClientDetails.getScope(),
                oauthClientDetails.getAuthorizedGrantTypes(),
                oauthClientDetails.getWebServerRedirectUri(),
                oauthClientDetails.getAuthorities(),
                oauthClientDetails.getAccessTokenValidity(),
                oauthClientDetails.getRefreshTokenValidity(),
                oauthClientDetails.getAdditionalInformation(),
                oauthClientDetails.getAutoapprove());
    }
}
