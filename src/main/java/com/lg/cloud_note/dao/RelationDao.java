package com.lg.cloud_note.dao;

import com.lg.cloud_note.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * Create by MIO on 2017/11/11 22:07
 */
@Repository
public interface RelationDao {
    //关联多个对象
    User findUserAndBooks(String userId);
    User findUserAndBooks1(String userId);
}
