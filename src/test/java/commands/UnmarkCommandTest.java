package commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exceptions.BrockException;

public class UnmarkCommandTest extends BaseCommandTest {
    @Test
    public void execute_missingNumber_throwsException() {
        UnmarkCommand uc = new UnmarkCommand("unmark");
        assertThrows(BrockException.class, () ->
                uc.execute(TASK_STORAGE, TEMP_STORAGE, TASKS));
    }

    @Test
    public void execute_invalidNumber_throwsException() {
        UnmarkCommand uc = new UnmarkCommand("unmark 5");
        assertThrows(BrockException.class, () ->
                uc.execute(TASK_STORAGE, TEMP_STORAGE, TASKS));
    }

    @Test
    public void execute_markedTask_unmarksTask() {
        TodoCommand tc = new TodoCommand("todo borrow book");
        assertDoesNotThrow(() -> {
            tc.execute(TASK_STORAGE, TEMP_STORAGE, TASKS);
            MarkCommand mc = new MarkCommand("mark 1");
            mc.execute(TASK_STORAGE, TEMP_STORAGE, TASKS);
        });

        String expectedOutput = """
                OK, I've marked this task as not done yet:
                  [T][ ] borrow book\s
                """;

        // Local variable in function must be final / effectively final!
        // By making it an array, it satisfies this
        final String[] temp = new String[1];
        UnmarkCommand uc = new UnmarkCommand("unmark 1");
        assertDoesNotThrow(() -> {
            String rawOutput = uc.execute(TASK_STORAGE, TEMP_STORAGE, TASKS);
            String processedOutput = this.removeQuirkyResponse(rawOutput);
            temp[0] = processedOutput;
        });
        assertEquals(expectedOutput, temp[0]);
    }
}
