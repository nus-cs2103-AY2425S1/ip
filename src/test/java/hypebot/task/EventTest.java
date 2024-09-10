package hypebot.task;

import org.junit.jupiter.api.Test;

import static hypebot.common.Messages.ERROR_EVENT_TIMES_INORDERED;
import static hypebot.common.Messages.ERROR_EVENT_TIME_PASSED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void Event_invalidTimeFormats_exceptionThrown() {
        try {
            Event temp1 = new Event("temp1", "2019", "2021");
            Event temp2 = new Event("temp2", "2019-09", "2019-09-12");
            Event temp3 = new Event("temp3", "2019-09-18 19:00", "2019-09-18 2000");
            Event temp4 = new Event("temp4", "September 8th 2024", "July 8th 2024");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals(e.getClass(), EventDateTimeParseException.class);
        }
    }

    @Test
    public void Event_timesPassedBy_exceptionThrown() {
        try {
            Event temp1 = new Event("temp1", "2019-09-15 18:00", "2021-09-15 18:00");
        } catch (Exception e) {
            assertEquals(ERROR_EVENT_TIME_PASSED, e.getMessage());
        }
    }

    @Test
    public void Event_timesNotInOrder_exceptionThrown() {
        try {
            Event temp1 = new Event("temp1", "2024-09-15 18:00", "2024-09-15 16:00");
        } catch (Exception e) {
            assertEquals(ERROR_EVENT_TIMES_INORDERED, e.getMessage());
        }
    }
}
