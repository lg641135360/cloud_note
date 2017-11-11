package com.lg.cloud_note.service;

import com.lg.cloud_note.pojo.Book;
import com.lg.cloud_note.pojo.Note;
import com.lg.cloud_note.util.NoteResult;

import java.util.List;
import java.util.Map;

/**
 * Create by MIO on 2017/11/05 16:47
 */
public interface BookService {
    NoteResult<List<Book>> loadUserBooks(String userId);
    NoteResult<Map> addBook(String userId, String bookName);
}
