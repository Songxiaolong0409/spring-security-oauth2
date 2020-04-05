package com.fih.auth.server.service;

import com.fih.auth.server.dao.IOauthUserDao;
import com.fih.auth.server.model.CustomUser;
import com.fih.auth.server.model.OauthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author songxiaolong
 * @CreateDate: 2020-03-28
 * @Description: 用户细节服务实现
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IOauthUserDao iOauthUserDao;

    public CustomUser loadUserByMobileAndPassword(String areaCode,String mobile, String password,String clientId) {
        if (StringUtils.isEmpty(areaCode)||StringUtils.isEmpty(mobile)) {
            throw new InvalidGrantException("手机号不能为空");
        } else if(StringUtils.isEmpty(password)){
            throw new InvalidGrantException("密码不能为空");
        }else if(StringUtils.isEmpty(clientId)){
            throw new InvalidGrantException("clientId 不能为空");
        }
        // 判断成功后返回用户细节
        CustomUser customUser= iOauthUserDao.getCustomUserByMobile(areaCode,mobile,clientId);
        if (customUser == null) {
            throw new InvalidGrantException("无法获取用户信息");
        }
        if(!new BCryptPasswordEncoder().matches(password,customUser.getPassword())){
            throw new InvalidGrantException("密码错误");
        }

        customUser.setPassword(null);

        return customUser;
    }

    public CustomUser loadUserByMobileAndSmscode(String areaCode,String mobile, String smscode,String clientId) {
        if (StringUtils.isEmpty(areaCode) || StringUtils.isEmpty(mobile)) {
            throw new InvalidGrantException("手机号不能为空");
        } else if(StringUtils.isEmpty(smscode)){
            throw new InvalidGrantException("验证码不能为空");
        }else if(StringUtils.isEmpty(clientId)){
            throw new InvalidGrantException("clientId 不能为空");
        }
        // 判断成功后返回用户细节
        CustomUser customUser=  iOauthUserDao.getCustomUserByMobile(areaCode,mobile,clientId);
        if (customUser == null) {
            throw new InvalidGrantException("无法获取用户信息");
        }

        //TODO 验证短信码

        customUser.setPassword(null);
        return customUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OauthUser user=new OauthUser();
        user.setUsername(username);
        return iOauthUserDao.getOauthUser(user);
    }
}
