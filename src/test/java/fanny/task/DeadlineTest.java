package fanny.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeadlineTest {

    private Deadline deadline;
    private LocalDateTime dueDate;

    @BeforeEach
    public void setUp() {
        dueDate = LocalDateTime.of(2024, 12, 22, 12, 0);
        deadline = new Deadline("Submit assignment", dueDate);
    }

    @Test
    public void testToString() {
        String expected = "[D][ ] Submit assignment (by: Dec 22, 2024 12:00)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testGetDeadline() {
        assertEquals("Dec 22, 2024 12:00", deadline.getDeadline());
    }
}
