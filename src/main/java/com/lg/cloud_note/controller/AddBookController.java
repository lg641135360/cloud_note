package com.lg.cloud_note.controller;

import com.lg.cloud_note.service.BookService;
import com.lg.cloud_note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Create by MIO on 2017/11/07 20:04
 */
@RequestMapping("/book")
@Controller
public class AddBookController {

    @Resource(name = "bookService")
    private BookService service;

    @RequestMapping("/add.do")
    @ResponseBody
    public NoteResult<Map> excute(String userId, String bookName){
        return service.addBook(userId,bookName);
    }
}
