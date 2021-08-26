package com.gwanho.notice.login.service;

import com.gwanho.notice.login.vo.LoginForm;
import com.gwanho.notice.login.mapper.LoginMapper;
import com.gwanho.notice.member.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    LoginMapper loginMapper;

    @Override
    public Member login(LoginForm loginForm) {
        return loginMapper.login(loginForm);
    }
}
