package taskon.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskon.task.Deadline;
import taskon.task.Task;
import taskon.task.TaskList;
import taskon.task.Todo;

public class StorageTest {

    private Storage storage;
    private TaskList tasks;
    private File tempFile;

    @BeforeEach
    public void setUp() throws Exception {
        tempFile = File.createTempFile("taskon", ".txt");
        storage = new Storage(tempFile.getAbsolutePath());
        tasks = new TaskList();
    }

    @AfterEach
    public void tearDown() {
        // Delete the temporary file
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    public void testSaveTasks() throws Exception {
        tasks.addTask(new Todo("Test todo"));
        tasks.addTask(new Deadline("Test deadline", "2024-09-30 1400"));

        storage.saveTasks(tasks);

        // Read the file and check its content
        String fileContent = Files.readString(Path.of(tempFile.getAbsolutePath()));
        String expectedContent = "T|0|Test todo\nD|0|Test deadline|2024-09-30 1400\n";

        String normalizedFileContent = fileContent.replace("\r\n", "\n").trim();
        String normalizedExpectedContent = expectedContent.replace("\r\n", "\n").trim();

        assertEquals(normalizedExpectedContent, normalizedFileContent);
    }

    @Test
    public void testLoadTasks() throws Exception {
        // Write sample task data to the file
        FileWriter fw = new FileWriter(tempFile);
        fw.write("T|1|Test todo\n");
        fw.write("D|0|Test deadline|2024-09-30 1400\n");
        fw.close();

        // Load tasks from the file
        ArrayList<Task> loadedTasks = storage.load();

        // Verify loaded tasks
        assertEquals(2, loadedTasks.size());
        assertEquals("Test todo", loadedTasks.get(0).getDescription());
        assertEquals("Test deadline", loadedTasks.get(1).getDescription());
    }

    @Test
    public void testParseTodoTask() throws Exception {
        String todoLine = "T|0|Test todo";
        Task parsedTask = Storage.parseTask(todoLine);

        assertEquals("Test todo", parsedTask.getDescription());
        assertEquals(false, parsedTask.getIsDone());
    }

    @Test
    public void testParseDeadlineTask() throws Exception {
        String deadlineLine = "D|1|Test deadline|2024-09-30 1400";
        Task parsedTask = Storage.parseTask(deadlineLine);

        assertEquals("Test deadline", parsedTask.getDescription());
        assertEquals(true, parsedTask.getIsDone());
    }

    @Test
    public void testSaveEmptyTaskList() throws Exception {
        TaskList emptyTaskList = new TaskList();
        File tempFile = File.createTempFile("taskon_empty", ".txt");
        Storage storage = new Storage(tempFile.getAbsolutePath());

        storage.saveTasks(emptyTaskList);

        String fileContent = Files.readString(Path.of(tempFile.getAbsolutePath()));
        assertTrue(fileContent.isEmpty());
    }
}
