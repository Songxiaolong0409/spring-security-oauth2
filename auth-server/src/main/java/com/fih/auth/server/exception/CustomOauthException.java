package com.fih.auth.server.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author songxiaolong
 * @CreateDate: 2020-03-28
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {

    public CustomOauthException(String msg) {
        super(msg);
    }

    public CustomOauthException(String msg, Throwable t) {
        super(msg, t);
    }
}
