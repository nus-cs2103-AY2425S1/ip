package streams.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import streams.exception.StreamsException;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTask() throws StreamsException {
        Task task = new ToDoTask("Test task");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void testDeleteTask() throws StreamsException {
        Task task1 = new ToDoTask("Task 1");
        Task task2 = new ToDoTask("Task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);

        taskList.deleteTask(0);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.getTask(0));
    }

    @Test
    public void testDeleteInvalidTask() {
        assertThrows(StreamsException.class, () -> taskList.deleteTask(0));
    }

    @Test
    public void testGetInvalidTask() {
        assertThrows(StreamsException.class, () -> taskList.getTask(0));
    }
}