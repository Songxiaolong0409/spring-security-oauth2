package com.fih.auth.server.dao.Impl;

import com.fih.auth.server.dao.BaseDao;
import com.fih.auth.server.dao.IOauthUserTypeDao;
import com.fih.auth.server.model.OauthUserType;
import org.springframework.stereotype.Repository;

@Repository
public class OauthUserTypeDaoImpl extends BaseDao<OauthUserType> implements IOauthUserTypeDao {

    @Override
    public boolean getUserTypeExist(String clientId, String userType) {
        String sql="select count(*) from oauth_user_type where client_id=? and user_type=? ";
        return jdbcTemplate.queryForObject(sql,new Object[]{clientId,userType},Integer.class)
                >0?true:false;
    }
}
