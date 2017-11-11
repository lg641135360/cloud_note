package com.lg.cloud_note.service;

import com.lg.cloud_note.pojo.User;
import com.lg.cloud_note.util.NoteResult;

/**
 * Create by MIO on 2017/11/04 11:44
 */
public interface UserService {
    NoteResult<User> checkLogin(String name,String password);

    NoteResult<Object> addUser(String name,String password,String nick);
}
