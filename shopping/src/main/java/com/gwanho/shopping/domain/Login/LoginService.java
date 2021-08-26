package com.gwanho.shopping.domain.Login;

import com.gwanho.shopping.domain.Member.Member;
import com.gwanho.shopping.domain.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member Login(String loginId, String password){
        return memberRepository.findByLoginId(loginId)
                .filter(m-> m.getPassWord().equals(password))
                .orElse(null);
    }
}
