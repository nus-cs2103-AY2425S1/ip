package count.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import count.exception.InvalidTimelineException;

public class EventTest {
    private static final LocalDate LOCAL_DATE_EXAMPLE_FROM = LocalDate.of(2024, 9, 10);
    private static final LocalDate LOCAL_DATE_EXAMPLE_TO = LocalDate.of(2024, 9, 20);
    @Test
    public void getDescriptionTest() throws InvalidTimelineException {
        Event t = new Event("test description", "10/09/2024", "05/09/2024");
        assertEquals("test description", t.getDescription());
    }

    @Test
    public void getStatusIconTest() throws InvalidTimelineException {
        Event t = new Event("test description", LOCAL_DATE_EXAMPLE_TO,
                LOCAL_DATE_EXAMPLE_FROM, true);
        assertEquals("X", t.getStatusIcon());
    }

    @Test
    public void setCompletionStatusTest() throws InvalidTimelineException {
        Event t = new Event("test description", LOCAL_DATE_EXAMPLE_TO,
                LOCAL_DATE_EXAMPLE_FROM, false);
        t.setCompletion(true);
        assertEquals("X", t.getStatusIcon());
    }

    @Test
    public void toStringCompletionTest() throws InvalidTimelineException {
        Event t = new Event("test description", LOCAL_DATE_EXAMPLE_TO,
                LOCAL_DATE_EXAMPLE_FROM, false);
        assertEquals("[E][ ] test description (from: September 10 2024 to: September 20 2024)", t.toString());
    }

    @Test
    public void dateTimeParseExceptionTest() {
        Exception exception = assertThrows(DateTimeParseException.class, ()
                -> new Event("test description", "300/20/2024", "05/09/2024"));
        assertEquals("Text '300/20/2024' could not be parsed at index 2", exception.getMessage());
    }

    @Test
    public void invalidTimeLineExceptionTest() {
        Exception exception = assertThrows(InvalidTimelineException.class, ()
                -> new Event("test description", "05/09/2024", "10/09/2024"));
        assertEquals("The end time cannot be before or the same as the start time!", exception.getMessage());
    }
}
