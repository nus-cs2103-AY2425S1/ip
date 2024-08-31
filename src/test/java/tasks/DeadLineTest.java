package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import exceptions.InvalidDateException;
import exceptions.InvalidTaskNameException;


public class DeadLineTest {
    @Test
    public void stringConstructor_validInputs_success() throws Exception {
        DeadLine test = new DeadLine("validName /by 2024-05-05");
        LocalDate date = LocalDate.parse("2024-05-05");
        assertEquals(test.name, "validName");
        assertTrue(test.endDate.equals(date));
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
    public void arrayConstructor_wasDone_success() throws Exception {
        DeadLine test = new DeadLine(new String[]{"1", "validName", "2024-05-05"});
        LocalDate date = LocalDate.parse("2024-05-05");
        assertEquals(test.name, "validName");
        assertTrue(test.isDone);
        assertTrue(test.endDate.equals(date));
    }

    @Test
    public void arrayConstructor_wasNotDone_success() throws Exception {
        DeadLine test = new DeadLine(new String[]{"0", "validName", "2024-05-05"});
        LocalDate date = LocalDate.parse("2024-05-05");
        assertEquals(test.name, "validName");
        assertFalse(test.isDone);
        assertTrue(test.endDate.equals(date));
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
