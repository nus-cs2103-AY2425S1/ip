package thanos.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import thanos.storage.IStorage;
import thanos.stubs.StorageStub;

public class TaskListTest {
    private TaskList taskList;
    private IStorage storageStub;

    @BeforeEach
    public void setUp() {
        storageStub = new StorageStub();
        taskList = new TaskList(storageStub);
    }

    @Test
    public void testSize() {
        assertEquals(0, taskList.size());
    }

    @Test
    public void testAdd() {
        Task task = new Todo("sample task");
        taskList.add(task);

        assertEquals(1, taskList.size());
        assertTrue(taskList.getTaskList().contains(task));
    }

    @Test
    public void testRemove() {
        Task task = new Todo("Sample Task");
        taskList.add(task);
        taskList.remove(0);

        assertEquals(0, taskList.size());
        assertFalse(taskList.getTaskList().contains(task));
    }

    @Test
    public void testMark() {
        Task task = new Todo("sample task");
        taskList.add(task);
        taskList.mark(0);

        String expectedOutput = "[T][X] sample task";
        assertEquals(expectedOutput, task.toString());
    }

    @Test
    public void testUnmark() {
        Task task = new Todo("sample task");
        task.setDone(true);
        taskList.add(task);
        taskList.unmark(0);

        String expectedOutput = "[T][ ] sample task";
        assertEquals(expectedOutput, task.toString());
    }

    @Test
    public void testFindByDate() {
        LocalDateTime date = LocalDateTime.of(2024, 8, 26, 0, 0);
        Task task1 = new Deadline("Task 1", date);
        taskList.add(task1);
        ArrayList<Task> result = taskList.findByDate(date);

        assertEquals(1, result.size());
        assertTrue(result.contains(task1));
    }
}
