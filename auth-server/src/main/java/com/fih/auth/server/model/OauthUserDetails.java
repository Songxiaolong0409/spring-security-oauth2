/**
 * Copyright @2020-03-29 Fih-foxconn All Rights Reserved
 */
package com.fih.auth.server.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
    * 用户表详情
    * </p>
 *
 * @author xiaolong.song
 * @since 2020-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OauthUserDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户详情id，自增主键
     */
    private Long userDetailId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 昵称/姓名
     */
    private String displayName;

    /**
     * 头像照片
     */
    private String headIcon;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 性别，0：女，1：男：2：其它
     */
    private Integer gender;

    /**
     * 密保问题id1
     */
    private Long securityQuestionId1;

    /**
     * 答案1
     */
    private String answer1;

    /**
     * 密保问题id2
     */
    private Long securityQuestionId2;

    /**
     * 答案2
     */
    private String answer2;

    /**
     * 密保问题id3
     */
    private Long securityQuestionId3;

    /**
     * 答案3
     */
    private String answer3;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 修改时间
     */
    private LocalDateTime updateAt;


}
