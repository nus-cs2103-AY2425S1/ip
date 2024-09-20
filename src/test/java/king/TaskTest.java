package king;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import king.task.Deadline;
import king.task.Event;
import king.task.Task;
import king.task.Todo;

class TaskTest {

    @Test
    void testTaskCreation() {
        Task task = new Todo("Write JUnit tests");
        assertEquals("[T][ ] Write JUnit tests", task.toString());
    }

    @Test
    void testMarkAsDone() {
        Task task = new Todo("Write JUnit tests");
        task.markAsDone();
        assertEquals("[T][X] Write JUnit tests", task.toString());
    }

    @Test
    void testMarkAsUndone() {
        Task task = new Todo("Write JUnit tests");
        task.markAsDone();
        task.markAsUndone();
        assertEquals("[T][ ] Write JUnit tests", task.toString());
    }

    @Test
    void testDeadlineCreation() {
        Task deadline = new Deadline("Submit assignment", LocalDateTime.of(2024, 9, 15, 23, 59));
        assertEquals("[D][ ] Submit assignment (by: Sep 15 2024 23:59)", deadline.toString());
    }

    @Test
    void testEventCreation() {
        Task event = new Event("Project meeting", LocalDateTime.of(2024, 9, 10, 14, 0),
                LocalDateTime.of(2024, 9, 10, 16, 0));
        assertEquals("[E][ ] Project meeting (from: Sep 10 2024 14:00 to: Sep 10 2024 16:00)", event.toString());
    }
}

