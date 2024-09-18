package james;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTask() throws MissingDescriptionException {
        Task task = new Todo("Read a book", false);
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void testDeleteTask() throws MissingDescriptionException {
        Task task1 = new Todo("Read a book", false);
        Task task2 = new Todo("Write a report", false);
        taskList.addTask(task1);
        taskList.addTask(task2);

        taskList.deleteTask(0);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.getTask(0));
    }

    @Test
    public void testGetTask() throws MissingDescriptionException {
        Task task = new Todo("Read a book", false);
        taskList.addTask(task);
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void testSize() throws MissingDescriptionException {
        assertEquals(0, taskList.size());
        taskList.addTask(new Todo("Read a book", false));
        assertEquals(1, taskList.size());
    }

    @Test
    public void testDeleteTaskWithInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(0));
    }

    @Test
    public void testGetTaskWithInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(0));
    }
}
