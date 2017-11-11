package com.lg.cloud_note.controller;

import com.lg.cloud_note.pojo.Book;
import com.lg.cloud_note.service.BookService;
import com.lg.cloud_note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by MIO on 2017/11/05 17:19
 */
@RequestMapping("/book")
@Controller
public class LoadBooksController {

    @Resource(name = "bookService")
    private BookService bookService;

    @RequestMapping("/loadbooks.do")
    @ResponseBody
    public NoteResult<List<Book>> excute(String userId){
        NoteResult<List<Book>> result = bookService.loadUserBooks(userId);
        return result;
    }
}
