package com.gwanho.notice.login;

import com.gwanho.notice.login.vo.LoginForm;
import com.gwanho.notice.login.service.LoginServiceImpl;
import com.gwanho.notice.member.vo.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Controller
@RequestMapping
public class LoginController {
    @Autowired
    LoginServiceImpl loginService;

    @GetMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        model.addAttribute("loginForm", loginForm);
        return "/login/login";
    }

    @PostMapping("/login")
    public String loginPass(@ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request) throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(loginForm.getPassword().getBytes(StandardCharsets.UTF_8));
        String format = String.format("%064x", new BigInteger(1, digest.digest()));
        loginForm.setPassword(format);
        Member login = loginService.login(loginForm);
        if (login == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("SessionMember", login);
        return "redirect:/board";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/board";

    }

}
