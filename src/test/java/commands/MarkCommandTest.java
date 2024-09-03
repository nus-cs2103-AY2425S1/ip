package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.BrockException;

public class MarkCommandTest extends BaseCommandTest {
    @Test
    public void execute_missingNumber_throwsException() {
        MarkCommand mc = new MarkCommand("mark");
        try {
            mc.execute(UI, STORAGE, TASKS);
        } catch (BrockException e) {
            assertEquals("Missing task number!", e.getMessage());
        }
    }

    @Test
    public void execute_invalidNumber_throwsException() {
        MarkCommand mc = new MarkCommand("mark 5");
        try {
            mc.execute(UI, STORAGE, TASKS);
        } catch (BrockException e) {
            assertEquals("Task number does not exist!", e.getMessage());
        }
    }

    @Test
    public void execute_correctNumber_marksTask() throws BrockException {
        TodoCommand tc = new TodoCommand("todo borrow book");
        tc.execute(UI, STORAGE, TASKS);
        NEW_OUT.reset();

        String expectedResponse = "Nice! I've marked this task as done:\n"
                + "  [T][X] borrow book ";
        UI.displayResponse(expectedResponse);
        String expectedOutput = super.getOutput();

        MarkCommand mc = new MarkCommand("mark 1");
        mc.execute(UI, STORAGE, TASKS);
        String actualOutput = super.getOutput();

        assertEquals(expectedOutput, actualOutput);
    }
}
