package colress.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ListCommandTest {
    @Test
    public void equalsTest() {
        ListCommand listCommand = new ListCommand();

        // same object -> returns true
        assertTrue(listCommand.equals(listCommand));

        // different objects -> returns true
        assertTrue(listCommand.equals(new ListCommand()));

        // null -> returns false
        assertFalse(listCommand.equals(null));

        // different types -> returns false
        assertFalse(listCommand.equals(17));
    }
}
