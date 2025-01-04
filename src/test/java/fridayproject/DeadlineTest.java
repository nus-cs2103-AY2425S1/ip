package fridayproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {
    
    @Test
    public void testToFileString() {
        Deadline deadline = new Deadline("Submit assignment", "2024-12-31");
        String expected = "D | 0 | Submit assignment | 2024-12-31";
        assertEquals(expected, deadline.toFileString());
    }

    @Test
    public void testMarkAsDone() {
        Deadline deadline = new Deadline("Submit assignment", "2024-12-31");
        deadline.markAsDone();
        assertTrue(deadline.isDone);
    }
}

// done with the guidance of ChatGPT