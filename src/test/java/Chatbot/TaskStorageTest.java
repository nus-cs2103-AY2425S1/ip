package Chatbot;

import Tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TaskStorageTest {

    private HarddiskStorage mockHarddiskStorage;
    private TaskStorage taskStorage;

    @BeforeEach
    public void setUp() {
        mockHarddiskStorage = Mockito.mock(HarddiskStorage.class);
        taskStorage = new TaskStorage(mockHarddiskStorage);
    }

    @Test
    public void testAddTask() {
        Task mockTask = Mockito.mock(Task.class);

        taskStorage.addTask(mockTask);

        // Verify that the task was saved
        verify(mockHarddiskStorage, times(1)).save(any(Map.class));
        assertEquals(1, taskStorage.getTaskCount());
        assertEquals(mockTask, taskStorage.getTask(1));
    }

    @Test
    public void testMarkTask() {
        Task mockTask = Mockito.mock(Task.class);
        taskStorage.addTask(mockTask);

        // Mark the task as done
        taskStorage.markTask(1);

        // Verify that the task was marked as done and saved
        verify(mockTask, times(1)).markDone();
        verify(mockHarddiskStorage, times(2)).save(any(Map.class)); // One for addTask, one for markTask
    }

    @Test
    public void testMarkTaskInvalidId() {
        taskStorage.markTask(99);

        // Verify that no save is performed since the ID is invalid
        verify(mockHarddiskStorage, times(0)).save(any(Map.class));
    }

    @Test
    public void testGetTask() {
        Task mockTask = Mockito.mock(Task.class);
        taskStorage.addTask(mockTask);

        Task retrievedTask = taskStorage.getTask(1);
        assertEquals(mockTask, retrievedTask);
    }

    @Test
    public void testDeleteTask() {
        Task mockTask = Mockito.mock(Task.class);
        taskStorage.addTask(mockTask);

        // Verify task count before deletion
        assertEquals(1, taskStorage.getTaskCount());

        // Delete the task
        taskStorage.deleteTask(1);

        // Verify that the task was deleted and saved
        verify(mockHarddiskStorage, times(2)).save(any(Map.class)); // One for addTask, one for deleteTask
        assertEquals(0, taskStorage.getTaskCount());
    }

    @Test
    public void testTaskStorageInitializationWithLoadedTasks() {
        // Mock loading tasks from hard disk
        Map<Integer, Task> mockLoadedTasks = new HashMap<>();
        Task mockTask = Mockito.mock(Task.class);
        mockLoadedTasks.put(1, mockTask);

        when(mockHarddiskStorage.load()).thenReturn(mockLoadedTasks);

        // Initialize TaskStorage with loaded tasks
        taskStorage = new TaskStorage(mockHarddiskStorage);

        // Verify that tasks were loaded correctly
        assertEquals(1, taskStorage.getTaskCount());
        assertEquals(mockTask, taskStorage.getTask(1));
    }
}
