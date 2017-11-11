package com.lg.cloud_note.controller;

import com.lg.cloud_note.pojo.Note;
import com.lg.cloud_note.service.NoteService;
import com.lg.cloud_note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Create by MIO on 2017/11/06 15:03
 */
@RequestMapping("/note")
@Controller
public class LoadNotesController {

    @Resource(name = "noteService")
    private NoteService service;

    @RequestMapping("/loadnotes.do")
    @ResponseBody
    public NoteResult<List<Note>> excute(String bookId){
        return service.loadBookNotes(bookId);
    }
}
