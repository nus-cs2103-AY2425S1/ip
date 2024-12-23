package colress.command;

import static colress.testutil.TestUtil.VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT;
import static colress.testutil.TestUtil.VALID_ONE_TASK_NUMBER_ARGUMENT;
import static colress.testutil.TestUtil.VALID_MULTIPLE_TASK_NUMBERS;
import static colress.testutil.TestUtil.VALID_ONE_TASK_NUMBER;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CheckCommandTest {
    @Test
    public void equalsTest() {
        CheckCommand checkCommand = new CheckCommand();

        // null values -> returns true
        assertTrue(checkCommand.equals(new CheckCommand()));

        checkCommand = new CheckCommand(new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT}, VALID_MULTIPLE_TASK_NUMBERS);

        // same values -> returns true
        assertTrue(checkCommand.equals(new CheckCommand(
                new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT}, VALID_MULTIPLE_TASK_NUMBERS)));

        // same object -> returns true
        assertTrue(checkCommand.equals(checkCommand));

        // null -> returns false
        assertFalse(checkCommand.equals(null));

        // different types -> returns false
        assertFalse(checkCommand.equals(17));

        // different values -> returns false
        assertFalse(checkCommand.equals(new CheckCommand(
                new String[]{VALID_ONE_TASK_NUMBER_ARGUMENT}, VALID_ONE_TASK_NUMBER)));
    }
}
