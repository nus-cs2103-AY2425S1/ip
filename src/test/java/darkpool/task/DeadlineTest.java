package darkpool.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import darkpool.DarkpoolException;


class DeadlineTest {

    private DateTimeFormatter formatter;
    private String validByTime;
    private String invalidTime;
    private String description;

    @BeforeEach
    void setUp() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        validByTime = "30-08-2024 18:00";
        invalidTime = "30-08-2024 18:00:00";
        description = "Submit assignment";
    }

    @Test
    void testDeadlineCreationSuccess() throws DarkpoolException {
        Deadline deadline = new Deadline(description, validByTime, false);
        assertNotNull(deadline);
        assertEquals(LocalDateTime.parse(validByTime, formatter), deadline.byTime);
    }

    @Test
    void testDeadlineCreationInvalidTimeFormat() {
        DarkpoolException exception = assertThrows(DarkpoolException.class, () ->
                new Deadline(description, invalidTime, false));
        assertEquals("know what a date is? (dd-mm-yyyy hh:mm)", exception.getMessage());
    }

    @Test
    void testToString() throws DarkpoolException {
        Deadline deadline = new Deadline(description, validByTime, false);
        String expected = "[D][ ] Submit assignment (by:30-08-2024 18:00)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    void testToFileString() throws DarkpoolException {
        Deadline deadline = new Deadline(description, validByTime, false);
        String expected = "D | 0 | Submit assignment | 30-08-2024 18:00\n";
        assertEquals(expected, deadline.toFileString());
    }

    @Test
    void testDeadlineCreationWithIsDoneTrue() throws DarkpoolException {
        Deadline deadline = new Deadline(description, validByTime, true);
        String expectedString = "[D][X] Submit assignment (by:30-08-2024 18:00)";
        assertEquals(expectedString, deadline.toString());
    }
}
