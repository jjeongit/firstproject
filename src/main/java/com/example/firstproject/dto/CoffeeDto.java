package com.example.firstproject.dto;

import com.example.firstproject.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@NoArgsConstructor // 기본 생성자가 있어야 api방식 작동
@Setter // api 방식 사용시 setter가 있어야 입력한 값이 set됨 아니면 null 값으로 들어가게됨
public class CoffeeDto {

    private Long id;
    private String name;
    private String price;


    public Coffee toEntity() {
        return new Coffee(id, name, price);
    }
}
