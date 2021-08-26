package com.gwanho.notice.notice.vo;

import lombok.Data;

@Data
public class BoardFile {

    private Long id;

    private Long boardId;

    private String fileName;

    private String storeFileName;

    public BoardFile(Long id,String fileName, String storeFileName) {
        this.boardId=id;
        this.fileName = fileName;
        this.storeFileName = storeFileName;
    }
}
