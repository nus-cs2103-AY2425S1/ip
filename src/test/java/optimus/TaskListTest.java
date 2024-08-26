package optimus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Asked ChatGPT to suggest kind of tests to do
// Let ChatGPT check and suggest comments and JavaDocs according to CS2103T style guide
/**
 * Test class for TaskList. Contains unit tests to verify the behavior
 * of the TaskList class, including adding tasks, marking tasks as done,
 * and handling exceptions for invalid input.
 */
public class TaskListTest {
    /**
     * Tests the addTask method to ensure tasks are correctly added
     * and their string representations are accurate.
     *
     * @throws OptimusException if task input format is invalid.
     */
    @Test
    public void addTaskTest() throws OptimusException {
        TaskList taskList = new TaskList();

        // Add todo task and verify
        taskList.addTask("todo return book");

        assertEquals(1, taskList.getTasks().size());
        assertEquals("[T][ ] return book", taskList.getTasks().get(0).toString());

        // Add deadline task and verify
        taskList.addTask("deadline return book /by 2024-10-02");

        assertEquals(2, taskList.getTasks().size());
        assertEquals("[D][ ] return book (by: Oct 2 2024)", taskList.getTasks().get(1).toString());

        // Add event task and verify
        taskList.addTask("event project meeting /from 2024-10-02 /to 2024-10-03");

        assertEquals(3, taskList.getTasks().size());
        assertEquals("[E][ ] project meeting (from: Oct 2 2024 to: Oct 3 2024)", taskList.getTasks().get(2).toString());
    }

    /**
     * Tests the addTask method to ensure an OptimusException is thrown
     * for tasks with invalid date formats.
     */
    @Test
    public void addTaskThrowsExceptionForInvalidDateFormatTest() {
        TaskList taskList = new TaskList();

        // Asked ChatGPT how to compare Exceptions
        // Test invalid date format for deadline
        Exception exception = assertThrows(OptimusException.class, () -> {
            taskList.addTask("deadline return book /by October");
        });

        assertEquals("Invalid date format for deadline. Please use yyyy-mm-dd.", exception.getMessage());

        // Test invalid date format for event
        Exception exception2 = assertThrows(OptimusException.class, () -> {
            taskList.addTask("event project meeting /from October /to December");
        });

        assertEquals("Invalid date format for Event. Please use yyyy-mm-dd.", exception2.getMessage());
    }

    /**
     * Tests the addTask method to ensure an OptimusException is thrown
     * when the description for a todo task is empty.
     */
    @Test
    public void addTaskThrowsExceptionForEmptyDescriptionTest() {
        TaskList taskList = new TaskList();

        // Test for empty description
        Exception exception = assertThrows(OptimusException.class, () -> {
            taskList.addTask("todo ");
        });

        assertEquals("The description of a todo cannot be empty >:(", exception.getMessage());
    }

    /**
     * Tests the markTaskAsDone method to ensure tasks can be correctly
     * marked as done.
     *
     * @throws OptimusException if an invalid task index is provided.
     */
    @Test
    public void markTaskAsDoneTest() throws OptimusException {
        TaskList taskList = new TaskList();

        // Add a task and verify it is initially not done
        taskList.addTask("todo return book");
        assertEquals(false, taskList.getTasks().get(0).isDone);

        // Mark task as done and verify
        taskList.markTaskAsDone(0);
        assertEquals(true, taskList.getTasks().get(0).isDone);
    }

    /**
     * Tests the markTaskAsDone method to ensure an OptimusException is thrown
     * for invalid task indices.
     */
    @Test
    public void markTaskAsDoneThrowsExceptionForInvalidIndexTest() throws OptimusException {
        TaskList taskList = new TaskList();

        // Add a task to the task list
        taskList.addTask("todo return book");

        // Test for out-of-bounds index
        Exception exception = assertThrows(OptimusException.class, () -> {
            taskList.markTaskAsDone(2);
        });
        assertEquals("Sorry, you only have up to task number 1.", exception.getMessage());
    }
}
