import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import dave.task.Event;
import dave.exceptions.InvalidDateTimeFormatException;
import dave.exceptions.InvalidDescriptionException;


public class EventTest {

    @Test
    public void testParseDate_validDate() throws InvalidDescriptionException,InvalidDateTimeFormatException {
        Event event = new Event("Meeting /from 2024-08-02 1800 /to 1900");
        LocalDate expectedDate = LocalDate.of(2024, 8, 2);
        assertEquals(expectedDate, event.fromDate);
    }

    @Test
    public void testParseDate_invalidDate() {
        assertThrows(InvalidDateTimeFormatException.class, () -> {
            new Event("Meeting /from 09-02-2024 1800 /to 1900");
        });
    }

    @Test
    public void testParseTime_validTime() throws InvalidDescriptionException, InvalidDateTimeFormatException {
        Event event = new Event("Meeting /from 2024-08-02 1800 /to 1900");
        LocalTime expectedTime = LocalTime.of(18, 0);
        assertEquals(expectedTime, event.fromTime);
    }

    @Test
    public void testEventCreation_missingFromKeyword() {
        assertThrows(InvalidDescriptionException.class, () -> {
            new Event("Meeting 2024-08-02 1800 /to 1900"); // Missing '/from'
        });
    }

    @Test
    public void testEventCreation_missingToKeyword() {
        assertThrows(InvalidDescriptionException.class, () -> {
            new Event("Meeting /from 2024-08-02 1800 1900"); // Missing '/to'
        });
    }

    @Test
    public void testParseTime_invalidTime() {
        assertThrows(InvalidDateTimeFormatException.class, () -> {
            new Event("Meeting /from 2024-08-02 18:00 /to 1900");
        });
    }
}
