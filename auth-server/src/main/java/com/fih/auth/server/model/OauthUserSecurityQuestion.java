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
    * 密保问题
    * </p>
 *
 * @author xiaolong.song
 * @since 2020-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OauthUserSecurityQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long questionId;

    private String securityQuestion;


}
