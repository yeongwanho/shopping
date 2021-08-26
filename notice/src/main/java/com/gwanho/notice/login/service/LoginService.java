package com.gwanho.notice.login.service;

import com.gwanho.notice.login.vo.LoginForm;
import com.gwanho.notice.member.vo.Member;

public interface LoginService {
    Member login(LoginForm loginForm);
}
