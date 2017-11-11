package com.lg.cloud_note.controller;

import com.lg.cloud_note.service.NoteService;
import com.lg.cloud_note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Create by MIO on 2017/11/06 22:06
 */
@RequestMapping("/note")
@Controller
public class UpdateNoteController {
    @Resource(name = "noteService")
    private NoteService service;

    @RequestMapping("/update.do")
    @ResponseBody
    public NoteResult<Object> excute(String noteId, String title, String body){
        return service.updateNote(noteId, title, body);
    }
}
