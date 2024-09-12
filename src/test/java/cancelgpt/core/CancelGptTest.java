package cancelgpt.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cancelgpt.task.Task;
import cancelgpt.task.ToDo;

public class CancelGptTest {
    private static final Path TEST_STORAGE_DIR_PATH = Paths
        .get(System.getProperty("user.home"), "accountexeregister-ip-test", "data");

    @BeforeEach
    public void setUp() {
        this.clearStorage();
    }

    @AfterEach
    public void tearDown() {
        this.clearStorage();
    }

    @Test
    public void name_getNameShould_returnCorrectName() {
        CancelGpt cancelGpt = new CancelGpt(TEST_STORAGE_DIR_PATH);
        assertEquals("cancelgpt", cancelGpt.getName());
    }

    @Test
    public void task_addTask_success() {
        CancelGpt cancelGpt = new CancelGpt(TEST_STORAGE_DIR_PATH);
        assertTrue(cancelGpt.getTasks().isEmpty());
        Task testTask = new ToDo("test description");
        cancelGpt.addToTaskList(testTask);
        assertEquals(1, cancelGpt.getTasks().size());
        assertTrue(cancelGpt.getTasks().contains(testTask));
    }

    private void clearStorage() {
        // Check if the directory exists
        if (Files.exists(TEST_STORAGE_DIR_PATH) && Files.isDirectory(TEST_STORAGE_DIR_PATH)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(TEST_STORAGE_DIR_PATH)) {
                // Iterate through the files in the directory and delete them
                for (Path path : directoryStream) {
                    if (Files.isRegularFile(path)) {
                        Files.delete(path);
                    }
                }
                System.out.println("All files in the directory have been deleted.");
            } catch (IOException e) {
                System.err.println("An error occurred while deleting files: " + e.getMessage());
            }
        } else {
            System.out.println("Directory does not exist or is not a directory.");
        }
    }
}

