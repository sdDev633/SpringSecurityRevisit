package com.example.securityRevise.SecurityRevise.model;

import com.example.securityRevise.SecurityRevise.entity.UserInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserInfoDto extends UserInfo {
    private String userName;
    private String lastName;
    private Long pgoneNumber;
    private String email;
}
