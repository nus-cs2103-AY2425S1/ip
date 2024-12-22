package colress.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ExitCommandTest {
    @Test
    public void equalsTest() {
        ExitCommand exitCommand = new ExitCommand();

        // same object -> returns true
        assertTrue(exitCommand.equals(exitCommand));

        // different objects -> returns true
        assertTrue(exitCommand.equals(new ExitCommand()));

        // null -> returns false
        assertFalse(exitCommand.equals(null));

        // different types -> returns false
        assertFalse(exitCommand.equals(17));
    }
}
