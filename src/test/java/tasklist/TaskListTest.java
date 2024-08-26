package tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import storage.FileStorage;
import storage.Storage;
import tasks.Deadline;
import tasks.DeadlineException;
import tasks.Event;
import tasks.EventException;
import tasks.ToDo;



class TaskListTest {

    private TaskList taskList;
    private Storage storage;

    @BeforeEach
    void setUp() {
        // Create a FileStorage object
        storage = new FileStorage("test_data");

        // Clear storage
        storage.update(new ArrayList<String>());

        // Initialize TaskList with the Storage
        taskList = new TaskList(storage);
    }

    @AfterEach
    void tearDown() {
        storage.update(new ArrayList<String>());
    }

    @Test
    void testConstructorLoadsTasksFromStorage() {
        // Prepare mock data
        List<String> mockData = new ArrayList<>();
        mockData.add("todo | 0 | Buy groceries");
        mockData.add("event | 1 | Team meeting | 2024-09-01 | 2024-09-01");
        mockData.add("deadline | 0 | Submit report | 2024-10-15");

        storage.update(mockData);

        // Reinitialize TaskList to load the mock data
        taskList = new TaskList(storage);

        assertEquals(3, taskList.getTasks().size());
        assertTrue(taskList.getTasks().get(0) instanceof ToDo);
        assertTrue(taskList.getTasks().get(1) instanceof Event);
        assertTrue(taskList.getTasks().get(2) instanceof Deadline);
    }

    @Test
    void testAddToDo() {
        taskList.addTask(new ToDo("Read a book"));
        assertEquals(1, taskList.getTasks().size());
        assertTrue(taskList.getTasks().get(0) instanceof ToDo);
    }

    @Test
    void testAddDeadline() {
        try {
            taskList.addTask(new Deadline("Submit assignment", "2024-12-01"));
            assertEquals(1, taskList.getTasks().size());
            assertTrue(taskList.getTasks().get(0) instanceof Deadline);
        } catch (DeadlineException e) {
            fail();
        }

    }

    @Test
    void testAddEvent() {
        try {
            taskList.addTask(new Event("Company retreat", "2024-09-05", "2024-09-07"));
            assertEquals(1, taskList.getTasks().size());
            assertTrue(taskList.getTasks().get(0) instanceof Event);
        } catch (EventException e) {
            fail();
        }

    }

    @Test
    void testMarkTask() {
        taskList.addTask(new ToDo("Read a book"));
        taskList.mark(0);
        assertEquals(
                taskList.getTask(0).toDataFormat().split(" \\| ")[1],
                "1");
    }

    @Test
    void testUnmarkTask() {
        taskList.addTask(new ToDo("Read a book"));
        taskList.mark(0);
        taskList.unmark(0);
        assertEquals(
                taskList.getTask(0).toDataFormat().split(" \\| ")[1],
                "0");
    }

    @Test
    void testSize() {
        taskList.addTask(new ToDo("Read a book"));
        taskList.addTask(new ToDo("Read a magazine"));

        assertEquals(taskList.size(), 2);

    }
}
