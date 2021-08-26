package com.gwanho.notice.notice.service;

import com.gwanho.notice.notice.vo.Board;
import com.gwanho.notice.notice.vo.BoardFile;
import com.gwanho.notice.notice.vo.PageMaker;


import java.util.List;

public interface NoticeService {

    void insertNotice(Board board);

    List<Board> findAll(PageMaker pageMaker);

    Board findById(Long id);

    void updateById(Board board);

    void boardHit(Long id);

    void insertFiles(BoardFile boardFile);

    List<BoardFile> findByFileId(Long id);

    Long selectId();

    int findAllCnt();

    List<Board> selectFindOne(String search);

}
