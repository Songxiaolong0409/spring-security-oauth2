package com.fih.auth.server.dao.Impl;

import com.fih.auth.server.dao.BaseDao;
import com.fih.auth.server.dao.IOauthUserDao;
import com.fih.auth.server.model.CustomUser;
import com.fih.auth.server.model.OauthUser;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OauthUserDaoImpl extends BaseDao<CustomUser> implements IOauthUserDao {

    @Override
    public CustomUser getCustomUserByMobile(String areaCode, String mobile, String clientId) {
        String sql="select * from oauth_user where area_code=? and mobile=? and client_id=?";
        return queryForObject(sql,new Object[]{areaCode,mobile,clientId}, CustomUser.class);
    }

    @Override
    public OauthUser getOauthUser(OauthUser user) {
        StringBuffer sql=new StringBuffer("select * from oauth_user where 1=1 ");

        List<Object> list=new ArrayList<>();
        if(StringUtils.hasLength(user.getMobile())){
            sql.append(" and area_code=? and mobile=?");
            list.add(user.getAreaCode());
            list.add(user.getMobile());
        }
        if(StringUtils.hasLength(user.getEmail())){
            sql.append(" and emial=? ");
            list.add(user.getEmail());
        }
        if(StringUtils.hasLength(user.getUsername())){
            sql.append(" and username=? ");
            list.add(user.getUsername());
        }
        return queryForObject(sql.toString(),list.toArray(),OauthUser.class);
    }
}
