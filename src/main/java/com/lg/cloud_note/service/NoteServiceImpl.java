package com.lg.cloud_note.service;

import com.lg.cloud_note.dao.NoteDao;
import com.lg.cloud_note.pojo.Note;
import com.lg.cloud_note.util.NoteResult;
import com.lg.cloud_note.util.NoteUtil;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Create by MIO on 2017/11/06 14:33
 */
@Service("noteService")
public class NoteServiceImpl implements NoteService{
    @Resource(name = "noteDao")
    private NoteDao dao;

    public NoteResult<List<Note>> loadBookNotes(String bookId) {
        List<Note> notes = dao.findByBookId(bookId);
        NoteResult<List<Note>> result = new NoteResult<List<Note>>();
        if (notes == null){
            result.setStatus(1);
            result.setMsg("该用户没有笔记");
            return result;
        }
        result.setStatus(0);
        result.setMsg("查询用户笔记成功");
        result.setData(notes);
        return result;
    }

    public NoteResult<Note> loadNote(String noteId) {
        Note note = dao.findByNoteId(noteId);
        NoteResult<Note> result = new NoteResult<Note>();
        if (note == null) {
            result.setStatus(1);
            result.setMsg("加载笔记失败");
            return result;
        }
        result.setStatus(0);
        result.setMsg("加载笔记成功");
        result.setData(note);
        return result;
    }

    public NoteResult<Object> updateNote(String noteId,String title,String body) {
        //创建note参数
        Note note = new Note();
        note.setCn_note_id(noteId);
        note.setCn_note_title(title);
        note.setCn_note_body(body);
        //更新数据库记录
        int rows = dao.updateNote(note);
        NoteResult<Object> result = new NoteResult<Object>();
        if (rows == 1){
            result.setStatus(0);
            result.setMsg("保存笔记成功");
            return result;
        } else {
            result.setStatus(1);
            result.setMsg("保存笔记失败");
            return result;
        }
    }

    public NoteResult<Note> addNote(String userId, String bookId, String title) {
        Note note = new Note();
        //用户id
        note.setCn_user_id(userId);
        //笔记本id
        note.setCn_notebook_id(bookId);
        //笔记标题
        note.setCn_note_title(title);
        note.setCn_note_id(NoteUtil.createId());
        //笔记内容
        note.setCn_note_body("");
        //笔记创建时间
        Long time = System.currentTimeMillis();
        note.setCn_note_create_time(time);
        //最后修改时间
        note.setCn_note_last_modify_time(time);
        //状态：1-normal 2-delete
        note.setCn_note_status_id("1");
        //类型: 1-normal 2-favor 3-share
        note.setCn_note_type_id("1");
        dao.save(note);
        //构建result
        NoteResult<Note> result = new NoteResult<Note>();
        result.setStatus(0);
        result.setMsg("添加笔记成功");
        result.setData(note);
        return result;
    }
}
