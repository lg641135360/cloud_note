package com.lg.cloud_note.service;

import com.lg.cloud_note.pojo.Share;
import com.lg.cloud_note.util.NoteResult;

import java.util.List;

/**
 * Create by MIO on 2017/11/10 23:22
 */
public interface ShareService {
    NoteResult<Object> shareNote(String noteId);
    NoteResult<List<Share>> searchNote(String keyword,int page);
}
