package hypebot.task;

import static hypebot.common.Messages.ERROR_EVENT_TIMES_INORDERED;
import static hypebot.common.Messages.MESSAGE_DELETING_PAST_EVENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import hypebot.exception.datetime.EventDateTimeParseException;
import hypebot.parser.datetime.FileDateTimeParser;

/**
 * Represents tests for {@link Event}.
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see FileDateTimeParser
 */
public class EventTest {
    private final FileDateTimeParser fileDateTimeParser = new FileDateTimeParser();

    @Test
    public void eventInvalidTimeFormatsEventDateTimeParseExceptionThrown() {
        try {
            Event temp1 = new Event(
                    "temp1",
                    fileDateTimeParser.parseEventTimes("2019/2021")[0],
                    fileDateTimeParser.parseEventTimes("2019/2021")[1]);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals(e.getClass(), EventDateTimeParseException.class);
        }
    }

    @Test
    public void eventFromFileTimesPassedByDatePassedExceptionThrownWithDeletingMessage() {
        try {
            Event temp1 = new Event(
                    "temp1",
                    fileDateTimeParser.parseEventTimes("2019-09-15 1800/2021-09-15 1800")[0],
                    fileDateTimeParser.parseEventTimes("2019-09-15 1800/2021-09-15 1800")[1]);
        } catch (Exception e) {
            assertEquals(MESSAGE_DELETING_PAST_EVENT, e.getMessage());
        }
    }

    @Test
    public void eventTimesNotInOrderIllegalEventTimesExceptionThrown() {
        try {
            Event temp1 = new Event(
                    "temp1",
                    fileDateTimeParser.parseEventTimes("2024-11-22 1800/2024-11-15 1600")[0],
                    fileDateTimeParser.parseEventTimes("2024-11-22 1800/2024-11-15 1600")[1]);
        } catch (Exception e) {
            assertEquals(ERROR_EVENT_TIMES_INORDERED, e.getMessage());
        }
    }
}
