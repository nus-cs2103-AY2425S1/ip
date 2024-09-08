package commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ListCommandTest extends BaseCommandTest {
    private static final ListCommand LIST_COMMAND = new ListCommand("list");

    @Test
    public void execute_singleTask_correctResponse() {
        TodoCommand tc = new TodoCommand("todo borrow book");
        assertDoesNotThrow(() -> tc.execute(UI, STORAGE, TASKS));
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
    public void execute_noTask_correctResponse() {
        String expectedResponse = "Here are the tasks in your list:\n"
                + "No current tasks!";
        UI.displayResponse(expectedResponse);
        String expectedOutput = super.getOutput();

        LIST_COMMAND.execute(UI, STORAGE, TASKS);
        String actualOutput = super.getOutput();

        assertEquals(expectedOutput, actualOutput);
    }
}
