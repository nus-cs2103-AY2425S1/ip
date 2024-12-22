package colress.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ToggleCommandTest {
    @Test
    public void equalsTest() {
        ToggleCommand toggleCommand = new ToggleCommand();

        // same object -> returns true
        assertTrue(toggleCommand.equals(toggleCommand));

        // different objects -> returns true
        assertTrue(toggleCommand.equals(new ToggleCommand()));

        // null -> returns false
        assertFalse(toggleCommand.equals(null));

        // different types -> returns false
        assertFalse(toggleCommand.equals(17));
    }
}
