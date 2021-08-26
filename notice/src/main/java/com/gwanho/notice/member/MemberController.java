package com.gwanho.notice.member;

import com.gwanho.notice.member.vo.Member;
import com.gwanho.notice.member.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberServiceImpl memberService;

    @GetMapping
    public String member(@ModelAttribute("member")Member member, Model model){
        model.addAttribute("member",member);

        return "/members/member";
    }
    @PostMapping
    public String memberAdd(@ModelAttribute("member") Member member, BindingResult bindingResult) throws NoSuchAlgorithmException {
        Member memberCheck = memberService.memberCheck(member.getLoginId());
        if (memberCheck==null) {
            //비밀번호 암호화
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(member.getPassword().getBytes(StandardCharsets.UTF_8));
            String format = String.format("%064x", new BigInteger(1, digest.digest()));
            member.setPassword(format);

            memberService.insertMember(member);
            return "redirect:/board";
        }
        bindingResult.reject("loginFail","생성된 아이디 입니다.");
        return "/members/member";
        }
    }



