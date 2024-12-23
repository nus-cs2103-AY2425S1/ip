package colress.command;

import static colress.testutil.TestUtil.VALID_MULTIPLE_TASK_NUMBERS;
import static colress.testutil.TestUtil.VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT;
import static colress.testutil.TestUtil.VALID_ONE_TASK_NUMBER;
import static colress.testutil.TestUtil.VALID_ONE_TASK_NUMBER_ARGUMENT;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeleteCommandTest {
    @Test
    public void equalsTest() {
        DeleteCommand deleteCommand = new DeleteCommand();

        // null values -> returns true
        assertTrue(deleteCommand.equals(new DeleteCommand()));

        deleteCommand = new DeleteCommand(new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT},
                VALID_MULTIPLE_TASK_NUMBERS);

        // same values -> returns true
        assertTrue(deleteCommand.equals(new DeleteCommand(
                new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT}, VALID_MULTIPLE_TASK_NUMBERS)));

        // same object -> returns true
        assertTrue(deleteCommand.equals(deleteCommand));

        // null -> returns false
        assertFalse(deleteCommand.equals(null));

        // different types -> returns false
        assertFalse(deleteCommand.equals(17));

        // different values -> returns false
        assertFalse(deleteCommand.equals(new DeleteCommand(
                new String[]{VALID_ONE_TASK_NUMBER_ARGUMENT}, VALID_ONE_TASK_NUMBER)));
    }
}
