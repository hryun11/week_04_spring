package com.hanghae.springweek04.controller;

import com.hanghae.springweek04.dto.CommentRequestDto;
import com.hanghae.springweek04.model.Comment;
import com.hanghae.springweek04.repository.CommentRepository;
import com.hanghae.springweek04.security.UserDetailsImpl;
import com.hanghae.springweek04.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // JSON으로 데이터를 주고받음을 선언
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;


    // 댓글 작성
    @PostMapping("/api/comments")
    public Comment saveComment(@RequestBody CommentRequestDto requestDto,
                                @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getId();

        Comment comment = new Comment(requestDto);
        return commentRepository.save(comment);
    }


    // 댓글 목록 조회
    @GetMapping("/api/comments")
    public List<Comment> getAllComments() {
        return commentRepository.findAllByOrderByCreatedAtDesc();
    }




    // 댓글 삭제
    @DeleteMapping("/api/comments/{commentId}")
    //@PathVariable은 경로에 있는 id를 변수로 받기 위해
    public Long deleteComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
        commentService.deleteComment(commentId, requestDto);

        return commentId;
    }


    // 댓글 수정
    @PutMapping("/api/comments/{commentId}")
    public Long updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
        commentService.update(commentId, requestDto);
        return commentId;
    }


}
