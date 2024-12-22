package colress.command;

import static colress.TestUtil.VALID_TASK_NUMBERS_ARGUMENT_ONE;
import static colress.TestUtil.VALID_TASK_NUMBERS_ARGUMENT_TWO;
import static colress.TestUtil.VALID_TASK_NUMBERS_ONE;
import static colress.TestUtil.VALID_TASK_NUMBERS_TWO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UncheckCommandTest {
    @Test
    public void equalsTest() {
        UncheckCommand uncheckCommand = new UncheckCommand();

        // null values -> returns true
        assertTrue(uncheckCommand.equals(new UncheckCommand()));

        uncheckCommand = new UncheckCommand(new String[]{VALID_TASK_NUMBERS_ARGUMENT_ONE}, VALID_TASK_NUMBERS_ONE);

        // same values -> returns true
        assertTrue(uncheckCommand.equals(new UncheckCommand(
                new String[]{VALID_TASK_NUMBERS_ARGUMENT_ONE}, VALID_TASK_NUMBERS_ONE)));

        // same object -> returns true
        assertTrue(uncheckCommand.equals(uncheckCommand));

        // null -> returns false
        assertFalse(uncheckCommand.equals(null));

        // different types -> returns false
        assertFalse(uncheckCommand.equals(17));

        // different values -> returns false
        assertFalse(uncheckCommand.equals(new UncheckCommand(
                new String[]{VALID_TASK_NUMBERS_ARGUMENT_TWO}, VALID_TASK_NUMBERS_TWO)));
    }
}
