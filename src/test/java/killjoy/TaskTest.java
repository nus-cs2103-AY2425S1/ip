package killjoy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import killjoy.task.Task;

public class TaskTest {

    private Task todo;
    private Task deadline;
    private Task event;

    @BeforeEach
    public void createNewObjects() {
        todo = new Task("dance", Task.TaskType.TODO);
        deadline = new Task("meowwwwww", Task.TaskType.DEADLINE);
        event = new Task("KAYOOOOOOO", Task.TaskType.EVENT);
    }

    @Test
    public void testTaskCreation() {
        assertEquals("dance", todo.getDescription());
        assertEquals(Task.TaskType.TODO, todo.getTaskType());
        assertFalse(todo.getTaskStatus());

        assertEquals("meowwwwww", deadline.getDescription());
        assertEquals(Task.TaskType.DEADLINE, deadline.getTaskType());
        assertFalse(deadline.getTaskStatus());

        assertEquals("KAYOOOOOOO", event.getDescription());
        assertEquals(Task.TaskType.EVENT, event.getTaskType());
        assertFalse(event.getTaskStatus());
    }

    @Test
    public void testChangeStatus() {
        assertFalse(todo.getTaskStatus());
        todo.changeStatus();
        assertTrue(todo.getTaskStatus());
        todo.changeStatus();
        assertFalse(todo.getTaskStatus());
    }

    @Test
    public void testGetStatusIcon() {
        assertEquals(" ", todo.getStatusIcon());
        todo.changeStatus();
        assertEquals("X", todo.getStatusIcon());
    }

    @Test
    public void testToString() {
        assertEquals("[ ] dance", todo.toString());
        todo.changeStatus();
        assertEquals("[X] dance", todo.toString());
    }
}
