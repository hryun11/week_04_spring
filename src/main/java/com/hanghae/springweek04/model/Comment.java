package com.hanghae.springweek04.model;

import com.hanghae.springweek04.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본생성자를 대신 생성해줍니다.
@Entity // 테이블임을 나타냅니다.
public class Comment extends Timestamped {

    @Id // id 값, Primary Key로 사용
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 명령
    private Long id;

    @Column(nullable = false)
    private String username;    // 닉네임

    @Column(nullable = false)
    private String contents;    // 작성 내용

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;



//    public Comment(String title, String username, String contents, String password) {
//        this.id = id;
//        this.title = title;
//        this.username = username;
//        this.contents = contents;
//        this.password = password;
//    }

    public Comment(CommentRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }



    public void update(CommentRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }

//    public void read(CommentRequestDto requestDto) {
//        this.id = requestDto.getId();
//        this.title = requestDto.getTitle();
//        this.username = requestDto.getUsername();
//        this.contents = requestDto.getContents();
//    }

}
