package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTask() {
        Task task = new ToDo("Test task");
        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    @Test
    public void testGetTask() {
        Task task = new ToDo("Test task");
        taskList.add(task);
        assertEquals(task, taskList.get(0));
    }

    @Test
    public void testRemoveTask() {
        Task task = new ToDo("Test task");
        taskList.add(task);
        Task removedTask = taskList.remove(0);
        assertEquals(0, taskList.size());
        assertEquals(task, removedTask);
    }

    @Test
    public void testSize() {
        assertEquals(0, taskList.size());
        taskList.add(new ToDo("Test task"));
        assertEquals(1, taskList.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(taskList.isEmpty());
        taskList.add(new ToDo("Test task"));
        assertFalse(taskList.isEmpty());
    }
}
