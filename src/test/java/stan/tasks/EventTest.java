package stan.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import stan.exceptions.StanInvalidDateTimeFormatException;



class EventTest {

    @Test
    void createValidEvent() throws StanInvalidDateTimeFormatException {
        Event event = new Event("Team meeting", "2023-08-30 1400", "2023-08-30 1600");
        assertEquals("[E][ ] Team meeting (from: Aug 30 2023 2:00PM to: 4:00PM)", event.toString());
    }

    @Test
    void createEventWithInvalidDateFormat() {
        Exception exception = assertThrows(StanInvalidDateTimeFormatException.class, () -> {
            new Event("Team meeting", "08-30-2023 1400", "2023-08-30 1600");
        });
        assertEquals("The event time must be in the format yyyy-MM-dd "
                + "HHmm.\nE.g. /from 2021-07-29 1000 /to 2021-07-30 2200", exception.getMessage());
    }

    @Test
    void createEventWithInvalidTimeOrder() {
        Exception exception = assertThrows(StanInvalidDateTimeFormatException.class, () -> {
            new Event("Team meeting", "2023-08-30 1600", "2023-08-30 1400");
        });
        assertEquals("The 'from' date and time must be before the 'to' date and time.", exception.getMessage());
    }

    @Test
    void testEventToStorageString() throws StanInvalidDateTimeFormatException {
        Event event = new Event("Team meeting", "2023-08-30 1400", "2023-08-30 1600");
        assertEquals("E | 0 | Team meeting | 2023-08-30 1400 | 2023-08-30 1600", event.toStorageString());
    }
}
