package CancelGPT.core;

import CancelGPT.core.CancelGPT;
import CancelGPT.exception.task.TaskDoesNotExist;
import CancelGPT.task.Task;
import CancelGPT.task.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class CancelGPTTest {
    private static final Path TEST_STORAGE_DIR_PATH = Paths.
            get(System.getProperty("user.home"), "accountexeregister-ip-test", "data");

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
        CancelGPT cancelGPT = new CancelGPT(TEST_STORAGE_DIR_PATH);
        assertEquals("CancelGPT", cancelGPT.getName());
    }

    @Test
    public void task_addTask_success() {
        CancelGPT cancelGPT = new CancelGPT(TEST_STORAGE_DIR_PATH);
        assertTrue(cancelGPT.getTasks().isEmpty());
        Task testTask = new ToDo("test description");
        cancelGPT.addToTaskList(testTask);
        assertEquals(1, cancelGPT.getTasks().size());
        assertTrue(cancelGPT.getTasks().contains(testTask));
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

