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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;
    @GetMapping("/members/new")
    public String newMemberForm(){
        return "members/new";
    }

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
        return "redirect:/members/" + saved.getId();
    }

    @GetMapping("members/{id}")
    public String show(@PathVariable Long id, Model model){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        Iterable<Member> memberEntityList = memberRepository.findAll();
        model.addAttribute("membersList", memberEntityList);
        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);

        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form){
        log.info(form.toString());
        Member memberEntity = form.toEntity();
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        if(target != null){
            memberRepository.save(memberEntity);
        }
        return "redirect:/members/" + memberEntity.getId();
    }

    //@PostMapping("/members/{id}/delete") 액션을 이 주소로 하면 input hidden 태그 없이 id 값이 넘어와서 사용할 수 있음
    @PostMapping("/members/delete")
    public String delete(MemberForm form, RedirectAttributes rttr){
        log.info(form.toString());
        Member memberEntity = form.toEntity();
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        if (target != null){
            log.info(target.getId().toString());
            log.info(target.toString());
            log.info(memberEntity.toString());
            memberRepository.delete(target);
            //memberRepository.delete(memberEntity);
            rttr.addFlashAttribute("msg", "삭제 완료!");
        }
        return "redirect:/members";
    }
}
