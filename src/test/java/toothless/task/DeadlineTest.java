package toothless.task;

import org.junit.jupiter.api.Test;
import toothless.exceptions.ToothlessExceptions;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void testDeadlineCreation() throws ToothlessExceptions {
        Deadline deadline = new Deadline("Submit assignment", "12/12/2023 2359");
        assertEquals("Submit assignment", deadline.description);
        assertEquals(LocalDateTime.of(2023, 12, 12, 23, 59), deadline.deadline);
    }

    @Test
    public void testDeadlineCreationInvalidFormat() {
        Exception exception = assertThrows(ToothlessExceptions.class, () -> {
            new Deadline("Submit assignment", "12-12-2023 25:00");
        });
        String expectedMessage = "Please enter a valid date and time\n" +
                "in the format: dd/MM/yyyy HHmm or dd-MM-yyyy HHmm\n" +
                "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testToFileString() throws ToothlessExceptions {
        Deadline deadline = new Deadline("Submit assignment", "12/12/2023 2359");
        assertEquals("D | 0 | Submit assignment | 12/12/2023 2359", deadline.toFileString());
    }

    @Test
    public void testToString() throws ToothlessExceptions {
        Deadline deadline = new Deadline("Submit assignment", "12/12/2023 2359");
        assertEquals("[D][ ] Submit assignment (by: 12 Dec 2023 23:59)", deadline.toString());
    }
}