package com.fih.auth.server.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author songxiaolong
 * @CreateDate: 2020-03-28
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomUser {

    /**
     * 用户登录id
     */
    private Long loginId;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 手机区号
     */
    private String areaCode;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    private Collection<? extends GrantedAuthority> authorities;
}
