package com.hanghae.springweek04.service;

import com.hanghae.springweek04.model.Comment;
import com.hanghae.springweek04.repository.CommentRepository;
import com.hanghae.springweek04.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

@RequiredArgsConstructor    // final로 선언했으면 넣어줌. 생성자를 알림.
@Service
public class CommentService {
    private final CommentRepository commentRepository;  // final로 필수를 알림.

    // 댓글 수정
    @Transactional
    public Long update(Long commentId, CommentRequestDto requestDto) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
//        if (!comment.getPassword().equals(requestDto.getPassword())) {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }

        comment.update(requestDto);

        return commentId;

    }

    public Long deleteComment(Long commentId, @RequestBody CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
//        if (!comment.getPassword().equals(requestDto.getPassword())) {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }

        commentRepository.deleteById(commentId);

        return commentId;
    }


}
