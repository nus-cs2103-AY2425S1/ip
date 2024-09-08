package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    private final Path DATA_DIRECTORY = Paths.get("data");
    private final File TEST_FILE = new File(DATA_DIRECTORY.toFile(), "test.txt");
    private final PrintStream ORIGINAL_OUT = System.out;
    private final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws IOException {
        if (!Files.exists(DATA_DIRECTORY)) {
            Files.createDirectories(DATA_DIRECTORY);
        }
        if (!TEST_FILE.exists()) {
            TEST_FILE.createNewFile();
        }
        System.setOut(new PrintStream(OUT_CONTENT));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(ORIGINAL_OUT);
        if (TEST_FILE.exists()) {
            TEST_FILE.delete();
        }
    }

    @Test
    public void testMark_success() {
        TaskList taskList = new TaskList();
        taskList.getTaskList().add(new Todo("Test Task"));
        int taskIndex = 1;
        taskList.mark(taskIndex, new Storage("data/", "test.txt"));
        assertTrue(taskList.getTask(taskIndex - 1).isDone());
        String expectedOutput = "Nice! I've marked this task as done:" + System.lineSeparator() +
                taskList.getTask(taskIndex - 1).toString() + System.lineSeparator();
        assertEquals(expectedOutput, OUT_CONTENT.toString());
    }

}

