/**
 * Copyright @2020-03-29 Fih-foxconn All Rights Reserved
 */
package com.fih.auth.server.model;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
    * 认证客户端信息
    * </p>
 *
 * @author xiaolong.song
 * @since 2020-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 资源ID集合,多个资源时用逗号(,)分隔
     */
    private String resourceIds;

    /**
     * 客户端密匙
     */
    private String clientSecret;

    /**
     * 客户受限的范围。如果范围未定义或为空（默认值），客户端不受范围限制。read write all
     */
    private String scope;

    /**
     * 授予客户端使用授权的类型,pwd:手机号密码模式，sms：短信模式，refresh_token：刷新token，authorization_code：验证码模式，password：用户密码模式
     */
    private String authorizedGrantTypes;

    /**
     * 重定向URI
     */
    private String webServerRedirectUri;

    /**
     * 客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔
     */
    private String authorities;

    /**
     * 访问令牌有效时间值(单位:秒) 
     */
    private Integer accessTokenValidity;

    /**
     * 更新令牌有效时间值(单位:秒)
     */
    private Integer refreshTokenValidity;

    /**
     * 预留字段
     */
    private String additionalInformation;

    /**
     * 用户是否自动Approval操作
     */
    private String autoapprove;


}
