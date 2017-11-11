package test.service;

import com.lg.cloud_note.pojo.Book;
import com.lg.cloud_note.service.BookService;
import com.lg.cloud_note.util.NoteResult;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import test.TestBase;

import java.util.List;
import java.util.Map;

/**
 * Create by MIO on 2017/11/05 17:01
 */
public class TestBookService extends TestBase{
    private BookService service;

    @Before
    public void init(){
        service = super.getContext().getBean("bookService", BookService.class);
    }

    @Test
    public void test(){
        NoteResult<List<Book>> result = service.loadUserBooks("48595f52-b22c-4485-9244-f4004255b972");
        System.out.println(result.getStatus());
        for (Book book : result.getData())
            System.out.println(book);
        System.out.println(result.getMsg());
    }

    @Test
    public void testAddBook(){
        NoteResult<Map> result = service.addBook("test", "test");
        System.out.println(result.getMsg() + "," + result.getStatus());
    }
}
