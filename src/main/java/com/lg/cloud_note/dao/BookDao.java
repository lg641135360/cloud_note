package com.lg.cloud_note.dao;

import com.lg.cloud_note.pojo.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Create by MIO on 2017/11/05 16:23
 */
@Repository
public interface BookDao {
    List<Book> findByUserId(String userId);
    int save(Book book);
    Book findByUserIdBookName(Map map);
}
