package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Slf4j //로깅기능
@Controller
public class ArticleController {

    @Autowired // 스프링부트가 미리 생성해 놓은 리파지터리 객체 주입(DI)
    private ArticleRepository articleRepository; // 객체선언

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){ // 폼 데이터를 DTO로 받기
        log.info(form.toString()); // 로깅 기능으로 DTO에 폼데이터가 잘 담겼는지 확인
        //System.out.println(form.toString()); //DTO에 폼데이터가 잘 담겼는지 확인
        
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString()); // 로깅 기능으로 DTO가 엔티티로 잘변환되는지 확인
        //System.out.println(article.toString()); // DTO가 엔티티로 잘변환되는지 확인 출력
        // 2. 리파지터리로 엔티티를 DB에 저장

        Article saved = articleRepository.save(article); // article 엔티티를저장해 saved 객체 반환
        log.info(saved.toString()); //DB에 장 저장되는지 확인
        //System.out.println(saved.toString()); // DB에 장 저장되는지 확인 출력

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) { // 매개변수로 id, Model 객체를 받아오기
        log.info("id = " + id); //id를 잘 받았는지 확인하는 로그 찍기

        // 1. id를 조회해 데이터 가져오기
        //Optional<Article> articleEntity = articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);


        // 2. 모델에 데이터 등록하기 , article 라는 이름으로 articleEntity 객체 추가
        model.addAttribute("article", articleEntity);


        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){ //model 객체 받아오기
        
        // 1. 모든 데이터 가져오기 (방법 세가지)
        ArrayList<Article> articleEntityList = articleRepository.findAll(); //ArticleRepository에서 오버라이딩하여 사용 가능함 그냥 List로 써도 됨
        //List<Article> articleEntityList = (List<Article>) articleRepository.findAll(); //Iterable<Articles>를 List<Articles>로 다운캐스팅
        //Iterable<Articles> articleEntityList = articleRepository.findAll(); List<Articles>를 업캐스팅

        
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList); // articleEntityList 등록
        
        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){

        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){ //매개변수로 DTO 받아 오기
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity(); // DTO(form)를 엔티티(articleEntity)로 변환하기
        log.info(articleEntity.toString()); //잘 변환됐는지 로그 찍기

        // 2. 엔티티를 DB에 저장하기
        // 2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-2. 기존 데이터 값을 갱신하기
        if(target != null){
            articleRepository.save(articleEntity); // 엔티티를 DB에 저장(갱신)
        }

        // 3. 수정 결과 페이지로 리다이렉트 하기.
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")  //delete를 왜 겟방식으로 해????
    public String delete(@PathVariable Long id, RedirectAttributes rttr){ //RedirectAttributes 리다이렉트로 Attributes 보내기
        log.info("삭제 요청이 들어왔습니다!!");
        // 1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null); // 데이터찾기
        log.info(target.toString());
        // 2. 대상 엔티티 삭제하기
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다!");
        }
        // 3. 결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }
}
