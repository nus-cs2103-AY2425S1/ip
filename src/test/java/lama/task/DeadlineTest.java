package lama.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;



/**
 * Test class for Deadline class.
 * Contains unit test case for deadline class.
 */
public class DeadlineTest {

    /**
     * Test the constructor of deadline class.
     * Verifies that a Deadline object is correctly initialized and
     * its toString method returns the expected format.
     */
    @Test
    public void constructorTest() {
        String description = "Return Book";
        LocalDate by = LocalDate.of(2025, 12, 12);
        Task deadline = new Deadline(description, by);

        String output = "[D][ ] Return Book (by: Dec 12 2025)";

        assertEquals(output, deadline.toString());
    }

    /**
     * Test the toFile method.
     * Verifies that the toFile method returns the correct string representation
     * of the Deadline object for file storage.
     */
    @Test
    public void toFileTest() {
        String description = "Return Book";
        LocalDate by = LocalDate.of(2025, 12, 12);
        Task deadline = new Deadline(description, by);

        String output = "D | 0 | Return Book | 2025-12-12";

        assertEquals(output, deadline.toFileFormat());
    }
}
