package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@NoArgsConstructor // 기본 생성자가 있어야 api방식 작동
@Setter // api 방식 사용시 setter가 있어야 입력한 값이 set됨 아니면 null 값으로 들어가게됨
public class ArticleForm {
    private Long id; //id 필드 추가
    private String title; //제목을 받을 필드
    private String content; //내용을 받을 필드

    //전송받은 제목과 내용을 필드에 저장하는 생성자 추가
/*    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
                                @AllArgsConstructor 추가로 삭제
    }*/

    // 데이터를 잘 받았는지 확인할 toString() 메서드 추가
/*    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
                                @ToString 어노테이션 추가로 삭제
    }*/

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
