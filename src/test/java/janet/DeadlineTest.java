package janet;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    public static boolean equals(Deadline d1, Deadline d2) {
        if (d2 == d1) {
            return true;
        }
        if (d1 == null || d2 == null) {
            return false;
        }
        if (Objects.equals(d1.getDescription(), d2.getDescription())
                && Objects.equals(d1.getSymbol(), d2.getSymbol())) {
            return Objects.equals(d1.getScheduledDate(), d2.getScheduledDate());
        }
        return false;
    }

    /**
     * tests whether the 2 different Deadline constructors will produce the same object
     */
    @Test
    public void Deadline_creation_test() throws JanetException {
        Deadline deadline_one = new Deadline("deadline return book /by 2024-09-01 17:00");

        DateTimeFormatter stringToDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        LocalDateTime dueDate = LocalDateTime.of(2024, 9, 1, 17, 0);
        dueDate.format(stringToDateTime);

        Deadline deadline_two = new Deadline("return book", "D", dueDate);

        assertTrue(equals(deadline_one, deadline_two));
    }

    /**
     * tests case where description of deadline is omitted and correct error message is printed.
     */
    @Test
    public void Description_omission_test() throws JanetException {
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
    public void Invalid_deadline_test() throws JanetException {
        String[] cd = new String[] {"deadline", "return", "book"};
        JanetException exception = assertThrows(JanetException.class,
                () -> {new Deadline("deadline return book");});

        assertEquals(exception.getMessage(), "WHOOPS! Missing/Wrong keywords for creating deadline...");
    }

    /**
     * tests case where due date is omitted and correct error message is printed.
     */
    @Test
    public void DueDate_omission_test() throws JanetException {
        String[] cd = new String[] {"deadline", "return", "book", "/by"};
        JanetException exception = assertThrows(JanetException.class,
                () -> {new Deadline("deadline return book /by");});

        assertEquals(exception.getMessage(), "WHOOPS! Missing/Wrong keywords for creating deadline...");
    }
}
