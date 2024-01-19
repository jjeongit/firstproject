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
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String price;

    public void patch(Coffee coffee) {
        if(coffee.name != null)
            this.name = coffee.name;
        if(coffee.price != null)
            this.price = coffee.price;

        /* 수정할 데이터(article)이
        있다면 this(target 기존데이터)의 값을 갱신해준다.
        없다면 target 의 값이 그대로 간다. */
    }

    //(strategy = GenerationType.IDENTITY) data.sql에 id값을 안적어도 자동생성 해줌
}
