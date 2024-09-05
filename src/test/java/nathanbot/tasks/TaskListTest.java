import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nathanbot.storage.Storage;
import nathanbot.tasks.Task;
import nathanbot.tasks.TaskListStore;

public class TaskListTest {
    // built with Copilot's assistance
    private Storage storage;
    private TaskListStore taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        storage = mock(Storage.class);
        task1 = mock(Task.class);
        task2 = mock(Task.class);

        when(storage.loadTasksFromFile()).thenReturn(new ArrayList<>(Arrays.asList(task1, task2)));

        taskList = new TaskListStore(storage);
    }

    @Test
    public void testConstructorLoadsTasks() {
        assertEquals(2, taskList.listLength());
    }

    @Test
    public void testAddTask() {
        Task newTask = mock(Task.class);
        taskList.addTask(newTask);

        assertEquals(3, taskList.listLength());
    }

    @Test
    public void testToString() {
        when(task1.toString()).thenReturn("Task 1");
        when(task2.toString()).thenReturn("Task 2");

        String expected = "1. Task 1\n2. Task 2\n";
        assertEquals(expected, taskList.toString());
    }
}
