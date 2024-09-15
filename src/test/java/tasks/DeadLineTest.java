package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.*;
import org.junit.jupiter.api.Test;


public class DeadLineTest {
    @Test
    public void stringConstructor_validInputsWithoutTags_success() throws Exception {
        DeadLine test = new DeadLine("validName /by 2024-05-05");
        LocalDate date = LocalDate.parse("2024-05-05");
        assertEquals(test.name, "validName");
        assertEquals(test.endDate, date);
    }

    @Test
    public void stringConstructor_validInputsWithTags_success() throws Exception {
        DeadLine test = new DeadLine("validName #thisIsATest! #pleasePass /by 2024-05-05");
        LocalDate date = LocalDate.parse("2024-05-05");
        assertEquals(test.name, "validName");
        assertEquals(test.endDate, date);
        ArrayList<String> correctTags = new ArrayList<>();
        correctTags.add("thisIsATest");
        correctTags.add("pleasePass");
        assertEquals(test.tags, correctTags);
    }

    @Test
    public void stringConstructor_invalidName_exceptionThrown() throws Exception {
        try {

            assertEquals(new DeadLine(" /by 2024-05-05").name, "validName");
            //test should not reach this line
            fail();
        } catch (InvalidTaskNameException ex) {
            assertEquals(ex.getMessage(), "Error: Please provide a name!");
        }
    }

    @Test
    public void stringConstructor_invalidDate_exceptionThrown() throws Exception {
        try {
            DeadLine test = new DeadLine("test /by 2024-05");
            //test should not reach this line
            fail();
        } catch (InvalidDateException ex) {
            assertEquals(ex.getMessage(), "Error: Invalid date format given");
        }
    }

    @Test
    public void stringConstructor_emptyTag_exceptionThrown() throws Exception {
        try {
            DeadLine test = new DeadLine("task #     /by 2024-05-05");
            //test should not reach this line
            fail();
        } catch (EmptyTagException e) {
            assertEquals(e.getMessage(), "Error: The tag is empty. Tags cannot just contain whitespace");
        }
    }

    @Test
    public void stringConstructor_spaceInTag_exceptionThrown() throws Exception {
        try {
            DeadLine test = new DeadLine("task #this should fail! /by 2024-05-05");
            //test should not reach this line
            fail();
        } catch (SpaceInTagException e) {
            assertEquals(e.getMessage(), "Error: The tag cannot have white space!");
        }
    }

    @Test
    public void arrayConstructor_wasDone_success() throws Exception {
        DeadLine test = new DeadLine(new String[]{"1", "validName", "2024-05-05"});
        LocalDate date = LocalDate.parse("2024-05-05");
        assertEquals(test.name, "validName");
        assertTrue(test.isDone);
        assertEquals(test.endDate, date);
    }

    @Test
    public void arrayConstructor_wasNotDone_success() throws Exception {
        DeadLine test = new DeadLine(new String[]{"0", "validName", "2024-05-05"});
        LocalDate date = LocalDate.parse("2024-05-05");
        assertEquals(test.name, "validName");
        assertFalse(test.isDone);
        assertEquals(test.endDate, date);
    }

    @Test
    public void arrayConstructor_failure() throws Exception {
        try {
            DeadLine test = new DeadLine(new String[]{"validName", "2024-05-05"});
            //test should not reach this line
            fail();
        } catch (BadDataException e) {
            assertEquals(e.getMessage(),
                    "Error: The data provided in data/tasks.txt is not in the correct format");
        }
    }

    @Test
    public void testStringConversion_wasDone_success() throws Exception {
        DeadLine test = new DeadLine(new String[]{"1", "validName", "2024-05-05"});
        assertEquals(test.toString(), "[D][X] validName (by: 2024-05-05)");
    }

    @Test
    public void testStringConversion_wasNotDone_success() throws Exception {
        DeadLine test = new DeadLine(new String[]{"0", "validName", "2024-05-05"});
        assertEquals(test.toString(), "[D][ ] validName (by: 2024-05-05)");
    }

    @Test
    public void testStringSaveConversion_wasDone_success() throws Exception {
        DeadLine test = new DeadLine(new String[]{"1", "validName", "2024-05-05"});
        assertEquals(test.toSave(), "D|1|validName|2024-05-05");
    }

    @Test
    public void testStringSaveConversion_wasNotDone_success() throws Exception {
        DeadLine test = new DeadLine(new String[]{"0", "validName", "2024-05-05"});
        assertEquals(test.toSave(), "D|0|validName|2024-05-05");
    }
}
