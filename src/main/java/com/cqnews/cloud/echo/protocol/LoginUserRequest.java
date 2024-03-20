package com.cqnews.cloud.echo.protocol;

import lombok.Data;

@Data
public class LoginUserRequest {

    private String userName;

    private String passWord;

}
