package com.gwanho.notice.notice;

import com.gwanho.notice.notice.vo.BoardFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String file){
        return fileDir+file;
    }

    public List<BoardFile> boardFiles(List<MultipartFile> multipartFiles,Long id) throws IOException {
        List<BoardFile> boardFiles= new ArrayList<>();
        for (MultipartFile multipartFile:multipartFiles) {

            if (!multipartFile.isEmpty()){

                // 파일이름
                String originalFilename = multipartFile.getOriginalFilename();
                // db에 넣을 이름 만들기
                int lastIndexOf = originalFilename.lastIndexOf(".")+1;
                String substring = originalFilename.substring(lastIndexOf);
                String uuid = UUID.randomUUID().toString();
                String storeName = uuid + "." + substring;
                multipartFile.transferTo(new File(getFullPath(storeName)));
                boardFiles.add(new BoardFile(id,originalFilename,storeName));
            }

        }
        return boardFiles;
    }

}
