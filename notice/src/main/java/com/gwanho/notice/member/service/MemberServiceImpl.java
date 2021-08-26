package com.gwanho.notice.member.service;

import com.gwanho.notice.member.mapper.MemberMapper;
import com.gwanho.notice.member.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Override
    public Member memberCheck(String loginId) {
        return memberMapper.memberCheck(loginId);
    }

    @Override
    public void insertMember(Member member) {

        memberMapper.insertMember(member);
    }


}
