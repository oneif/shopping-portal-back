package com.portal.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String userPic;
    private String createTime;
    private String updateTime;
}
