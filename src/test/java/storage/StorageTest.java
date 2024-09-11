package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StorageTest {

    @Test
    public void saveTasksToFile_validData_fileSaved() throws IOException {
        Storage storage = new Storage("data/FridayTaskListTest.txt");
        List<Task> tasks = List.of(new ToDo("Task 1", false),
                new Deadline("Task 2 /by 2024-10-09", false),
                new Event("Task 3 /from 2024-10-09 /to 2024-10-10", true));
        storage.saveTasksToFile(tasks);

        File file = new File("data/FridayTaskListTest.txt");
        assertTrue(file.exists());

        List<String> fileContent = Files.readAllLines(Paths.get("data/FridayTaskListTest.txt"));
        assertEquals("T 1 Task 1", fileContent.get(0));
        assertEquals("D 1 Task 2 /by 2024-10-09", fileContent.get(1));
        assertEquals("E 0 Task 3 /from 2024-10-09 /to 2024-10-10", fileContent.get(2));

        // Clean up
        file.delete();
    }

    @Test
    public void loadTasksFromFile_existingFile_dataLoaded() throws IOException {
        File file = new File("data/FridayTaskListTest.txt");
        List<String> data = List.of("T 1 Task 1",
                "D 1 Task 2 /by 2024-10-09",
                "E 1 Task 3 /from 2024-10-09 /to 2024-10-10");
        Files.write(Paths.get("data/FridayTaskListTest.txt"), data);

        Storage storage = new Storage("data/FridayTaskListTest.txt");
        List<Task> loadedTasks = storage.loadTasksFromFile();

        assertEquals(3, loadedTasks.size());
        assertInstanceOf(ToDo.class, loadedTasks.get(0));
        assertInstanceOf(Deadline.class, loadedTasks.get(1));
        assertInstanceOf(Event.class, loadedTasks.get(2));

        // Clean up
        file.delete();
    }

    @Test
    public void createTaskFromInput_validInput_correctTask() {
        Storage storage = new Storage("data/FridayTaskListTest.txt");

        Task todo = storage.createTaskFromInput("T 0 Task 1");
        assertInstanceOf(ToDo.class, todo);
        assertEquals("Task 1", todo.getTaskName());

        Task deadline = storage.createTaskFromInput("D 0 Task 2 /by 2024-10-11");
        assertInstanceOf(Deadline.class, deadline);
        assertEquals("Task 2 ", deadline.getTaskName());

        Task event = storage.createTaskFromInput("E 0 Task 3 /from 2024-10-11 /to 2025-10-11");
        assertInstanceOf(Event.class, event);
        assertEquals("Task 3 ", event.getTaskName());
    }

    @Test
    public void isFileUncorrupted_validFile_trueReturned() throws IOException {
        File file = new File("data/FridayTaskListTest.txt");
        List<String> data = List.of("T 0 Task 1", "D 0 Task 2 /by 2024-11-30",
                "E 0 Task 3 /from 2024-11-09 /to 2025-11-09");
        Files.write(Paths.get("data/FridayTaskListTest.txt"), data);

        Storage storage = new Storage("data/FridayTaskListTest.txt");
        assertTrue(storage.isFileUncorrupted(file));

        // Clean up
        file.delete();
    }

    @Test
    public void isFileUncorrupted_invalidFile_falseReturned() throws IOException {
        File file = new File("data/FridayTaskListTest.txt");
        List<String> data = List.of("D 0 Task 2 /by 2024-11-40");
        Files.write(Paths.get("data/FridayTaskListTest.txt"), data);

        Storage storage = new Storage("data/FridayTaskListTest.txt");
        assertFalse(storage.isFileUncorrupted(file));

        // Clean up
        file.delete();
    }
}