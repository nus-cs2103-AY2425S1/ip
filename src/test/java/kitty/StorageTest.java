package kitty;

import kitty.tasks.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class StorageTest {

    private static final String TEST_DATA_PATH = "data/Test.txt";
    private static final String TEST_DATA_NAME = "Test.txt";
    private Storage storage;
    private TaskList taskList;


    @BeforeEach
    void setUp() {
        // Initialize the storage and task list for testing
        storage = new Storage(TEST_DATA_NAME);
        taskList = new TaskList();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void testAddContent() throws IOException {
        storage.addContent("Test content");

        File testFile = new File(TEST_DATA_PATH);
        assertTrue(testFile.exists(), "File should exist after content is added");

        Scanner scanner = new Scanner(testFile);
        String content = "";
        while (scanner.hasNextLine()) {
            content = scanner.nextLine();
        }
        assertTrue(content.contains("Test content"), "Content should match the last written text");
    }

    @Test
    void testRewriteFile() throws IOException {
        storage.addContent("Old content");
        storage.rewriteFile("New content");

        File testFile = new File(TEST_DATA_PATH);
        Scanner scanner = new Scanner(testFile);
        String content = scanner.nextLine();
        assertTrue(content.contains("New content"), "Content should be rewritten with the new text");
    }

    @Test
    void testInitializeTaskList() throws IOException {
        FileWriter writer = new FileWriter(TEST_DATA_PATH);
        writer.write("T~!!0~!!Test task");
        writer.close();

        storage.initializeTaskList(taskList);

        assertEquals(1, taskList.getTasks().size(),
                "Task list should have one task after initialization");
        Task task = taskList.getTasks().get(0);
        assertEquals("T ~!! 0 ~!! Test task", task.getTaskData().trim(),
                "The task data should match the input content");
    }
}
