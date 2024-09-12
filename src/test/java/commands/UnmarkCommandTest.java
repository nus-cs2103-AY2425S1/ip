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
                uc.execute(STORAGE, TASKS));
    }

    @Test
    public void execute_invalidNumber_throwsException() {
        UnmarkCommand uc = new UnmarkCommand("unmark 5");
        assertThrows(BrockException.class, () ->
                uc.execute(STORAGE, TASKS));
    }

    @Test
    public void execute_markedTask_unmarksTask() {
        TodoCommand tc = new TodoCommand("todo borrow book");
        assertDoesNotThrow(() -> {
            tc.execute(STORAGE, TASKS);
            MarkCommand mc = new MarkCommand("mark 1");
            mc.execute(STORAGE, TASKS);
        });

        String expectedOutput = "OK, I've marked this task as not done yet:\n"
                + "  [T][ ] borrow book ";

        // Local variable in function must be effectively final!
        final String[] actualOutput = new String[1];
        UnmarkCommand uc = new UnmarkCommand("unmark 1");
        assertDoesNotThrow(() -> {
            actualOutput[0] = uc.execute(STORAGE, TASKS);
        });
        assertEquals(expectedOutput, actualOutput[0]);
    }
}
