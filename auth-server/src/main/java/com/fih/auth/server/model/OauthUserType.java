/**
 * Copyright @2020-03-29 Fih-foxconn All Rights Reserved
 */
package com.fih.auth.server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
    * 用户类型
    * </p>
 *
 * @author xiaolong.song
 * @since 2020-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OauthUserType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户类型id，自增主键
     */
    private Long userTypeId;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 用户类型
     */
    private String userType;

}
