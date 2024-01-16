package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberForm {

    private String email;
    private String password;

    public Member toEntity() {
        return new Member(null, email, password);
    }

/*    public MemberForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "signupForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }*/

}
