package toothless.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import toothless.exceptions.InvalidTimelineException;
import toothless.exceptions.ToothlessExceptions;

/**
 * Tests for Event class.
 */
public class EventTest {

    /**
     * Tests if the event is created correctly.
     *
     * @throws ToothlessExceptions if the date and time format is invalid
     */
    @Test
    public void testEventCreation() throws ToothlessExceptions {
        Event event = new Event("Dragon training", "12/12/2050 1000", "12/12/2050 1200");
        assertEquals("Dragon training", event.description);
        assertEquals(LocalDateTime.of(2050, 12, 12, 10, 0), event.eventStart);
        assertEquals(LocalDateTime.of(2050, 12, 12, 12, 0), event.eventEnd);
    }

    /**
     * Tests if an exception is thrown for invalid date format.
     */
    @Test
    public void testEventCreationInvalidFormat() {
        Exception exception = assertThrows(ToothlessExceptions.class, () -> {
            new Event("Dragon training", "12.12.2050 1000", "12.12.2050 1200");
        });
        String expectedMessage = "Please enter a valid date and time\n"
                + "in the format: dd/MM/yyyy HHmm or dd-MM-yyyy HHmm\n";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    /**
     * Tests if an exception is thrown when the start date is after the end date.
     */
    @Test
    public void testEventCreationStartAfterEnd() {
        Exception exception = assertThrows(InvalidTimelineException.class, () -> {
            new Event("Dragon training", "12/12/2050 1300", "12/12/2050 1200");
        });
        String expectedMessage = "the start date and time should be before the end date and time.\n";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    /**
     * Tests if an exception is thrown for past dates.
     */
    @Test
    public void testEventCreationPastDates() {
        Exception exception = assertThrows(InvalidTimelineException.class, () -> {
            new Event("Dragon training", "12/12/2020 1000", "12/12/2020 1200");
        });
        String expectedMessage = "the event start or end date and time is earlier than today! "
                + "Please enter a valid date and time.\n";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    /**
     * Tests the toFileString method.
     *
     * @throws ToothlessExceptions if the date and time format is invalid
     */
    @Test
    public void testToFileString() throws ToothlessExceptions {
        Event event = new Event("Dragon training", "12/12/2050 1000", "12/12/2050 1200");
        assertEquals("E | 0 | Dragon training | 12 Dec 2050 10:00 | 12 Dec 2050 12:00", event.toFileString());
    }

    /**
     * Tests the toString method.
     *
     * @throws ToothlessExceptions if the date and time format is invalid
     */
    @Test
    public void testToString() throws ToothlessExceptions {
        Event event = new Event("Dragon training", "12/12/2050 1000", "12/12/2050 1200");
        assertEquals("[E][ ] Dragon training (from: 12 Dec 2050 10:00 to: 12 Dec 2050 12:00)", event.toString());
    }
}
