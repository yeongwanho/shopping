package com.gwanho.notice.notice.service;

import com.gwanho.notice.notice.vo.Board;
import com.gwanho.notice.notice.mapper.BoardMapper;
import com.gwanho.notice.notice.vo.BoardFile;
import com.gwanho.notice.notice.vo.PageMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private BoardMapper boardMapper;

    @Override
    public void insertNotice(Board board) {
        boardMapper.insertNotice(board);
    }

    @Override
    public List<Board> findAll(PageMaker pageMaker){
        return boardMapper.findAll(pageMaker);
    }

    @Override
    public Board findById(Long id) {
        return boardMapper.findById(id);
    }

    @Override
    public void updateById(Board board) {
        boardMapper.updateById(board);
    }

    @Override
    public void boardHit(Long id) {
        boardMapper.boardHit(id);
    }

    @Override
    public void insertFiles(BoardFile boardFile) {
        boardMapper.insertFiles(boardFile);
    }

    @Override
    public List<BoardFile> findByFileId(Long id) {
        return boardMapper.findByFileId(id);
    }

    @Override
    public Long selectId() {
        return boardMapper.selectId();
    }

    @Override
    public int findAllCnt() {
        return boardMapper.findAllCnt();
    }

    @Override
    public List<Board> selectFindOne(String search) {
        return boardMapper.selectFindOne(search);
    }
}
