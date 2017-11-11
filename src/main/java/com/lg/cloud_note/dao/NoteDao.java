package com.lg.cloud_note.dao;

import com.lg.cloud_note.pojo.Note;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Create by MIO on 2017/11/05 22:25
 */
@Repository
public interface NoteDao {
    List<Note> findByBookId(String bookId);
    Note findByNoteId(String noteId);
    int updateNote(Note note);
    void save(Note note);
}
