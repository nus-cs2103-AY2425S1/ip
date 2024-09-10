package commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ListCommandTest extends BaseCommandTest {
    private static final ListCommand LIST_COMMAND = new ListCommand("list");

    @Test
    public void execute_singleTask_correctResponse() {
        TodoCommand tc = new TodoCommand("todo borrow book");
        assertDoesNotThrow(() -> tc.execute(STORAGE, TASKS));

        String expectedOutput = "Here is the task in your list:\n"
                + "1. [T][ ] borrow book ";
        String actualOutput = LIST_COMMAND.execute(STORAGE, TASKS);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void execute_noTask_correctResponse() {
        String expectedOutput = "Here are the tasks in your list:\n"
                + "No current tasks!";
        String actualOutput = LIST_COMMAND.execute(STORAGE, TASKS);
        assertEquals(expectedOutput, actualOutput);
    }
}
