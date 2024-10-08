package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import exceptions.BadDataException;
import exceptions.EmptyTagException;
import exceptions.SpaceInTagException;
import org.junit.jupiter.api.Test;

import exceptions.InvalidTaskNameException;

import java.time.LocalDate;
import java.util.ArrayList;

public class ToDoTest {
    @Test
    public void stringConstructor_validName_success() throws Exception {
        ToDo test = new ToDo("validName");
        assertEquals(test.name, "validName");
        assertFalse(test.isDone);
    }

    @Test
    public void stringConstructor_validInputsWithTags_success() throws Exception {
        ToDo test = new ToDo("validName #thisIsATest! #pleasePass");
        assertEquals(test.name, "validName");
        ArrayList<String> correctTags = new ArrayList<>();
        correctTags.add("thisIsATest");
        correctTags.add("pleasePass");
        assertEquals(test.tags, correctTags);
    }

    @Test
    public void stringConstructor_invalidName_exceptionThrown() throws Exception {
        try {
            ToDo test = new ToDo("");
            //test should not reach this line
            fail();
        } catch (InvalidTaskNameException ex) {
            assertEquals(ex.getMessage(), "Error: Please provide a name!");
        }
    }

    @Test
    public void stringConstructor_emptyTag_exceptionThrown() throws Exception {
        try {
            ToDo test = new ToDo("task #     ");
            //test should not reach this line
            fail();
        } catch (EmptyTagException e) {
            assertEquals(e.getMessage(), "Error: The tag is empty. Tags cannot just contain whitespace");
        }
    }

    @Test
    public void stringConstructor_spaceInTag_exceptionThrown() throws Exception {
        try {
            ToDo test = new ToDo("task #this should fail! ");
            //test should not reach this line
            fail();
        } catch (SpaceInTagException e) {
            assertEquals(e.getMessage(), "Error: The tag cannot have white space!");
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
    public void arrayConstructor_failure() throws Exception {
        try {
            ToDo test = new ToDo (new String[]{"validName", "2024-05-05"});
            //test should not reach this line
            fail();
        } catch (BadDataException e) {
            assertEquals(e.getMessage(),
                    "Error: The data provided in data/tasks.txt is not in the correct format");
        }
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
