package com.lg.cloud_note.dao;

import com.lg.cloud_note.pojo.Share;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Create by MIO on 2017/11/10 23:13
 */
@Repository
public interface ShareDao {
    void save(Share share);
    List<Share> findLikeTitle(Map params);
}
