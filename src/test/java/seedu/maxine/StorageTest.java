package seedu.maxine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.maxine.task.Task;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture the output
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testRefreshStorage_givesError() {
        // Using a file path that does not exist
        Storage storage = new Storage("storage.txt");
        ArrayList<Task> list = new ArrayList<>();
        TaskListStub taskList = new TaskListStub(list);
        taskList.addTodo(new String[]{"T", "0", "homework"});
        storage.refreshStorage(taskList);
        String expectedOutput = "";
        String actualOutput = outputStream.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testRefreshStorage() {
        Storage storage = new Storage("data/maxine.txt");

        // Getting tasklist ready
        ArrayList<Task> list = new ArrayList<>();
        TaskListStub taskList = new TaskListStub(list);

        // Adding task to list
        taskList.addTodo(new String[]{"T", "0", "homework"});
        storage.refreshStorage(taskList);
        String expectedOutput = "";
        String actualOutput = outputStream.toString();
        assertEquals(expectedOutput, actualOutput);
    }
}

