package com.hanghae.springweek04.controller;

import com.hanghae.springweek04.dto.BoardRequestDto;
import com.hanghae.springweek04.model.Board;
import com.hanghae.springweek04.repository.BoardRepository;
import com.hanghae.springweek04.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardRepository boardRepository;  // 필수적인 것들이라 private final
    private final BoardService boardService;


    // 게시글 작성
    @PostMapping("/api/boards")
    public Board writeBoard(@RequestBody BoardRequestDto requestDto, Long postId) {
        Board board = new Board(requestDto);
        return boardRepository.save(board);
    }


    // 전체 게시글 리스트 가져오기
    @GetMapping("/api/boards")
    public List<Board> getAllBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }


    // 개별 게시글 조회
    @GetMapping("/api/boards/{postId}")
    public Optional<Board> readBoard(@PathVariable Long postId) {
        return boardRepository.findById(postId);

//        Optional<Board> board = boardRepository.findById(id);
//
//        return board.get();
    }


    // 게시글 삭제
    @DeleteMapping("/api/boards/{postId}")
    //@PathVariable은 경로에 있는 id를 변수로 받기 위해
    public Long deleteBoard(@PathVariable Long postId, @RequestBody BoardRequestDto requestDto) {
        Board board = boardRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (!board.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        boardRepository.deleteById(postId);

        return postId;
    }


    // 게시글 수정
    @PutMapping("/api/boards/{postId}")
    public Long updateBoard(@PathVariable Long postId, @RequestBody BoardRequestDto requestDto) {
        boardService.update(postId, requestDto);
        return postId;
    }


}
