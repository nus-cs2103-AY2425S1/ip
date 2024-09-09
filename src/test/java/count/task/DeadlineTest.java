package count.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private static final LocalDate LOCAL_DATE_EXAMPLE = LocalDate.of(2024, 9, 10);
    @Test
    public void getDescriptionTest() {
        Deadline t = new Deadline("test description", "10/09/2024");
        assertEquals("test description", t.getDescription());
    }

    @Test
    public void getStatusIconTest() {
        Deadline t = new Deadline("test description", LOCAL_DATE_EXAMPLE, true);
        assertEquals("X", t.getStatusIcon());
    }

    @Test
    public void setCompletionStatusTest() {
        Deadline t = new Deadline("test description", LOCAL_DATE_EXAMPLE, false);
        t.setCompletion(true);
        assertEquals("X", t.getStatusIcon());
    }

    @Test
    public void toStringCompletionTest() {
        Deadline t = new Deadline("test description", LOCAL_DATE_EXAMPLE, false);
        assertEquals("[D][ ] test description (by: September 10 2024)", t.toString());
    }

    @Test
    public void dateTimeParseExceptionTest() {
        Exception exception = assertThrows(DateTimeParseException.class, ()
                -> new Deadline("test description", "300/20/2024"));
        assertEquals("Text '300/20/2024' could not be parsed at index 2", exception.getMessage());
    }
}
