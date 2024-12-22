package colress.command;

import static colress.TestUtil.VALID_TASK_NUMBERS_ARGUMENT_ONE;
import static colress.TestUtil.VALID_TASK_NUMBERS_ARGUMENT_TWO;
import static colress.TestUtil.VALID_TASK_NUMBERS_ONE;
import static colress.TestUtil.VALID_TASK_NUMBERS_TWO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeleteCommandTest {
    @Test
    public void equalsTest() {
        DeleteCommand deleteCommand = new DeleteCommand();

        // null values -> returns true
        assertTrue(deleteCommand.equals(new DeleteCommand()));

        deleteCommand = new DeleteCommand(new String[]{VALID_TASK_NUMBERS_ARGUMENT_ONE}, VALID_TASK_NUMBERS_ONE);

        // same values -> returns true
        assertTrue(deleteCommand.equals(new DeleteCommand(
                new String[]{VALID_TASK_NUMBERS_ARGUMENT_ONE}, VALID_TASK_NUMBERS_ONE)));

        // same object -> returns true
        assertTrue(deleteCommand.equals(deleteCommand));

        // null -> returns false
        assertFalse(deleteCommand.equals(null));

        // different types -> returns false
        assertFalse(deleteCommand.equals(17));

        // different values -> returns false
        assertFalse(deleteCommand.equals(new DeleteCommand(
                new String[]{VALID_TASK_NUMBERS_ARGUMENT_TWO}, VALID_TASK_NUMBERS_TWO)));
    }
}
