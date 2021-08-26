package com.gwanho.notice.login.mapper;

import com.gwanho.notice.login.vo.LoginForm;
import com.gwanho.notice.member.vo.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    Member login(LoginForm loginForm);
}
