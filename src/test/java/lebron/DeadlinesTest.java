package lebron;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlinesTest {

    @Test
    public void testDeadlinesCreation() {
        Deadlines deadlines = new Deadlines("Return book", LocalDate.of(2024, 9, 1));
        assertEquals("Return book",deadlines.getDescription());
        assertFalse(deadlines.getStatus());
    }

    @Test
    public void testMarking() {
        Deadlines deadlines = new Deadlines("Return book", LocalDate.of(2024, 9, 1));
        deadlines.markAsDone();
        assertTrue(deadlines.getStatus());
    }
}
