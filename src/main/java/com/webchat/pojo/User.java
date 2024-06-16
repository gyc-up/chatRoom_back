package com.webchat.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer userId;

    private String nickName;

    private String email;

    private Integer sex;

    private String password;

    private String avatar;

}
