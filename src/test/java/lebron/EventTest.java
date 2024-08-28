package lebron;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void testEventCreation() {
        Event event = new Event("Project Meeting", LocalDate.of(2024, 9, 1),
                LocalDate.of(2024, 9, 2));
        assertEquals(LocalDate.of(2024, 9, 2),event.getEnd());
        assertFalse(event.getStatus());
    }

}