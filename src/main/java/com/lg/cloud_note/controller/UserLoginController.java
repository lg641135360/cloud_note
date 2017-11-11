package com.lg.cloud_note.controller;

import com.lg.cloud_note.pojo.User;
import com.lg.cloud_note.service.UserService;
import com.lg.cloud_note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Create by MIO on 2017/11/04 12:42
 */
@Controller
@RequestMapping("/user")
public class UserLoginController {

    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping("/login.do")
    @ResponseBody
    public NoteResult<User> execute(String name, String password){
        System.out.println(name + "," + password);
        NoteResult<User> result = userService.checkLogin(name, password);
        return result;
    }
}
