package com.webchat.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRecord {
    private Integer recordId;

    private String content;

    private String fromuser;

    private String fromavatar;

    private String time;

    private String type;

    private String touser;

    private String toavatar;

    private Integer readed;

}
