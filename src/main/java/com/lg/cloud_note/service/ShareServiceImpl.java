package com.lg.cloud_note.service;

import com.lg.cloud_note.dao.NoteDao;
import com.lg.cloud_note.dao.ShareDao;
import com.lg.cloud_note.pojo.Note;
import com.lg.cloud_note.pojo.Share;
import com.lg.cloud_note.util.NoteResult;
import com.lg.cloud_note.util.NoteUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by MIO on 2017/11/10 23:23
 */
@Service("shareService")
public class ShareServiceImpl implements ShareService{

    @Resource(name = "shareDao")
    private ShareDao shareDao;
    @Resource(name = "noteDao")
    private NoteDao noteDao;

    public NoteResult<Object> shareNote(String noteId) {
        //向cn_share表插入记录
        Share share = new Share();
        share.setCn_share_id(NoteUtil.createId());//主键
        share.setCn_note_id(noteId);
        //获取笔记标题和内容
        Note note = noteDao.findByNoteId(noteId);
        share.setCn_share_title(note.getCn_note_title());
        share.setCn_share_body(note.getCn_note_body());
        shareDao.save(share);
        //模拟异常
//        String str = null;
//        str.length();

        NoteResult<Object> result = new NoteResult<Object>();
        result.setStatus(0);
        result.setMsg("分享笔记成功");
        result.setData(share);
        return result;
    }

    public NoteResult<List<Share>> searchNote(String keyword,int page) {
        String title = "%" + keyword + "%";
        int begin = (page - 1) * 3;
        Map map = new HashMap();
        map.put("title",title);
        map.put("begin",begin);
        //模糊查询
        List<Share> shares = shareDao.findLikeTitle(map);
        NoteResult<List<Share>> result = new NoteResult<List<Share>>();
        result.setStatus(0);
        result.setMsg("查询成功");
        result.setData(shares);
        return result;
    }
}
