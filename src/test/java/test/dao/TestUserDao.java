package test.dao;

import com.lg.cloud_note.dao.UserDao;
import com.lg.cloud_note.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Create by MIO on 2017/11/04 11:28
 */
public class TestUserDao {

    @Test
    public void testUserDao(){
        ApplicationContext ac =
                new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
        UserDao dao = ac.getBean("userDao",UserDao.class);
        User user = dao.findByName("demo");
        System.out.println(user);
    }

    @Test
    public void testSave(){
        String[] conf =
                {"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
        ApplicationContext ac =
                new ClassPathXmlApplicationContext(conf);
        UserDao dao = ac.getBean("userDao",UserDao.class);
        User user = new User();
        user.setCn_user_id("123456789");
        user.setCn_user_name("李港");
        user.setCn_user_password("123456798");
        user.setCn_user_nick("爹");
        dao.save(user);
        System.out.println(user);
    }
}
