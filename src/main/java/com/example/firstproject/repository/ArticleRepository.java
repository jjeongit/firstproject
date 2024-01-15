package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

// Article - 관리대상 엔티티, Long - id가 대푯값이고 타입이 Long
// extends CrudRepository - CrudRepository라는 인터페이스를 상속, JPA에서 제공, 생성/조회/수정/삭제 가능
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
