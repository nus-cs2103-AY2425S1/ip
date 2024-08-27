package regina;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import errorhandling.ReginaException;
import tasks.TaskList;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReginaTest {
    private Regina regina;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        // Initialize regina.Regina before each test
        regina = new Regina();
    }

    @Test
    public void testClearTaskList() throws ReginaException {
        regina.add("todo Finish homework");
        regina.clearTaskList(); // Clear tasks
        assertEquals(0, regina.getListOfTasks().size()); // Ensure the task list is empty
    }

    @Test
    public void testOccurringTasks() throws ReginaException {
        regina.clearTaskList();
        regina.add("todo Finish homework");
        regina.add("deadline Submit homework /by 27/8/2024 2359");
        regina.add("deadline Submit project /by 27/8/2024 1700"); // This will not be in the list
        regina.add("event project meeting /from 27/8/2024 1800 /to 27/8/2024 1900"); // This will not be in the list
        regina.add("event project meeting /from 27/8/2024 1800 /to 27/8/2024 2000");

        // Call the method being tested
        TaskList testList = regina.occurringOn("27/8/2024 1930");

        // Prepare the expected output based on the interaction you provided
        String expectedOutput = "1. [T][ ] Finish homework\n"
                + "2. [D][ ] Submit homework (by: Aug 27 2024, 11.59 pm)\n"
                + "3. [E][ ] project meeting (from: Aug 27 2024, 6.00 pm to: Aug 27 2024, 8.00 pm)\n";

        // Compare the output with the expected string from the chatbot interaction
        assertEquals(expectedOutput, testList.toString());
    }
}
