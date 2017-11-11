package com.lg.cloud_note.controller;

import com.lg.cloud_note.pojo.Note;
import com.lg.cloud_note.service.NoteService;
import com.lg.cloud_note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Create by MIO on 2017/11/10 22:03
 */
@RequestMapping("/note")
@Controller
public class AddNoteController {

    @Resource
    private NoteService service;

    @RequestMapping("/add.do")
    @ResponseBody
    public NoteResult<Note> excute(String userId,String bookId,String title){
        return service.addNote(userId,bookId,title);
    }
}
