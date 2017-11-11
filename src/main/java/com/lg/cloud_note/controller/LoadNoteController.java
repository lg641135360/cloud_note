package com.lg.cloud_note.controller;

import com.lg.cloud_note.pojo.Note;
import com.lg.cloud_note.service.NoteService;
import com.lg.cloud_note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Create by MIO on 2017/11/06 17:28
 */
@RequestMapping("/note")
@Controller
public class LoadNoteController {
    @Resource(name = "noteService")
    private NoteService service;

    @RequestMapping("/loadnote.do")
    @ResponseBody
    public NoteResult<Note> excute(String noteId){
        return service.loadNote(noteId);
    }
}
