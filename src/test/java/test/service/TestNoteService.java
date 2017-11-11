package test.service;

import com.lg.cloud_note.pojo.Note;
import com.lg.cloud_note.service.NoteService;
import com.lg.cloud_note.util.NoteResult;
import org.junit.Before;
import org.junit.Test;
import test.TestBase;

import java.util.List;
import java.util.Map;

/**
 * Create by MIO on 2017/11/06 14:37
 */
public class TestNoteService extends TestBase{
    private NoteService service;
    @Before
    public void init(){
        service = super.getContext().getBean("noteService",NoteService.class);
    }

    @Test
    public void testLoadBookNotes(){
        NoteResult<List<Note>> listNoteResult = service.loadBookNotes("516f6f4f-eaa3-4c76-84ff-530b92c7f64d");
        for (Note map : listNoteResult.getData()) {
            System.out.println(map);
        }
    }

    @Test
    public void testLoadNote(){
        System.out.println(service.loadNote("003ec2a1-f975-4322-8e4d-dfd206d6ac0c").getData());
    }

    @Test
    public void testUpdateNote(){
        NoteResult<Object> result = service.updateNote("003ec2a1-f975-4322-8e4d-dfd206d6ac0c", "123", "123");
        System.out.println(result);
    }

    @Test
    public void testAddNote(){
        System.out.println(service.addNote("111","111","111").getData());
    }
}