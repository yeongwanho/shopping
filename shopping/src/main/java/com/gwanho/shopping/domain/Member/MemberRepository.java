package com.gwanho.shopping.domain.Member;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepository {

    private static Map<Long,Member> store = new HashMap<>();

    private static Long sequence = 0L;

    public Member save (Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId){
        List<Member> all = findAll();
        for (Member member : all) {
            if (member.getLoginId().equals(loginId)){
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }
}
