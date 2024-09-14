package hypebot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import hypebot.parser.DateTimeParser;
import hypebot.parser.DueDateParseException;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void Deadline_invalidDateFormat_exceptionThrown() {
        try {
            Deadline temp1 = new Deadline("temp1", DateTimeParser.parseDueDateFile("2019"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals(e.getClass(), DueDateParseException.class);
        }
    }

    @Test
    public void Deadline_dueDatePassed_exceptionThrown() {
        try {
            Deadline temp1 = new Deadline("temp1", DateTimeParser.parseDueDateFile("2019-09-15"));
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
        }
    }
}
