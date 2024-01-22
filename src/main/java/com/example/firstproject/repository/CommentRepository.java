package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글의 모든 댓글 조회
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId" ,
            nativeQuery = true) // value 속성에 실행하려는 쿼리 작성
    List<Comment> findByArticleId(@Param("articleId") Long articleId);  //@Param 붙이라고 오류뜸

    // 특정 닉네임의 모든 댓글 조회 -> xml로 sql 작성
    List<Comment> findByNickname(@Param("nickname") String nickname); // null포인트로 오류떠서 @Param 붙이니까 테스트 통과
}
