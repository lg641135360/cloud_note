package test.dao;

import com.lg.cloud_note.dao.BookDao;
import com.lg.cloud_note.pojo.Book;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by MIO on 2017/11/05 16:31
 */
public class TestBookDao {
    private BookDao dao;
    @Before
    public void init(){
        String[] conf = {"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
        ApplicationContext ac =
                new ClassPathXmlApplicationContext(conf);
        dao = ac.getBean("bookDao",BookDao.class);
    }

    @Test
    public void testFindByUserId(){
        List<Book> books = dao.findByUserId("48595f52-b22c-4485-9244-f4004255b972");
        for (Book book : books)
            System.out.println(book);
    }

    @Test
    public void testAddBook(){
        Book book = new Book();
        book.setCn_notebook_id("test");
        book.setCn_user_id("test");
        book.setCn_notebook_name("test");
        /*long time = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(time);
        book.setCn_notebook_createtime(timestamp);*/
        int i = dao.save(book);
        System.out.println(i);
    }

    @Test
    public void testFindByUserIdBookName(){
        Map map = new HashMap();
        map.put("userId","52f9b276-38ee-447f-a3aa-0d54e7a736e4");
        map.put("bookName","wsf");
        System.out.println(dao.findByUserIdBookName(map).getCn_notebook_name());
    }
}
