package brainrot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class DeadlineTest {
    private Deadline deadline;

    @BeforeEach
    public void setUp() {
        // Initialize the Deadline object with a specific date and mark it as done
        deadline = new Deadline("Submit assignment" , "Nov 15 2020 23:59");
        deadline.mark();  // Mark the deadline as done
    }

    @Test
    public void testToString_markedTask() {
        // Test the string representation of the deadline when marked as done
        String expected = "[D][X] Submit assignment (by: Nov 15 2020 23:59)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testToFileString_markedTask() {
        // Test the file string representation of the marked deadline
        String expected = "[D][X]/Submit assignment/Nov 15 2020 23:59";
        assertEquals(expected, deadline.toFileString());
    }
}
