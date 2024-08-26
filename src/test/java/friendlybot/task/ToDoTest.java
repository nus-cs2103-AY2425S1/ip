package friendlybot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void createToDo_markAsCompleted_correctStringOutput() {
        ToDo task = new ToDo("test task");
        task.markAsDone();
        assertEquals("[T][X] test task", task.toString());
    }

    @Test
    public void createToDo_incomplete_correctStringOutput() {
        ToDo task = new ToDo("test task");
        assertEquals("[T][ ] test task", task.toString());
    }
}
