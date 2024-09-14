package duck.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests the Task class.
 */
class TaskTest {

    private static final String DESCRIPTION = "test";
    private static final String DONE_STATUS_ICON = "X";
    private static final String INCOMPLETE_STATUS_ICON = " ";
    private static final String EXPECTED_TO_STRING_INCOMPLETE_TASK = "[ ] " + DESCRIPTION;
    private static final String EXPECTED_FILE_FORMAT_INCOMPLETE_TASK = "0 | " + DESCRIPTION;
    private static final String EXPECTED_TO_STRING_COMPLETE_TASK = "[X] " + DESCRIPTION;
    private static final String EXPECTED_FILE_FORMAT_COMPLETE_TASK = "1 | " + DESCRIPTION;

    private Task task;

    private Task completeTask;
    private Task incompleteTask;


    /**
     * Set up the task for testing.
     */
    @BeforeEach
    void setUp() {

        // task that will be updated during test
        task = new Task(DESCRIPTION) {
        };


        // tasks that will not be updated during test
        completeTask = new Task(true, DESCRIPTION) {
        };
        incompleteTask = new Task(false, DESCRIPTION) {
        };
    }

    /**
     * Test if the description of the task is correct.
     */
    @Test
    public void getDescription() {
        assertEquals(DESCRIPTION, task.getDescription());
    }

    /**
     * Test if the status icon of the task is correct.
     */
    @Test
    public void getStatusIcon() {
        assertEquals(INCOMPLETE_STATUS_ICON, task.getStatusIcon());
    }

    /**
     * Test if the task is marked as done.
     */
    @Test
    public void markAsDone() {
        task.markAsDone();
        assertEquals(DONE_STATUS_ICON, task.getStatusIcon());
    }

    /**
     * Test if the task is marked as not done.
     */
    @Test
    public void markAsIncomplete() {
        task.markAsIncomplete();
        assertEquals(INCOMPLETE_STATUS_ICON, task.getStatusIcon());
    }

    /**
     * Test if the file format of the task is correct.
     */
    @Test
    public void toFileFormat() {
        assertEquals(EXPECTED_FILE_FORMAT_COMPLETE_TASK, completeTask.toFileFormat());
        assertEquals(EXPECTED_FILE_FORMAT_INCOMPLETE_TASK, incompleteTask.toFileFormat());
    }

    /**
     * Test if the string representation of the task is correct.
     */
    @Test
    public void testToString() {
        assertEquals(EXPECTED_TO_STRING_INCOMPLETE_TASK, incompleteTask.toString());
        assertEquals(EXPECTED_TO_STRING_COMPLETE_TASK, completeTask.toString());
    }
}
