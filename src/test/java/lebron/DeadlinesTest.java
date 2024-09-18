package lebron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlinesTest {

    @Test
    public void testDeadlinesCreation() {
        Deadlines deadlines = new Deadlines("Return book", LocalDate.of(2024, 9, 1));
        assertEquals("Return book", deadlines.getDescription());
        assertFalse(deadlines.getStatus());
    }

    @Test
    public void testMarking() {
        Deadlines deadlines = new Deadlines("Return book", LocalDate.of(2024, 9, 1));
        deadlines.markAsDone();
        assertTrue(deadlines.getStatus());
    }
}
