package com.gwanho.notice.member.mapper;

import com.gwanho.notice.member.vo.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    Member memberCheck(String memberId);

    void insertMember(Member member);
}
