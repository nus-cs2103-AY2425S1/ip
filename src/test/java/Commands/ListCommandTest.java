package Commands;

import Exceptions.BrockException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest extends BaseCommandTest {
    private static final ListCommand LIST_COMMAND = new ListCommand("list");

    @Test
    public void execute_singleTask_correctResponse() throws BrockException {
        TodoCommand tc = new TodoCommand("todo borrow book");
        tc.execute(UI, STORAGE, TASKS);
        NEW_OUT.reset();

        String expectedResponse = "Here is the task in your list:\n"
                + "1. [T][ ] borrow book ";
        UI.displayResponse(expectedResponse);
        String expectedOutput = super.getOutput();

        LIST_COMMAND.execute(UI, STORAGE, TASKS);
        String actualOutput = super.getOutput();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void execute_noTask_correctResponse() throws BrockException {
        String expectedResponse = "Here are the tasks in your list:\n"
                + "No current tasks!";
        UI.displayResponse(expectedResponse);
        String expectedOutput = super.getOutput();

        LIST_COMMAND.execute(UI,STORAGE,TASKS);
        String actualOutput = super.getOutput();

        assertEquals(expectedOutput, actualOutput);
    }
}
