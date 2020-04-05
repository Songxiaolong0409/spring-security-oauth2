/**
 * Copyright @2020-03-29 Fih-foxconn All Rights Reserved
 */
package com.fih.auth.server.model;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
    * 用户账号修改记录
    * </p>
 *
 * @author xiaolong.song
 * @since 2020-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OauthUserModificationRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户详情id，自增主键
     */
    private Long recordId;

    /**
     * 用户id
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
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;


}
