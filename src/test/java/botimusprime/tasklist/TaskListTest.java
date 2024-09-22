package botimusprime.tasklist;

import botimusprime.BotimusPrime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the TaskList functionality within the BotimusPrime application.
 *
 * This class contains test cases that validate the behavior of adding tasks to the
 * task list and ensuring the correct state is maintained in response to various
 * inputs.
 */
public class TaskListTest {
    private static final String TEST_FILE_NAME = "test_tasks.txt";

    private BotimusPrime botimusPrime;
    private File testFile;

    /**
     * Sets up the test environment before each test.
     * Initializes a new BotimusPrime instance with a test file and
     * ensures any existing test files are deleted.
     */
    @BeforeEach
    public void setup() {
        botimusPrime = new BotimusPrime(TEST_FILE_NAME);

        testFile = new File(new File("./data"), TEST_FILE_NAME);

        if (testFile.exists()) {
            testFile.delete();
        }
    }

    /**
     * Cleans up the test environment after each test.
     * Deletes the test file if it exists.
     */
    @AfterEach
    public void reset() {
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    /**
     * Tests adding a ToDo item with no description.
     * Verifies that the task list remains empty when no valid
     * description is provided.
     */
    @Test
    public void testAddToDo_noDescriptionInput_emptyListReturned() {
        String noDescInput = "todo ";

        botimusPrime.getTaskList().addToDo(noDescInput);

        assertTrue(botimusPrime.getTaskList().getTasks().isEmpty());
    }

    @Test
    public void testAddToDo_ValidInput_nonEmptyListReturned() {
        String validInput = "todo buy banana";

        botimusPrime.getTaskList().addToDo(validInput);

        assertEquals(1, botimusPrime.getTaskList().getTasks().size());
        assertEquals("buy banana", botimusPrime.getTaskList().getTasks().get(0).getDescription());
        assertFalse(botimusPrime.getTaskList().getTasks().get(0).isDone());
    }
}
