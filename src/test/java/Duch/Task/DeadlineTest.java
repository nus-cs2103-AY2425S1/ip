package duch.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.DateTimeException;

public class DeadlineTest {
    @Test
    public void testToString() {
        Deadline deadline = new Deadline("return book", "18/01/2002 1800", true);
        String expected = "[D][X] return book (by: Jan-18-2002 1800)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testToString2() {
        Deadline deadline = new Deadline("return book", "18/01/2002 1800", false);
        String expected = "[D][ ] return book (by: Jan-18-2002 1800)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testToStore() {
        Deadline deadline = new Deadline("return book", "18/01/2002 1800", true);
        String expected = "D|1|return book|18/01/2002 1800";
        assertEquals(expected, deadline.toStore());
    }

    @Test
    public void testToStore2() {
        Deadline deadline = new Deadline("return book", "18/01/2002 1800", false);
        String expected = "D|0|return book|18/01/2002 1800";
        assertEquals(expected, deadline.toStore());
    }

    @Test
    public void testInputInvalidDate() {
        try {
            new Deadline("return book", "12/30/2002 1800", true);
            fail();
        } catch(DateTimeException e) {
            // success
        }
       
    }

    @Test
    public void testInputInvalidDate2() {
        try {
            new Deadline("return book", "18-01-2002 1800", true);
            fail();
        } catch (DateTimeException e) {
            // This means a DateTimeException is thrown, which is the expected behaviour
        }
    }
}
