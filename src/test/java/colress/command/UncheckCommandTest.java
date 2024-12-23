package colress.command;

import static colress.testutil.TestUtil.VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT;
import static colress.testutil.TestUtil.VALID_ONE_TASK_NUMBER_ARGUMENT;
import static colress.testutil.TestUtil.VALID_MULTIPLE_TASK_NUMBERS;
import static colress.testutil.TestUtil.VALID_ONE_TASK_NUMBERS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UncheckCommandTest {
    @Test
    public void equalsTest() {
        UncheckCommand uncheckCommand = new UncheckCommand();

        // null values -> returns true
        assertTrue(uncheckCommand.equals(new UncheckCommand()));

        uncheckCommand = new UncheckCommand(new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT}, VALID_MULTIPLE_TASK_NUMBERS);

        // same values -> returns true
        assertTrue(uncheckCommand.equals(new UncheckCommand(
                new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT}, VALID_MULTIPLE_TASK_NUMBERS)));

        // same object -> returns true
        assertTrue(uncheckCommand.equals(uncheckCommand));

        // null -> returns false
        assertFalse(uncheckCommand.equals(null));

        // different types -> returns false
        assertFalse(uncheckCommand.equals(17));

        // different values -> returns false
        assertFalse(uncheckCommand.equals(new UncheckCommand(
                new String[]{VALID_ONE_TASK_NUMBER_ARGUMENT}, VALID_ONE_TASK_NUMBERS)));
    }
}
