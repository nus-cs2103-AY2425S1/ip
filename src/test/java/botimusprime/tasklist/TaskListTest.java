package botimusprime.tasklist;

import botimusprime.BotimusPrime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private static final String TEST_FILE_NAME = "test_tasks.txt";

    private BotimusPrime botimusPrime;
    private File testFile;

    @BeforeEach
    public void setUp() {
        botimusPrime = new BotimusPrime(TEST_FILE_NAME);

        testFile = new File(new File("./data"), TEST_FILE_NAME);

        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @AfterEach
    public void reset() {
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testAddToDo_noDescriptionInput() {
        String noDescInput = "todo ";

        botimusPrime.getTaskList().addToDo(noDescInput);

        assertTrue(botimusPrime.getTaskList().getTasks().isEmpty());
    }

    @Test
    public void testAddToDo_ValidInput() {
        String validInput = "todo buy banana";

        botimusPrime.getTaskList().addToDo(validInput);

        assertEquals(1, botimusPrime.getTaskList().getTasks().size());
        assertEquals("buy banana", botimusPrime.getTaskList().getTasks().get(0).getDescription());
        assertFalse(botimusPrime.getTaskList().getTasks().get(0).isDone());
    }
}
