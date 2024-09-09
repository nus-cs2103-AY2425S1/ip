package nuffle;

import nuffle.exception.NuffleException;
import nuffle.parser.Parser;
import nuffle.task.Deadline;
import nuffle.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class parserTest {
    @Test
    void parseReturnCorrectCommand() throws NuffleException {
        String userInput = "deadline submit homework /by 2024-09-15 2359";
        Task newTask = Parser.parseDeadlineCommand(userInput);

        // Then verify that the task is not null
        assertNotNull(newTask);

        // Verify that the task is an instance of Deadline
        assertTrue(newTask instanceof Deadline);

        // Cast to Deadline to verify specific properties
        Deadline deadlineTask = (Deadline) newTask;

        // Verify that the description and deadline are correct
        assertEquals("submit homework", deadlineTask.getDescription());
        assertEquals("Sep 15 2024, 11:59 pm", deadlineTask.getFormattedDeadline()); // Assuming getFormattedDeadline() returns the deadline in this format
    }
}
