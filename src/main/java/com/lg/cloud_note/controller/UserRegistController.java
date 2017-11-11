package com.lg.cloud_note.controller;

import com.lg.cloud_note.service.UserService;
import com.lg.cloud_note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Create by MIO on 2017/11/05 11:18
 */
@Controller
@RequestMapping("/user")
public class UserRegistController {
    @Resource
    private UserService userService;

    @RequestMapping("/add.do")
    @ResponseBody
    public NoteResult<Object> excute(String name,String password,String nick){
        NoteResult<Object> result = userService.addUser(name, password, nick);
        return result;
    }
}
