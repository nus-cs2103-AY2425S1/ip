package colress.command;

import static colress.testutil.TestUtil.VALID_KEYWORD_ONE;
import static colress.testutil.TestUtil.VALID_KEYWORD_TWO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FindCommandTest {
    @Test
    public void equalsTest() {
        FindCommand dateCommand = new FindCommand();

        // null values -> returns true
        assertTrue(dateCommand.equals(new FindCommand()));

        dateCommand = new FindCommand(new String[]{VALID_KEYWORD_ONE}, VALID_KEYWORD_ONE);

        // same values -> returns true
        assertTrue(dateCommand.equals(new FindCommand(new String[]{VALID_KEYWORD_ONE}, VALID_KEYWORD_ONE)));

        // same object -> returns true
        assertTrue(dateCommand.equals(dateCommand));

        // null -> returns false
        assertFalse(dateCommand.equals(null));

        // different types -> returns false
        assertFalse(dateCommand.equals(17));

        // different values -> returns false
        assertFalse(dateCommand.equals(new FindCommand(new String[]{VALID_KEYWORD_TWO}, VALID_KEYWORD_TWO)));
    }
}
