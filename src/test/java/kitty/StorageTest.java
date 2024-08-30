package kitty;

import kitty.tasks.Task;
import kitty.tasks.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    private Storage storage;
    private TaskList taskList;
    private static final String KITTY_DATA_PATH = "data/Kitty.txt";
    private static final String BACKUP_PATH = "data/Kitty_backup.txt";

    @BeforeEach
    void setUp() throws IOException {
        // Backup the original file
        File originalFile = new File(KITTY_DATA_PATH);

        storage = new Storage();
        taskList = new TaskList();
    }

    @AfterEach
    void tearDown() throws IOException {
        // Restore the original file
        File originalFile = new File(KITTY_DATA_PATH);
    }


    @Test
    void testAddContent() throws IOException {
        storage.addContent("Test content");

        File testFile = new File(KITTY_DATA_PATH);
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

        File testFile = new File(KITTY_DATA_PATH);
        Scanner scanner = new Scanner(testFile);
        String content = scanner.nextLine();
        assertTrue(content.contains("New content"), "Content should be rewritten with the new text");
    }

    @Test
    void testInitializeTaskList() throws IOException {
        FileWriter writer = new FileWriter(KITTY_DATA_PATH);
        writer.write("T~!!0~!!Test task");
        writer.close();

        storage.initializeTaskList(taskList);

        assertEquals(1, taskList.getTasks().size(), "Task list should have one task after initialization");
        Task task = taskList.getTasks().get(0);
        assertEquals("T ~!! 0 ~!! Test task", task.getTaskData().trim(), "The task data should match the input content");
    }
}
