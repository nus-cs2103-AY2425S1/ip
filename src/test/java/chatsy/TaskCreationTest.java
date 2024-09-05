package chatsy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import chatsy.tasks.DeadlineTask;
import chatsy.tasks.EventTask;
import chatsy.tasks.TodoTask;
class TaskCreationTest {

    @Test
    void testTodoTaskCreation() {
        TodoTask todo = new TodoTask("Buy groceries");
        assertEquals("Buy groceries", todo.getDescription());
        assertFalse(todo.isDone());

        todo.markAsDone();
        assertTrue(todo.isDone());
    }

    @Test
    void testDeadlineTaskCreation() {
        LocalDate deadline = LocalDate.of(2024, 8, 29);
        DeadlineTask deadlineTask = new DeadlineTask("Submit assignment", deadline);

        assertEquals("Submit assignment", deadlineTask.getDescription());
        assertEquals(deadline, deadlineTask.getBy());
        assertFalse(deadlineTask.isDone());

        deadlineTask.markAsDone();
        assertTrue(deadlineTask.isDone());
    }

    @Test
    void testEventTaskCreation() {
        LocalDateTime from = LocalDateTime.of(2024, 9, 1, 14, 0);
        LocalDateTime to = LocalDateTime.of(2024, 9, 1, 16, 0);
        EventTask eventTask = new EventTask("Team meeting", from, to);

        assertEquals("Team meeting", eventTask.getDescription());
        assertEquals(from, eventTask.getFrom());
        assertEquals(to, eventTask.getTo());
        assertFalse(eventTask.isDone());

        eventTask.markAsDone();
        assertTrue(eventTask.isDone());
    }
}
