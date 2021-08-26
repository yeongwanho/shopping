package com.gwanho.notice.member.vo;

import lombok.Data;

@Data
public class Member {

    private long Id;

    private String loginId;

    private String name;

    private String password;
}
