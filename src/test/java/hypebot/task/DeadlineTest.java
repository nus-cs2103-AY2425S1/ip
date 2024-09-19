package hypebot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import hypebot.exception.illegal.DatePassedException;
import hypebot.parser.datetime.FileDateTimeParser;
import hypebot.exception.datetime.DueDateParseException;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    FileDateTimeParser fileDateTimeParser = new FileDateTimeParser();

    @Test
    public void Deadline_invalidDateFormat_exceptionThrown() {
        try {
            Deadline temp1 = new Deadline("temp1", fileDateTimeParser.parseDueDate("2019"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals(e.getClass(), DueDateParseException.class);
        }
    }

    @Test
    public void Deadline_dueDatePassed_exceptionThrown() {
        try {
            Deadline temp1 = new Deadline("temp1", fileDateTimeParser.parseDueDate("2019-09-15"));
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), DatePassedException.class);
        }
    }
}
