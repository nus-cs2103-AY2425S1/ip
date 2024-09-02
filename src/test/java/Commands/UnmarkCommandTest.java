package Commands;

import Exceptions.BrockException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnmarkCommandTest extends BaseCommandTest {
    @Test
    public void execute_missingNumber_throwsException() {
        UnmarkCommand uc = new UnmarkCommand("unmark");
        try {
            uc.execute(UI, STORAGE, TASKS);
        } catch (BrockException e) {
            assertEquals("Missing task number!", e.getMessage());
        }
    }

    @Test
    public void execute_invalidNumber_throwsException() {
        UnmarkCommand uc = new UnmarkCommand("unmark 5");
        try {
            uc.execute(UI, STORAGE, TASKS);
        } catch (BrockException e) {
            assertEquals("Task number does not exist!", e.getMessage());
        }
    }

    @Test
    public void execute_correctNumber_marksTask() throws BrockException {
        TodoCommand tc = new TodoCommand("todo borrow book");
        tc.execute(UI, STORAGE, TASKS);
        NEW_OUT.reset();

        String expectedResponse = "OK, I've marked this task as not done yet:\n"
                + "  [T][ ] borrow book ";
        UI.displayResponse(expectedResponse);
        String expectedOutput = super.getOutput();

        UnmarkCommand uc = new UnmarkCommand("unmark 1");
        uc.execute(UI, STORAGE, TASKS);
        String actualOutput = super.getOutput();

        assertEquals(expectedOutput, actualOutput);
    }
}
