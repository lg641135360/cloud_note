package test.dao;

import com.lg.cloud_note.dao.ShareDao;
import com.lg.cloud_note.pojo.Share;
import com.lg.cloud_note.util.NoteUtil;
import org.junit.Before;
import org.junit.Test;
import test.TestBase;

import java.util.List;

/**
 * Create by MIO on 2017/11/10 23:18
 */
public class TestShareDao extends TestBase {
    private ShareDao dao;
    @Before
    public void init(){
        dao = getContext().getBean("shareDao",ShareDao.class);
    }

    @Test
    public void testSave(){
        Share share = new Share();
        share.setCn_share_id(NoteUtil.createId());
        dao.save(share);
    }

   /* @Test
    public void testSearchLikeTitle(){
        List<Share> shares = dao.findLikeTitle("1");
        for (Share share : shares) {
            System.out.println(share);
        }
    }*/
}
