package test.dao;

import com.lg.cloud_note.dao.RelationDao;
import com.lg.cloud_note.pojo.User;
import org.junit.Before;
import org.junit.Test;
import test.TestBase;

/**
 * Create by MIO on 2017/11/11 23:15
 */
public class TestRelationDao extends TestBase{
    private RelationDao dao;
    @Before
    public void init(){
        dao = getContext().getBean("relationDao",RelationDao.class);
    }

    @Test
    public void test1(){
        User user = dao.findUserAndBooks1("48595f52-b22c-4485-9244-f4004255b972");
        System.out.println("名字：" + user.getCn_user_name());
        System.out.println("笔记本数:" + user.getBooks().size());
    }
}
