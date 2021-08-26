package com.gwanho.notice.notice.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Board {

    private Long boardId;

    private String loginId;

    private String title;

    private String content;

    private String writer;

    private Date firstInputDt;

    private Date LastInputDt;

    private String useYn;

    private String hit;

    private List<BoardFile> boardFiles= new ArrayList<>();


}
