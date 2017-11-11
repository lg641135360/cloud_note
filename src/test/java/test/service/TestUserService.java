package test.service;

import com.lg.cloud_note.pojo.User;
import com.lg.cloud_note.service.UserService;
import com.lg.cloud_note.util.NoteResult;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * Create by MIO on 2017/11/04 11:59
 */
public class TestUserService {
    private UserService service;
    @Before
    public void init(){
        String[] conf = {"conf/spring-mybatis.xml",
                        "conf/spring-mvc.xml"};
        ApplicationContext ac =
                new ClassPathXmlApplicationContext(conf);
        service = ac.getBean("userService",UserService.class);
    }

    @Test  //用例-1：预期结果：用户名不存在
    public void test1(){
        NoteResult<User> result = service.checkLogin("123", "11");
        System.out.println(result.getMsg());
    }

    @Test
    public void test2(){
        NoteResult<User> result = service.checkLogin("demo", "123");
        System.out.println(result.getMsg());
    }

    @Test
    public void testUserRegist(){
        NoteResult<Object> result = service.addUser("胡高", "123456", "bitch");
        System.out.println(result.toString());
    }
}
