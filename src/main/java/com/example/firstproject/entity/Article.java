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
    private Long id;

/*    public Long getId() {
        return id;
    }*/

    @Column
    private String title;

    @Column
    private String content;
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
