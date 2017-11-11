package com.lg.cloud_note.service;

import com.lg.cloud_note.pojo.Note;
import com.lg.cloud_note.util.NoteResult;

import java.util.List;
import java.util.Map;


/**
 * Create by MIO on 2017/11/06 14:32
 */
public interface NoteService {
    NoteResult<List<Note>> loadBookNotes(String bookId);
    NoteResult<Note> loadNote(String noteId);
    NoteResult<Object> updateNote(String noteId,String title,String body);
    NoteResult<Note> addNote(String userId,String bookId,String title);
}
