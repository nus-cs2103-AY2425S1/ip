package hypebot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import hypebot.exception.datetime.DueDateParseException;
import hypebot.exception.illegal.DatePassedException;
import hypebot.parser.datetime.FileDateTimeParser;

/**
 * Represents tests for {@link Deadline}.
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see FileDateTimeParser
 */
public class DeadlineTest {
    private final FileDateTimeParser fileDateTimeParser = new FileDateTimeParser();

    @Test
    public void deadlineInvalidDateFormatDueDateParseExceptionThrown() {
        try {
            Deadline temp1 = new Deadline("temp1", fileDateTimeParser.parseDueDate("2019"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals(e.getClass(), DueDateParseException.class);
        }
    }

    @Test
    public void deadlineDueDatePassedDatePassedExceptionThrown() {
        try {
            Deadline temp1 = new Deadline("temp1", fileDateTimeParser.parseDueDate("2019-09-15"));
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), DatePassedException.class);
        }
    }
}
