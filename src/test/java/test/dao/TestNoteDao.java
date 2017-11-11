package test.dao;

import com.lg.cloud_note.dao.NoteDao;
import com.lg.cloud_note.pojo.Note;
import com.lg.cloud_note.util.NoteUtil;
import org.junit.Before;
import org.junit.Test;
import test.TestBase;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Create by MIO on 2017/11/05 22:34
 */
public class TestNoteDao extends TestBase{
    private NoteDao dao;
    @Before
    public void init(){
        dao = super.getContext().getBean("noteDao",NoteDao.class);
    }

    @Test
    public void testNoteDao(){
        List<Note> notes = dao.findByBookId("516f6f4f-eaa3-4c76-84ff-530b92c7f64d");
        for (Note note : notes) {
            System.out.println(note);
        }
    }

    @Test
    public void testLoadNote(){
        System.out.println(dao.findByNoteId("003ec2a1-f975-4322-8e4d-dfd206d6ac0c"));
    }

    @Test
    public void testUpdateNote(){
        Note note = dao.findByNoteId("003ec2a1-f975-4322-8e4d-dfd206d6ac0c");
        note.setCn_note_title("1234");
        note.setCn_note_body("1234");
        int count = dao.updateNote(note);
        System.out.println(count);
    }

    @Test
    public void testSave(){
        Note note = new Note();
        note.setCn_note_id("00000000");
        dao.save(note);
    }
}
