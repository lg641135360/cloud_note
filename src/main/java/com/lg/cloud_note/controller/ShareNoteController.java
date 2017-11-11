package com.lg.cloud_note.controller;

import com.lg.cloud_note.service.ShareService;
import com.lg.cloud_note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Create by MIO on 2017/11/10 23:36
 */
@RequestMapping("/share")
@Controller
public class ShareNoteController {

    @Resource(name = "shareService")
    private ShareService service;

    @RequestMapping("/add.do")
    @ResponseBody
    public NoteResult<Object> excute(String noteId){
        return service.shareNote(noteId);
    }
}
