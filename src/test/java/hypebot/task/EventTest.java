package hypebot.task;

import static hypebot.common.Messages.ERROR_EVENT_TIMES_INORDERED;
import static hypebot.common.Messages.MESSAGE_DELETING_PAST_EVENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import hypebot.parser.DateTimeParser;
import hypebot.parser.EventDateTimeParseException;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void Event_invalidTimeFormats_exceptionThrown() {
        try {
            Event temp1 = new Event(
                    "temp1",
                    DateTimeParser.parseEventTimesFile("2019", "2021")[0],
                    DateTimeParser.parseEventTimesFile("2019", "2021")[1]);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals(e.getClass(), EventDateTimeParseException.class);
        }
    }

    @Test
    public void Event_fromFileTimesPassedBy_exceptionThrown() {
        try {
            Event temp1 = new Event(
                    "temp1",
                    DateTimeParser.parseEventTimesFile("2019-09-15 18:00", "2021-09-15 18:00")[0],
                    DateTimeParser.parseEventTimesFile("2019-09-15 18:00", "2021-09-15 18:00")[1]);
        } catch (Exception e) {
            assertEquals(MESSAGE_DELETING_PAST_EVENT, e.getMessage());
        }
    }

    @Test
    public void Event_timesNotInOrder_exceptionThrown() {
        try {
            Event temp1 = new Event(
                    "temp1",
                    DateTimeParser.parseEventTimesFile("2024-09-15 18:00", "2024-09-15 16:00")[0],
                    DateTimeParser.parseEventTimesFile("2024-09-15 18:00", "2024-09-15 16:00")[1]);
        } catch (Exception e) {
            assertEquals(ERROR_EVENT_TIMES_INORDERED, e.getMessage());
        }
    }
}
