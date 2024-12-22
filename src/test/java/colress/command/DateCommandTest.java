package colress.command;

import static colress.TestUtil.VALID_DATE_ARGUMENT_ONE;
import static colress.TestUtil.VALID_DATE_ARGUMENT_TWO;
import static colress.TestUtil.VALID_DATE_ONE;
import static colress.TestUtil.VALID_DATE_TWO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DateCommandTest {
    @Test
    public void equalsTest() {
        DateCommand dateCommand = new DateCommand();

        // null values -> returns true
        assertTrue(dateCommand.equals(new DateCommand()));

        dateCommand = new DateCommand(new String[]{VALID_DATE_ARGUMENT_ONE}, VALID_DATE_ONE);

        // same values -> returns true
        assertTrue(dateCommand.equals(new DateCommand(
                new String[]{VALID_DATE_ARGUMENT_ONE}, VALID_DATE_ONE)));

        // same object -> returns true
        assertTrue(dateCommand.equals(dateCommand));

        // null -> returns false
        assertFalse(dateCommand.equals(null));

        // different types -> returns false
        assertFalse(dateCommand.equals(17));

        // different values -> returns false
        assertFalse(dateCommand.equals(new DateCommand(
                new String[]{VALID_DATE_ARGUMENT_TWO}, VALID_DATE_TWO)));
    }
}
