package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testMarkAsDone() {
        Task task = new ToDo("Test task");
        task.markAsDone();
        assertEquals("X", task.getStatusIcon(), "The task should be marked as done.");
    }

    @Test
    void testUnmark() {
        Task task = new ToDo("Test task");
        task.markAsDone();
        task.unmark();
        assertEquals(" ", task.getStatusIcon(), "The task should be unmarked (not done).");
    }

    @Test
    void testParseToDo() {
        String[] details = {"T", "0", "Test ToDo task"};
        Task task = Task.parse(details);
        assertEquals("[T][ ] Test ToDo task", task.toString());
    }

    @Test
    void testParseDeadline() {
        String[] details = {"D", "0", "Test Deadline task", "12/12/2024 1800"};
        Task task = Task.parse(details);
        assertEquals("[D][ ] Test Deadline task (by: Dec 12 2024, 6:00 pm)", task.toString());
    }

    @Test
    void testParseEvent() {
        String[] details = {"E", "0", "Test Event task", "12/12/2024 1800", "13/12/2024 1800"};
        Task task = Task.parse(details);
        assertEquals("[E][ ] Test Event task (from: Dec 12 2024, 6:00 pm to: Dec 13 2024, 6:00 pm)", task.toString());
    }

    @Test
    void testParseInvalidInput() {
        String[] details = {"X", "0", "Test Invalid task"};
        assertThrows(IllegalArgumentException.class, () -> Task.parse(details), "An invalid task type should throw an exception.");
    }
}
