package tasks;

import exceptions.InvalidTaskNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
public class ToDoTest {
    @Test
    public void stringConstructor_validName_success() throws Exception {
        ToDo test = new ToDo("validName");
        assertEquals(test.name, "validName");
        assertFalse(test.isDone);
    }

    @Test
    public void stringConstructor_invalidName_exceptionThrown() throws Exception {
        try {
            assertEquals(new ToDo(""), "validName");
            //test should not reach this line
            fail();
        } catch (InvalidTaskNameException ex) {
            assertEquals(ex.getMessage(), "Error: Please provide a name!");
        }


    }

    @Test
    public void arrayConstructor_wasDone_success() throws Exception {
        ToDo test = new ToDo(new String[]{"1", "validName"});
        assertEquals(test.name, "validName");
        assertTrue(test.isDone);
    }

    @Test
    public void arrayConstructor_wasNotDone_success() throws Exception {
        ToDo test = new ToDo(new String[]{"0", "validName"});
        assertEquals(test.name, "validName");
        assertFalse(test.isDone);
    }

    @Test
    public void mark_wasDone_success() throws Exception {
        ToDo test = new ToDo(new String[]{"1", "validName"});
        test.mark();
        assertTrue(test.isDone);
    }

    @Test
    public void mark_wasNotDone_success() throws Exception {
        ToDo test = new ToDo(new String[]{"0", "validName"});
        test.mark();
        assertTrue(test.isDone);
    }

    @Test
    public void unmark_wasDone_success() throws Exception {
        ToDo test = new ToDo(new String[]{"1", "validName"});
        test.unMark();
        assertFalse(test.isDone);
    }

    @Test
    public void unmark_wasNotDone_success() throws Exception {
        ToDo test = new ToDo(new String[]{"0", "validName"});
        test.unMark();
        assertFalse(test.isDone);
    }



    @Test
    public void testStringConversion_wasDone_success() throws Exception {
        ToDo test = new ToDo(new String[]{"1", "validName"});
        assertEquals(test.toString(), "[T][X] validName");
    }

    @Test
    public void testStringConversion_wasNotDone_success() throws Exception {
        ToDo test = new ToDo(new String[]{"0", "validName"});
        assertEquals(test.toString(), "[T][ ] validName");
    }

    @Test
    public void testStringSaveConversion_wasDone_success() throws Exception {
        ToDo test = new ToDo(new String[]{"1", "validName"});
        assertEquals(test.toSave(), "T|1|validName");
    }

    @Test
    public void testStringSaveConversion_wasNotDone_success() throws Exception {
        ToDo test = new ToDo(new String[]{"0", "validName"});
        assertEquals(test.toSave(), "T|0|validName");
    }
}
