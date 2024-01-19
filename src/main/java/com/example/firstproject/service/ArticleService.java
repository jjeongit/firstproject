package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service // 서비스 객체 선언
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository; // 게시글 리파지터리 객체 주입

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null){
            return null; // 수정이아니라 생성이기 때문에 이미 존재하는 id에 관해서는 응답할 수 없음
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. DTO -> 엔티티 변환하기
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        // 2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리하기
        if (target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id : {}, article: {}", id, article.toString()); //로그찍기
            return null;
        }

        // 4. 업데이트 및 정상 응답(200)하기
        target.patch(article); // 기존 데이터(target) + 새로운 데이터(article)
        Article updated = articleRepository.save(target); // 수정 내용 DB에 저장
        return updated; // 정상응답, 본문에 반환할 데이터 updated 싣기
    }

    public Article delete(Long id) {
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리하기
        if (target == null) {
            return null;
        }
        // 3. 대상 삭제하기
        articleRepository.delete(target);
        //return ResponseEntity.status(HttpStatus.OK).body(null);
        return target;
    }

    @Transactional // 붙이지 않으면 마지막에 에러가 나도 DB에 저장이 되거나 반영이 되어버린다. 하지만 트랜잭션을 붙이면 롤백되어 반영되지 않는다.
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. dto 묶음(리스트)을 엔티티 묶음(리스트)으로 변환하기
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        // 2. 엔티티  묶음(리스트)을 DB에 저장하기
        articleList.forEach(article -> articleRepository.save(article));

        // 3. 강제로 에러를 발생시키기
        articleRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("결제 실패!")); // 찾는 데이터가 없으면 예외 발생

        // 4. 결과 값 반환하기
        return articleList;
    }
}
