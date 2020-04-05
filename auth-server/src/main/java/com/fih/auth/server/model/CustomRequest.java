package com.fih.auth.server.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CustomRequest {

    @NotNull
    String client_id;

    @NotNull
    String client_secret;

    @NotNull
    String grant_type;
}
