package wolfie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void testValidDeadline() {
        LocalDateTime by = LocalDateTime.of(2023, 10, 1, 12, 0);
        Deadline deadline = new Deadline("Test Deadline", by, false);
        assertEquals("Test Deadline", deadline.getDescription());
        assertEquals(by, deadline.getBy());
    }

}
