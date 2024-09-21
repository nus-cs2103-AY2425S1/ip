package twilight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddTaskCommandTest {

    @Test
    public void execute_valid_success() throws InvalidInputException {
        assertEquals("added: [T][ ] read book\nThere are 1 tasks in the list.",
                new AddTaskCommand(3, "read book").execute(new TaskList(), new Storage("./data/TwilightTest1.txt")));
        assertEquals("added: [E][ ] read book from: 0 to: 1\nThere are 1 tasks in the list.",
                new AddTaskCommand(4, "read book /from 0 /to 1").execute(new TaskList(), new Storage("./data/TwilightTest2.txt")));
        assertEquals("added: [D][ ] read book by: Jan 1 2024\nThere are 1 tasks in the list.",
                new AddTaskCommand(5, "read book /by 2024-01-01").execute(new TaskList(), new Storage("./data/TwilightTest3.txt")));
    }

    @Test
    public void execute_misInput_exceptionThrown() throws InvalidInputException {
        try {
            new AddTaskCommand(4, "read book/from 0 /to 1").execute(new TaskList(), new Storage("./data/TwilightTest2.txt"));
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid Input:\nEvents must have the format:\nevent EventName /from startTime /to endTime", e.toString());
        }
        try {
            new AddTaskCommand(5, "read book /by2024-01-01").execute(new TaskList(), new Storage("./data/TwilightTest3.txt"));
        } catch (InvalidInputException e) {
            assertEquals("Invalid Input:\nDeadlines must have the format:\ndeadline deadlineName /by YYYY-MM-DD", e.toString());
        }
    }

    @Test
    public void execute_savingIssues_errorMessage() throws InvalidInputException {
        assertEquals("error saving data after your last input",
                new AddTaskCommand(3, "read book").execute(new TaskList(), new Storage("./data")));
    }
}

