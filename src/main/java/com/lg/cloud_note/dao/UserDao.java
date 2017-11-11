package com.lg.cloud_note.dao;

import com.lg.cloud_note.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * Create by MIO on 2017/11/04 11:21
 */
@Repository
public interface UserDao {
    User findByName(String name);
    void save(User user);
}
