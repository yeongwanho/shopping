package com.gwanho.notice.notice.mapper;

import com.gwanho.notice.notice.vo.Board;
import com.gwanho.notice.notice.vo.BoardFile;
import com.gwanho.notice.notice.vo.PageMaker;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface BoardMapper {
    void insertNotice(Board board);

    List<Board> findAll(PageMaker pageMaker);

    Board findById(Long id);

    void boardHit(Long id);

    void updateById(Board board);

    void insertFiles(BoardFile boardFile);

    List<BoardFile> findByFileId(Long id);

    Long selectId();

    int findAllCnt();

    List<Board> selectFindOne(String search);
}
