package colress.command;

import static colress.TestUtil.VALID_TASK_NUMBERS_ARGUMENT_ONE;
import static colress.TestUtil.VALID_TASK_NUMBERS_ARGUMENT_TWO;
import static colress.TestUtil.VALID_TASK_NUMBERS_ONE;
import static colress.TestUtil.VALID_TASK_NUMBERS_TWO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CheckCommandTest {
    @Test
    public void equalsTest() {
        CheckCommand checkCommand = new CheckCommand();

        // null values -> returns true
        assertTrue(checkCommand.equals(new CheckCommand()));

        checkCommand = new CheckCommand(new String[]{VALID_TASK_NUMBERS_ARGUMENT_ONE}, VALID_TASK_NUMBERS_ONE);

        // same values -> returns true
        assertTrue(checkCommand.equals(new CheckCommand(
                new String[]{VALID_TASK_NUMBERS_ARGUMENT_ONE}, VALID_TASK_NUMBERS_ONE)));

        // same object -> returns true
        assertTrue(checkCommand.equals(checkCommand));

        // null -> returns false
        assertFalse(checkCommand.equals(null));

        // different types -> returns false
        assertFalse(checkCommand.equals(17));

        // different values -> returns false
        assertFalse(checkCommand.equals(new CheckCommand(
                new String[]{VALID_TASK_NUMBERS_ARGUMENT_TWO}, VALID_TASK_NUMBERS_TWO)));
    }
}
