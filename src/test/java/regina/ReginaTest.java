package regina;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import errorhandling.ReginaException;
import tasks.TaskList;

/**
 * The ReginaTest class contains unit tests for the Regina chatbot.
 * It uses JUnit to verify the functionality of the Regina class,
 * ensuring that task management operations work as expected.
 */
public class ReginaTest {
    private Regina regina;

    /**
     * Sets up the test environment before each test case.
     * Initializes a new instance of the Regina chatbot.
     */
    @BeforeEach
    public void setUp() {
        // Initialize regina.Regina before each test
        regina = new Regina();
    }

    /**
     * Tests the clearTaskList method of the Regina class.
     * This test checks whether the task list is empty after clearing it.
     *
     * @throws ReginaException If an error occurs while adding tasks.
     */
    @Test
    public void testClearTaskList() throws ReginaException {
        regina.add("todo Finish homework");
        regina.clearTaskList(); // Clear tasks
        assertEquals(0, regina.getListOfTasks().size()); // Ensure the task list is empty
    }

    /**
     * Tests the occurringOn method of the Regina class.
     * This test checks whether the correct tasks are returned that occur
     * on the specified date and time.
     *
     * @throws ReginaException If an error occurs while adding tasks or retrieving occurring tasks.
     */
    @Test
    public void testOccurringTasks() throws ReginaException {
        regina.clearTaskList();
        regina.add("todo Finish homework");
        regina.add("deadline Submit homework /by 27/8/2024 2359");
        regina.add("deadline Submit project /by 27/8/2024 1700"); // This will not be in the list
        regina.add("event project meeting /from 27/8/2024 1800 /to 27/8/2024 1900"); // This will not be in the list
        regina.add("event project meeting /from 27/8/2024 1800 /to 27/8/2024 2000");

        // Call the method being tested
        String testList = regina.occurringOn("27/8/2024 1930");

        // Prepare the expected output based on the interaction you provided
        String expectedOutput = """
                1. [T][ ] Finish homework
                2. [D][ ] Submit homework (by: Aug 27 2024, 11.59 pm)
                3. [E][ ] project meeting (from: Aug 27 2024, 6.00 pm to: Aug 27 2024, 8.00 pm)
                """;

        // Compare the output with the expected string from the chatbot interaction
        assertEquals(expectedOutput, testList.toString());
    }

    /**
     * Tests the functionality of finding tasks by a keyword in the Regina chatbot.
     * This method verifies that the find method correctly identifies tasks
     * containing the specified keyword and returns them in a TaskList.
     *
     * <p>This test case performs the following steps:
     * <ul>
     * <li>Clears the task list.</li>
     * <li>Adds several tasks, including a To-Do, two Deadlines, and two Events.</li>
     * <li>Calls the find method with a keyword that is expected to match some tasks.</li>
     * <li>Asserts that the returned TaskList contains only the tasks that have the keyword.</li>
     * </ul>
     *
     * @throws ReginaException If an error occurs while adding tasks to the Regina instance.
     */
    @Test
    public void testFindingTaskWithKeyword() throws ReginaException {
        regina.clearTaskList();
        regina.add("todo Finish homework");
        regina.add("deadline Submit homework /by 27/8/2024 2359");
        regina.add("deadline Submit project /by 27/8/2024 1700"); // This will not be in the list
        regina.add("event project meeting /from 27/8/2024 1800 /to 27/8/2024 1900"); // This will not be in the list
        regina.add("event project meeting /from 27/8/2024 1800 /to 27/8/2024 2000");

        // Call the method being tested
        TaskList testList = regina.find("homework");

        // Prepare the expected output based on the interaction you provided
        String expectedOutput = """
                1. [T][ ] Finish homework
                2. [D][ ] Submit homework (by: Aug 27 2024, 11.59 pm)
                """;

        // Compare the output with the expected string from the chatbot interaction
        assertEquals(expectedOutput, testList.toString());
    }
}
