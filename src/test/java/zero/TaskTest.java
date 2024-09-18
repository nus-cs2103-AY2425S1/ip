package zero;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import zero.exception.ZeroException;
import zero.task.Deadline;
import zero.task.Event;
import zero.task.Task;
import zero.task.Todo;
import zero.util.Parser;

public class TaskTest {

    @Test
    public void testTask() {
        Task task = new Task("Test task");
        assertEquals("[ ] Test task", task.toString());
    }

    @Test
    public void testTodo() {
        Todo todo = new Todo("Study");
        assertEquals("[T][ ] Study", todo.toString());
        todo.markAsDone();
        assertEquals("[T][X] Study", todo.toString());  
    }

    @Test
    public void testDeadline() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 9, 1, 14, 30);
        Deadline deadline = new Deadline("Submit report", dateTime);
        assertEquals("[D][ ] Submit report (by: 1 SEPTEMBER 2024)", deadline.toString());
    }

    @Test
    public void testEvent() {
        LocalDateTime from = LocalDateTime.of(2024, 9, 1, 14, 30);
        LocalDateTime to = LocalDateTime.of(2024, 9, 1, 16, 30);
        Event event = new Event("Project meeting", from, to);
        assertEquals("[E][ ] Project meeting (from: 1 SEPTEMBER 2024 to: 1 SEPTEMBER 2024)", event.toString());
    }

    @Test
    public void testDate() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 9, 1, 14, 30);
        try {
            assertEquals(dateTime, Parser.handleDate("2024-09-01 1430"));
        } catch (ZeroException e) {
            fail("Exception should not be thrown for valid date.");
        }
    }

    @Test
    public void testDateInvalidFormat() {
        try {
            Parser.handleDate("2024/09/01 1430");
            fail("Exception should have been thrown for invalid date format.");
        } catch (ZeroException e) {
            assertEquals("Invalid date/time format. Please use 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.", e.getMessage());
        }
    }

    @Test
    public void testSnoozeDeadline() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 9, 1, 14, 30);
        Deadline deadline = new Deadline("Submit report", dateTime);
        deadline.snooze();
        assertEquals("[D][ ] Submit report (by: 2 SEPTEMBER 2024)", deadline.toString());
    }

    @Test
    public void testMarkTask() {
        Task task = new Task("Complete homework");
        assertEquals("0", task.isDone());
        task.markAsDone();
        assertEquals("1", task.isDone());
    }

    @Test
    public void testUnmarkTask() {
        Task task = new Task("Finish project");
        task.markAsDone();
        assertEquals("1", task.isDone());

        task.markAsNotDone();
        assertEquals("0", task.isDone());
    }


}
