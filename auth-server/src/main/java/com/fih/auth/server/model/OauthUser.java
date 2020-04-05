/**
 * Copyright @2020-03-29 Fih-foxconn All Rights Reserved
 */
package com.fih.auth.server.model;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Collection;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
    * 用户表
    * </p>
 *
 * @author xiaolong.song
 * @since 2020-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OauthUser implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id，自增主键
     */
    private Long userId;

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
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 0:无效,1:有效
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 修改时间
     */
    private LocalDateTime updateAt;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
