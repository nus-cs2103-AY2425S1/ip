package stobberi;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stobberi.components.Storage;
import stobberi.stobberiexception.StobberiException;
import stobberi.task.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StobberiTest {

    private Stobberi stobberi;
    private Storage storage;
    private File testFile;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary file for testing
        testFile = File.createTempFile("testTasks", ".txt");
        testFile.deleteOnExit();

        // Initialize Storage and Stobberi with the temporary file
        storage = new Storage(testFile.getPath());
        stobberi = new Stobberi();
    }

    @AfterEach
    public void tearDown() {
        // Clean up after tests
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testInitialization() {
        // Verify that the Stobberi instance initializes with an empty task list
        ArrayList<Task> tasks = storage.getList();
        assertTrue(tasks.isEmpty(), "Task list should be empty upon initialization.");
    }

    @Test
    public void testGetResponse_commandExecution() throws StobberiException {
        // Prepare a sample command
        String input = "todo Test task"; // Assuming this command creates a Todo task

        // Execute the command and get the response
        String response = stobberi.getResponse(input);

        // Verify the response
        assertNotNull(response);
        assertTrue(response.contains("Yayyy! I've added a new task:"));
    }

    @Test
    public void testGetResponse_invalidCommand() {
        // Prepare an invalid command
        String input = "invalid command";

        // Execute the command and get the response
        String response = stobberi.getResponse(input);

        // Verify the response
        assertNotNull(response);
        assertTrue(response.contains("I just don't know how to do that yettt."), "Response should indicate an invalid command.");
    }
}
