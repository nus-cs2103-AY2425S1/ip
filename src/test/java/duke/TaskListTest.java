package duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    private final Path dataDirectory = Paths.get("data");
    private final File testFile = new File(dataDirectory.toFile(), "test.txt");
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws IOException {
        if (!Files.exists(dataDirectory)) {
            Files.createDirectories(dataDirectory);
        }
        if (!testFile.exists()) {
            testFile.createNewFile();
        }
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        if (testFile.exists()) {
            testFile.delete();
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
        assertEquals(expectedOutput, outContent.toString());
    }

}

