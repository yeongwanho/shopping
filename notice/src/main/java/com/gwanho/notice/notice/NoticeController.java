package com.gwanho.notice.notice;


import com.gwanho.notice.member.vo.Member;
import com.gwanho.notice.notice.vo.Board;
import com.gwanho.notice.notice.service.NoticeServiceImpl;
import com.gwanho.notice.notice.vo.BoardFile;
import com.gwanho.notice.notice.vo.PageMaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.print.Pageable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
public class NoticeController {

    @Value("${file.dir}")
    private String fileDir;

    @Autowired
    private FileStore fileStore;

    @Autowired
    NoticeServiceImpl noticeService;

    @GetMapping
    public String notice(Model model, @RequestParam(defaultValue = "1") int page, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object sessionMember = session.getAttribute("SessionMember");
        boolean login = true;
        if (sessionMember == null) {
            login = false;
        }
        model.addAttribute("login", login);


        int cnt = noticeService.findAllCnt();

        PageMaker pageMaker = new PageMaker(cnt, page);

        List<Board> boards = noticeService.findAll(pageMaker);
        if (boards.isEmpty()) {

            return "notice/notice";
        }

        model.addAttribute("board", boards);
        model.addAttribute("pageMaker", pageMaker);

        return "notice/notice";
    }

    @GetMapping("/{itemId}")
    public String boardDetail(@PathVariable Long itemId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member sessionMember = (Member) session.getAttribute("SessionMember");


        log.info("여기방문하니?{}", itemId);
        Board byId = noticeService.findById(itemId);
        byId.setBoardFiles(noticeService.findByFileId(itemId));
        noticeService.boardHit(itemId);

        model.addAttribute("boardDetail", byId);

        boolean CheckLoginId = true;
        if (sessionMember.getLoginId() != byId.getLoginId()) {
            CheckLoginId = false;
        }
        model.addAttribute("CheckLoginId", CheckLoginId);
        return "notice/boardDetail";
    }

    @PostMapping("/{itemId}")
    public String boardDetails(@ModelAttribute("boardDetail") Board board) {
        log.info("여기방문하니?{}", board);
        noticeService.updateById(board);
        return "redirect:/board";
    }

    @ResponseBody
    @GetMapping("/image/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }


    @GetMapping("/add")
    public String addBoard(Model model) {

        model.addAttribute("board", new Board());


        return "notice/addBoard";
    }

    @PostMapping("/add")
    public String addBoardSave(@ModelAttribute("board") Board board, HttpServletRequest request) throws IOException {

        // 첨부파일
        HttpSession session = request.getSession();
        Member sessionMember = (Member) session.getAttribute("SessionMember");
        board.setWriter(sessionMember.getName());
        board.setLoginId(sessionMember.getLoginId());
        noticeService.insertNotice(board);
        Long boardId = noticeService.selectId();
        MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
        List<BoardFile> boardFiles = fileStore.boardFiles(multipart.getFiles("file"), boardId);
        for (BoardFile boardFile : boardFiles) {
            noticeService.insertFiles(boardFile);
        }
        return "redirect:/board";
    }

    @GetMapping("/search")
    public String search(@RequestParam String search, HttpServletRequest request, Model model) {
        int page = 1;
        HttpSession session = request.getSession();
        Object sessionMember = session.getAttribute("SessionMember");

        boolean login = true;
        if (sessionMember == null) {
            login = false;
        }
        model.addAttribute("login", login);
        List<Board> boards = noticeService.selectFindOne(search);
        int cnt = boards.size();

        PageMaker pageMaker = new PageMaker(cnt, page);


        if (boards.isEmpty()) {

            return "notice/notice";
        }

        model.addAttribute("board", boards);
        model.addAttribute("pageMaker", pageMaker);

        return "notice/notice";
    }


}
