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
                mc.execute(UI, STORAGE, TASKS));
    }

    @Test
    public void execute_invalidNumber_throwsException() {
        MarkCommand mc = new MarkCommand("mark 5");
        assertThrows(BrockException.class, () ->
                mc.execute(UI, STORAGE, TASKS));
    }

    @Test
    public void execute_unmarkedTask_marksTask() {
        TodoCommand tc = new TodoCommand("todo borrow book");
        assertDoesNotThrow(() -> tc.execute(UI, STORAGE, TASKS));
        NEW_OUT.reset();

        String expectedResponse = "Nice! I've marked this task as done:\n"
                + "  [T][X] borrow book ";
        UI.displayResponse(expectedResponse);
        String expectedOutput = super.getOutput();

        MarkCommand mc = new MarkCommand("mark 1");
        assertDoesNotThrow(() -> mc.execute(UI, STORAGE, TASKS));
        String actualOutput = super.getOutput();

        assertEquals(expectedOutput, actualOutput);
    }
}
