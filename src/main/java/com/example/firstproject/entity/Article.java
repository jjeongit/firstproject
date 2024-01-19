package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // JPA 어노테이션, 테이블이 생성됨
@AllArgsConstructor
@ToString
@NoArgsConstructor //기본 생성자 추가 어노테이션
@Getter //롬복으로 게터추가
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id 자동생성
    //(strategy = GenerationType.IDENTITY) data.sql에 id값을 안적어도 자동생성 해줌
    private Long id;

/*    public Long getId() {
        return id;
    }*/

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;

        /* 수정할 데이터(article)이
        있다면 this(target 기존데이터)의 값을 갱신해준다.
        없다면 target 의 값이 그대로 간다. */
    }
/*

    @AllArgsConstructor
    @ToString

    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }*/
}
