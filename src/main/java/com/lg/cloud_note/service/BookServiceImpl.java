package com.lg.cloud_note.service;

import com.lg.cloud_note.dao.BookDao;
import com.lg.cloud_note.pojo.Book;
import com.lg.cloud_note.util.NoteResult;
import com.lg.cloud_note.util.NoteUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by MIO on 2017/11/05 16:48
 */
@Service("bookService")
public class BookServiceImpl implements BookService{

    @Resource(name = "bookDao")
    private BookDao dao;

    public NoteResult<List<Book>> loadUserBooks(String userId) {
        NoteResult<List<Book>> result = new NoteResult<List<Book>>();
        List<Book> books = dao.findByUserId(userId);
        if (books == null){
            result.setStatus(1);
            result.setMsg("查询失败");
            return result;
        }
        result.setStatus(0);
        result.setMsg("查询成功");
        result.setData(books);
        return result;
    }

    public NoteResult<Map> addBook(String userId, String bookName) {
        Book book = new Book();
        book.setCn_notebook_id(NoteUtil.createId());
        book.setCn_user_id(userId);
        book.setCn_notebook_name(bookName);
        NoteResult<Map> result = new NoteResult<Map>();
        int rows = dao.save(book);
        if (rows != 1){
            result.setStatus(1);
            result.setMsg("插入失败");
            return result;
        }
        result.setStatus(0);
        result.setMsg("添加笔记本成功");
        Map map = new HashMap();
        map.put("userId",userId);
        map.put("bookName",bookName);
        Book resultBook = dao.findByUserIdBookName(map);
        map.put("bookId",resultBook.getCn_notebook_id());
        result.setData(map);
        return result;
    }
}
