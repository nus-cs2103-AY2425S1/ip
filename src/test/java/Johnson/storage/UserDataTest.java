package Johnson.storage;

import Johnson.task.Deadline;
import Johnson.task.Event;
import Johnson.task.TaskList;
import Johnson.task.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class UserDataTest {

    private static final String TEST_DIRECTORY = "data/testSaveFile.txt";
    private UserData userData;

    @BeforeEach
    void setUp() {
        userData = new UserData(TEST_DIRECTORY);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_DIRECTORY));
    }

    @Test
    void testSaveAndLoadTasks() {
        TaskList tasks = getTaskList();

        userData.setTasks(tasks);
        try {
            userData.saveTasks();
        } catch (FileNotFoundException e) {
            fail("Test file not found");
        }

        UserData loadedUserData = new UserData(TEST_DIRECTORY);
        TaskList loadedTasks = loadedUserData.getTasks();

        assertEquals(3, loadedTasks.taskCount());
        assertEquals("Test ToDo", loadedTasks.getTask(0).getTaskName());
        assertTrue(loadedTasks.getTask(0).getTaskStatus());

        assertEquals("Test Deadline", loadedTasks.getTask(1).getTaskName());
        assertFalse(loadedTasks.getTask(1).getTaskStatus());
        assertEquals("2024-12-12", ((Deadline) loadedTasks.getTask(1)).getDate().toString());
        assertEquals("12:00", ((Deadline) loadedTasks.getTask(1)).getTime().toString());

        assertEquals("Test Event", loadedTasks.getTask(2).getTaskName());
        assertTrue(loadedTasks.getTask(2).getTaskStatus());
        assertEquals("2024-12-12", ((Event) loadedTasks.getTask(2)).getDate().toString());
        assertEquals("18:00", ((Event) loadedTasks.getTask(2)).getTime().toString());
    }

    @Test
    void testLoadTasksFromNonExistentFile() {
        UserData userData = new UserData("data/nonExistentFile.txt");
        TaskList tasks = userData.getTasks();
        assertNotNull(tasks);
        assertEquals(0, tasks.taskCount());
    }

    @Test
    void testInvalidDirectory() {
        UserData userData = new UserData("invalid/directory/path");
        assertEquals("data/testSaveFile.txt", userData.getSaveFileDirectory());
    }

    private static TaskList getTaskList() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo();
        todo.setTaskName("Test ToDo");
        todo.setTaskStatus(true);
        tasks.addTask(todo);

        Deadline deadline = new Deadline();
        deadline.setTaskName("Test Deadline");
        deadline.setTaskStatus(false);
        deadline.setDate("2024-12-12");
        deadline.setTime("12:00");
        tasks.addTask(deadline);

        Event event = new Event();
        event.setTaskName("Test Event");
        event.setTaskStatus(true);
        event.setDate("2024-12-12");
        event.setTime("18:00");
        tasks.addTask(event);
        return tasks;
    }
}