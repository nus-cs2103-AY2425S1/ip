package joe.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the InvalidCommandException class.
 */
public class InvalidCommandExceptionTest {
    /**
     * Tests the getMessage method in InvalidCommandException.
     */
    @Test
    public void testInvalidCommandException() {
        InvalidCommandException invalidCommandException = new InvalidCommandException("invalidCommand");
        assertEquals("\"invalidCommand\" is not a valid command.\n"
                + "Type /help to see the list of available commands.", invalidCommandException.getMessage());
    }
}
