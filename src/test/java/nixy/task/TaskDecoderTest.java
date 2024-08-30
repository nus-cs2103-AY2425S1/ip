package nixy.task;

import nixy.task.DeadlineTask;
import nixy.task.EventTask;
import nixy.task.Task;
import nixy.task.TodoTask;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskDecoderTest {

    @Test
    void parseTodoSaveString() {
        Task task = TaskDecoder.parseTask("T|X|Buy groceries");
        assertTrue(task instanceof TodoTask);
        TodoTask todoTask = (TodoTask) task;
        assertEquals("Buy groceries", todoTask.getDescription());
        assertTrue(todoTask.isDone());
    }

    @Test
    void parseDeadlineSaveString() {
        Task task = TaskDecoder.parseTask("D| |Submit assignment|2024-12-01");
        assertTrue(task instanceof DeadlineTask);
        DeadlineTask deadlineTask = (DeadlineTask) task;
        assertEquals("Submit assignment", deadlineTask.getDescription());
        assertEquals(LocalDate.of(2024, 12, 1), deadlineTask.getDeadline());
        assertFalse(deadlineTask.isDone());
    }

    @Test
    void parseEventSaveString() {
        Task task = TaskDecoder.parseTask("E|X|Team meeting|2024-12-01|2024-12-02");
        assertTrue(task instanceof EventTask);
        EventTask eventTask = (EventTask) task;
        assertEquals("Team meeting", eventTask.getDescription());
        assertEquals(LocalDate.of(2024, 12, 1), eventTask.getStartDate());
        assertEquals(LocalDate.of(2024, 12, 2), eventTask.getEndDate());
        assertTrue(eventTask.isDone());
    }

    @Test
    void partialSaveString() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            TaskDecoder.parseTask("T|"));
        assertEquals("Invalid task string: T|", exception.getMessage());
    }

    @Test
    void gibberishSaveString() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            TaskDecoder.parseTask("Gibberish"));
        assertEquals("Invalid task string: Gibberish", exception.getMessage());
    }
}
