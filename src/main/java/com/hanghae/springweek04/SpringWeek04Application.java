package com.hanghae.springweek04;

import com.hanghae.springweek04.repository.CommentRepository;
import com.hanghae.springweek04.service.CommentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringWeek04Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringWeek04Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(CommentRepository commentRepository, CommentService commentService) {
        return (args) -> {
//            commentRepository.save(new Board("test1", "test1", "test1", "1234"));
//
//            System.out.println("데이터 인쇄");
//            List<Board> boardList = commentRepository.findAll();
//            for (int i = 0; i < boardList.size(); i++) {
//                Board board = boardList.get(i);
//                System.out.println(board.getId());
//                System.out.println(board.getUsername());
//                System.out.println(board.getTitle());
//                System.out.println(board.getContents());
//                System.out.println(board.getPassword());
//            }

//            BoardRequestDto requestDto = new BoardRequestDto("웹개발의 봄, Spring", "임민영");
//            commentService.update(1L, requestDto);
//            boardList = commentRepository.findAll();
//            for (int i=0; i<boardList.size(); i++) {
//                Board board = boardList.get(i);
//                System.out.println(board.getId());
//                System.out.println(board.getTitle());
//                System.out.println(board.getContents());
//                System.out.println(board.getPassword());
//            }


        };
    }
}