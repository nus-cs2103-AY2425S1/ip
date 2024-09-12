package blacknut.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testToFileFormat() {
        Deadline deadline = new Deadline("Submit assignment", "2024-09-15 23:59");
        String expectedOutput = "D | 0 | Submit assignment | 2024-09-15 23:59";
        assertEquals(expectedOutput, deadline.toFileFormat());
    }

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("Submit assignment", "2024-09-15 23:59");
        String expectedOutput = "[D][ ] Submit assignment (by: Sep 15 2024, 23:59)";
        assertEquals(expectedOutput, deadline.toString());
    }
}
