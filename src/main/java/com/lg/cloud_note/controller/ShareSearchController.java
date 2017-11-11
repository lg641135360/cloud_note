package com.lg.cloud_note.controller;

import com.lg.cloud_note.pojo.Share;
import com.lg.cloud_note.service.ShareService;
import com.lg.cloud_note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by MIO on 2017/11/11 12:05
 */
@RequestMapping("/share")
@Controller
public class ShareSearchController {

    @Resource(name = "shareService")
    private ShareService service;

    @RequestMapping("/search.do")
    @ResponseBody
    public NoteResult<List<Share>> excute(String keyword, int page){
        return service.searchNote(keyword,page);
    }
}
