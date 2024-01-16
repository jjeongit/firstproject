package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String SignUpPage(){
        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm memberForm){
        log.info(memberForm.toString());
        //System.out.println(memberForm.toString());
        //1
        Member member = memberForm.toEntity();
        log.info(member.toString());
        //System.out.println(member.toString());
        //2
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "";
    }

    @GetMapping("members/{id}")
    public String show(@PathVariable Long id, Model model){

        Member memberEntity = memberRepository.findById(id).orElse(null);

        model.addAttribute("members", memberEntity);

        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){

        Iterable<Member> memberEntityList = memberRepository.findAll();

        model.addAttribute("members", memberEntityList);

        return "members/index";
    }
}
