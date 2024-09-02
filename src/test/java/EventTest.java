import org.junit.jupiter.api.Test;
import dave.task.Event;
import dave.exceptions.InvalidDateTimeFormatException;
import dave.exceptions.InvalidDescriptionException;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class EventTest {

    @Test
    public void testParseDate_validDate() throws InvalidDescriptionException,InvalidDateTimeFormatException {
        Event event = new Event("Meeting /from 2024-09-02 1800 /to 1900");
        LocalDate expectedDate = LocalDate.of(2024, 9, 2);
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
        Event event = new Event("Meeting /from 2024-09-02 1800 /to 1900");
        LocalTime expectedTime = LocalTime.of(18, 0);
        assertEquals(expectedTime, event.fromTime);
    }

    @Test
    public void testParseTime_invalidTime() {
        assertThrows(InvalidDateTimeFormatException.class, () -> {
            new Event("Meeting /from 2024-09-02 18:00 /to 1900");
        });
    }
}
