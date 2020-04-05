package com.fih.auth.server;

import com.fih.auth.server.dao.IOauthClientDetailsDao;
import com.fih.auth.server.model.OauthClientDetails;
import net.sf.json.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuthServerApplicationTests {

    @Autowired
    private IOauthClientDetailsDao oauthClientDetailsDao;

    private String clientId="driver_ios";//给哪个端，哪个产品调用的业务账号。
    private String information="SCS项目，司机IOS App";//clientID说明

    /**
     * 手动操作
     * 再没有业务页面之前
     * 不过应该这样的逻辑也是不会有页面的。
     *
     * 直接执行到测试数据库，导出sql给到生产吧。
     */
    @Test
    void addOauthClientDetails() {
        OauthClientDetails t=new OauthClientDetails();
        t.setClientId(clientId+"_"+RandomStringUtils.randomNumeric(6));
        t.setResourceIds(null);
        t.setScope("all");
        t.setAuthorizedGrantTypes(AuthorizedGrantTypes.SMS.getVal()+","+AuthorizedGrantTypes.REFRESH_TOKEN.getVal());
        t.setWebServerRedirectUri(null);
        t.setAuthorities(null);
        t.setAccessTokenValidity(60*60*24);
        t.setRefreshTokenValidity(60*60*24*7);
        t.setAutoapprove(null);

        String client_secret="Fih"+RandomStringUtils.randomNumeric(6);

        //没加密前先保存起来，主要是为里后期查看调试方便。
        Map<String,Object> map=new HashMap<>();
        map.put("client_secret",client_secret);
        map.put("information",information);
        t.setAdditionalInformation(JSONObject.fromObject(map).toString());

        //加密后保存到数据库
        t.setClientSecret(new BCryptPasswordEncoder().encode(client_secret));

        oauthClientDetailsDao.addOauthClientDetails(t);
    }

}
