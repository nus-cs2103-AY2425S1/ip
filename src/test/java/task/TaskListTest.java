package task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTask() {
        Todo todo = new Todo("Test task");
        taskList.add(todo);
        assertEquals(1, taskList.getTasks().size());
        assertEquals("Test task", taskList.getTasks().get(0).getName());
    }

    @Test
    public void testDeleteTask() {
        Todo todo = new Todo("Test task");
        taskList.add(todo);
        taskList.delete(0);
        assertEquals(0, taskList.getTasks().size());
    }

    @Test
    public void testMarkTask() {
        Todo todo = new Todo("Test task");
        taskList.add(todo);
        taskList.mark(0);
        assertTrue(taskList.getTasks().get(0).isDone());
    }

    @Test
    public void testUnmarkTask() {
        Todo todo = new Todo("Test task");
        taskList.add(todo);
        taskList.mark(0);
        taskList.unmark(0);
        assertFalse(taskList.getTasks().get(0).isDone());
    }
}
