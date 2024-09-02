package samson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import samson.task.Task;
import samson.task.ToDo;
import samson.task.Deadline;
import samson.task.Event;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertFalse;



public class StorageTest {

    private Storage storage;
    private String testFilePath = "./data/testfile.txt";

    @BeforeEach
    public void setUp() throws IOException {
        // Clean up the test file before each test
        File testFile = new File(testFilePath);
        if (testFile.exists()) {
            testFile.delete();
        }
        storage = new Storage(testFilePath);
    }

    @Test
    public void loadTaskFromFile_noTasks_success() throws IOException, SamException {
        List<Task> tasks = storage.loadTaskFromFile();
        assertEquals(0, tasks.size());
    }

    @Test
    public void loadTaskFromFile_allTasks_success() throws IOException, SamException {
        Task todo = new ToDo("Drink Water");
        Task deadline = new Deadline("Writing report", "2011-11-11 1234");
        Task event = new Event("Dinner Gathering", "2008-08-08 0808", "2010-08-09 1111");

        storage.addTaskToFile(todo);
        storage.addTaskToFile(deadline);
        storage.addTaskToFile(event);

        List<Task> tasks = storage.loadTaskFromFile();
        assertEquals(3, tasks.size());
        assertInstanceOf(ToDo.class, tasks.get(0));
        assertInstanceOf(Deadline.class, tasks.get(1));
        assertInstanceOf(Event.class, tasks.get(2));
    }
    @Test
    public void addTaskToFile_addSingleTask_success() throws IOException, SamException {
        ToDo todo = new ToDo("Write unit tests");
        storage.addTaskToFile(todo);

        List<Task> tasks = storage.loadTaskFromFile();
        assertEquals(1, tasks.size());
        assertEquals("Write unit tests", tasks.get(0).getDescription());
        assertInstanceOf(ToDo.class, tasks.get(0));
        assertFalse(tasks.get(0).isDone());
    }

    @Test
    public void saveTasksToFile_addMultipleTasks_success() throws IOException, SamException {
        ToDo todo = new ToDo("Write unit tests");
        Deadline deadline = new Deadline("Submit assignment", "2024-09-02 2359");
        Event event = new Event("Team meeting", "2024-09-02 1400", "2024-09-02 1600");

        storage.addTaskToFile(todo);
        storage.addTaskToFile(deadline);
        storage.addTaskToFile(event);

        List<Task> tasks = storage.loadTaskFromFile();
        assertEquals(3, tasks.size());

        // Verify the details of each task
        assertEquals("Write unit tests", tasks.get(0).getDescription());
        assertEquals("Submit assignment", tasks.get(1).getDescription());
        assertEquals("Team meeting", tasks.get(2).getDescription());
    }

    @Test
    public void saveTasksToFile_overwritesExistingData_success() throws IOException, SamException {
        ToDo todo1 = new ToDo("Task 1");
        storage.addTaskToFile(todo1);

        List<Task> tasksBefore = storage.loadTaskFromFile();
        assertEquals(1, tasksBefore.size());

        ToDo todo2 = new ToDo("Task 2");
        storage.saveTasksToFile(List.of(todo2));

        List<Task> tasksAfter = storage.loadTaskFromFile();
        assertEquals(1, tasksAfter.size());
        assertEquals("Task 2", tasksAfter.get(0).getDescription());
    }
}
