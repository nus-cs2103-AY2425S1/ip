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
                uc.execute(UI, STORAGE, TASKS));
    }

    @Test
    public void execute_invalidNumber_throwsException() {
        UnmarkCommand uc = new UnmarkCommand("unmark 5");
        assertThrows(BrockException.class, () ->
                uc.execute(UI, STORAGE, TASKS));
    }

    @Test
    public void execute_markedTask_unmarksTask() {
        TodoCommand tc = new TodoCommand("todo borrow book");
        assertDoesNotThrow(() -> {
            tc.execute(UI, STORAGE, TASKS);
            MarkCommand mc = new MarkCommand("mark 1");
            mc.execute(UI, STORAGE, TASKS);
        });
        NEW_OUT.reset();

        String expectedResponse = "OK, I've marked this task as not done yet:\n"
                + "  [T][ ] borrow book ";
        UI.displayResponse(expectedResponse);
        String expectedOutput = super.getOutput();

        UnmarkCommand uc = new UnmarkCommand("unmark 1");
        assertDoesNotThrow(() -> uc.execute(UI, STORAGE, TASKS));
        String actualOutput = super.getOutput();

        assertEquals(expectedOutput, actualOutput);
    }
}
