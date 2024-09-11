package loafy.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        // task marked as done has correct symbol "[X]" and date display
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 25, 23, 59);
        String expected = "[D][X] test (by: 25/12/2024 2359)";
        assertEquals(expected, new Deadline(true, "test", dateTime).toString());

        // task marked as not done has correct symbol "[ ]"
        dateTime = LocalDateTime.of(2024, 12, 25, 23, 59);
        expected = "[D][ ] test (by: 25/12/2024 2359)";
        assertEquals(expected, new Deadline(false, "test", dateTime).toString());
    }

    @Test
    public void testConvertToTxt() {
        // text representation of done tasks contains "true" and default LocalDateTime format
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 25, 23, 59);
        String expected = "D,true,test,2024-12-25T23:59";
        assertEquals(expected, new Deadline(true, "test", dateTime).convertToTxt());

        // text representation of done tasks contains "false"
        dateTime = LocalDateTime.of(2024, 12, 25, 23, 59);
        expected = "D,false,test,2024-12-25T23:59";
        assertEquals(expected, new Deadline(false, "test", dateTime).convertToTxt());
    }
}
