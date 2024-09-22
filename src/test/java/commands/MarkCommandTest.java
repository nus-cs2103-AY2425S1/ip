package commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exceptions.BrockException;

public class MarkCommandTest extends BaseCommandTest {
    @Test
    public void execute_missingNumber_throwsException() {
        MarkCommand mc = new MarkCommand("mark");
        assertThrows(BrockException.class, () ->
                mc.execute(TASK_STORAGE, TEMP_STORAGE, TASKS));
    }

    @Test
    public void execute_invalidNumber_throwsException() {
        MarkCommand mc = new MarkCommand("mark 5");
        assertThrows(BrockException.class, () ->
                mc.execute(TASK_STORAGE, TEMP_STORAGE, TASKS));
    }

    @Test
    public void execute_unmarkedTask_marksTask() {
        TodoCommand tc = new TodoCommand("todo borrow book");
        assertDoesNotThrow(() -> tc.execute(TASK_STORAGE, TEMP_STORAGE, TASKS));

        String expectedOutput = """
                Nice! I've marked this task as done:
                  [T][X] borrow book\s
                """;

        // Local variable in function must be final / effectively final!
        // By making it an array, it satisfies this
        final String[] temp = new String[1];
        MarkCommand mc = new MarkCommand("mark 1");
        assertDoesNotThrow(() -> {
            String rawOutput = mc.execute(TASK_STORAGE, TEMP_STORAGE, TASKS);
            String processedOutput = this.removeQuirkyResponse(rawOutput);
            temp[0] = processedOutput;
        });
        assertEquals(expectedOutput, temp[0]);
    }
}
