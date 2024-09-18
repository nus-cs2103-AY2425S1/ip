package janet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @BeforeAll
    public static void setUp() {
        Locale.setDefault(new Locale("en", "SG"));
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Singapore"));
    }

    public static boolean equals(Deadline d1, Deadline d2) {
        if (d2 == d1) {
            return true;
        }
        if (d1 == null || d2 == null) {
            return false;
        }
        if (Objects.equals(d1.getDescription(), d2.getDescription())
                && Objects.equals(d1.getSymbol(), d2.getSymbol())) {
            return d1.getScheduledDateAndTime().isEqual(d2.getScheduledDateAndTime());
        }
        return false;
    }

    /**
     * tests whether the 2 different Deadline constructors will produce the same object
     */
    @Test
    public void deadline_creation_test() throws JanetException {
        Deadline deadline_one = new Deadline("deadline return book /by 2024-11-01 17:00");

        DateTimeFormatter stringToDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        LocalDateTime dueDate = LocalDateTime.of(2024, 11, 1, 17, 0);
        dueDate.format(stringToDateTime);

        Deadline deadline_two = new Deadline("return book", "D", dueDate);

        assertTrue(equals(deadline_one, deadline_two));
    }

    /**
     * tests case where description of deadline is omitted and correct error message is printed.
     */
    @Test
    public void description_omission_test() throws JanetException {
        // set the numOfTasksInList parameter to be some random number (example: 3)
        String[] cd = new String[] {"deadline"};
        JanetException exception = assertThrows(JanetException.class,
                () -> {Parser.checkInaccurateCommand(cd, 3);});

        assertEquals(exception.getMessage(), "WHOOPS! You can't leave out the task's description!");
    }

    /**
     * tests case where '/by' is omitted and correct error message is printed.
     */
    @Test
    public void invalid_deadline_test() throws JanetException {
        String[] cd = new String[] {"deadline", "return", "book"};
        JanetException exception = assertThrows(JanetException.class,
                () -> {new Deadline("deadline return book");});

        assertEquals(exception.getMessage(), "WHOOPS! Missing/Wrong keywords for creating deadline...");
    }

    /**
     * tests case where due date is omitted and correct error message is printed.
     */
    @Test
    public void dueDate_omission_test() throws JanetException {
        String[] cd = new String[] {"deadline", "return", "book", "/by"};
        JanetException exception = assertThrows(JanetException.class,
                () -> {new Deadline("deadline return book /by");});

        assertEquals(exception.getMessage(), "WHOOPS! Ensure that the due date is in the format: yyyy-MM-dd HH:mm (24hr)");
    }

    @Test
    public void deadlineCreationFromTxtTest() throws JanetException {
        String[] textLine = "D | 0 | return bike | Nov 30 2024 18:00 pm".split("\\|");
        Deadline actualDeadline = new Deadline(textLine);
        Deadline expectedDeadline = new Deadline("deadline return bike /by 2024-11-30 18:00");

        assertTrue(equals(actualDeadline, expectedDeadline));
    }
}
