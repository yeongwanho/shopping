package com.gwanho.notice.member.service;

import com.gwanho.notice.member.vo.Member;

public interface MemberService {
    Member memberCheck(String loginId);

    void insertMember(Member member);
}
