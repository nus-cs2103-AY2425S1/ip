package commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ListCommandTest extends BaseCommandTest {
    private static final ListCommand LIST_COMMAND = new ListCommand("list");

    @Test
    public void execute_singleTask_correctResponse() {
        TodoCommand tc = new TodoCommand("todo borrow book");
        assertDoesNotThrow(() -> tc.execute(TASK_STORAGE, TEMP_STORAGE, TASKS));

        String expectedOutput = """
                Here is the task in your list:
                1. [T][ ] borrow book\s
                """;
        String rawOutput = LIST_COMMAND.execute(TASK_STORAGE, TEMP_STORAGE, TASKS);
        String processedOutput = this.removeQuirkyResponse(rawOutput);
        assertEquals(expectedOutput, processedOutput);
    }

    @Test
    public void execute_noTask_correctResponse() {
        String expectedOutput = """
                Here are the tasks in your list:
                No current tasks!
                """;
        String rawOutput = LIST_COMMAND.execute(TASK_STORAGE, TEMP_STORAGE, TASKS);
        String processedOutput = this.removeQuirkyResponse(rawOutput);
        assertEquals(expectedOutput, processedOutput);
    }
}
