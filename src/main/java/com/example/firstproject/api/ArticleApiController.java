package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j // 로그 어노테이션
@RestController // RESR API용 컨트롤러
public class ArticleApiController {

    @Autowired
    private ArticleService articleService; // 서비스 객체 주입

    // GET
    @GetMapping("/api/articles")
    public List<Article> index(){ //반환형이 List<Article>, 전체조회
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){ //반환형이 단일 Article
        return articleService.show(id);
    }

    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){ //dto 매개변수, @RequestBody가 있어야 매개변수의 값을 받아올 수 있다 아니면 null로 들어감
        Article created = articleService.create(dto); // Entity 변환
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){ //ResponseEntity<>으로 반환형 수정

        Article updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() : // 삭제되었다는 뜻
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

// ~~서비스 계층 없을때~~ 컨트롤러에서 service 객체의 활동까지 한 후 리파지터리와 연결하여 작용함
//
//    @Autowired
//    private ArticleRepository articleRepository;
//
//    // GET
//    @GetMapping("/api/articles")
//    public List<Article> index(){ //반환형이 List<Article>, 전체조회
//        return articleRepository.findAll();
//    }
//
//    @GetMapping("/api/articles/{id}")
//    public Article show(@PathVariable Long id){ //반환형이 단일 Article
//        return articleRepository.findById(id).orElse(null);
//    }
//
//    // POST
//
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto){ //dto 매개변수, @RequestBody가 있어야 매개변수의 값을 받아올 수 있다 아니면 null로 들어감
//        Article article = dto.toEntity(); // Entity 변환
//        return articleRepository.save(article); // Entity 를 DB에 저장
//    }
//
//    // PATCH
//    @PatchMapping("api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){ //ResponseEntity<>으로 반환형 수정
//
//        // 1. DTO -> 엔티티 변환하기
//        Article article = dto.toEntity();
//        log.info("id: {}, article: {}", id, article.toString());
//
//        // 2. 타깃 조회하기
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 3. 잘못된 요청 처리하기
//        if (target == null || id != article.getId()) {
//            // 400, 잘못된 요청 응답!
//            log.info("잘못된 요청! id : {}, article: {}", id, article.toString()); //로그찍기
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); //ResponseEntity(REST API 응답을 위해 사용)반환
//        }
//
//        // 4. 업데이트 및 정상 응답(200)하기
//        target.patch(article); // 기존 데이터(target) + 새로운 데이터(article)
//        Article updated = articleRepository.save(target); // 수정 내용 DB에 저장
//        return ResponseEntity.status(HttpStatus.OK).body(updated); // 정상응답, 본문에 반환할 데이터 updated 싣기
//    }
//
//    // DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id){
//        // 1. 대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//        // 2. 잘못된 요청 처리하기
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        // 3. 대상 삭제하기
//        articleRepository.delete(target);
//        //return ResponseEntity.status(HttpStatus.OK).body(null);
//        return ResponseEntity.status(HttpStatus.OK).build(); //body 가 없는 ResponseEntity 객체를 생성하기 때문에 body(null)과 같다
//    }



}
