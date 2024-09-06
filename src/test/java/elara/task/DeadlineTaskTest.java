package elara.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the DeadlineTask class.
 */
public class DeadlineTaskTest {

    /**
     * Tests that the toString() method of DeadlineTask returns the correct string format.
     * The test constructs a DeadlineTask with a deadline of "Aug 6 2024 01:00 pm" and
     * checks that the string representation matches the expected output.
     */
    @Test
    public void testToString() {
        LocalDateTime deadline = LocalDateTime.parse("2024-08-06 1300", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        assertEquals("[D][ ] return book (by: Aug 6 2024 01:00 pm)",
                new DeadlineTask("return book", deadline, false).toString());
    }
}
