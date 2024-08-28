package joe.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidCommandExceptionTest {
    @Test
    public void testInvalidCommandException() {
        InvalidCommandException invalidCommandException = new InvalidCommandException("invalidCommand");
        assertEquals("\"invalidCommand\" is not a valid command.\n" +
                "Type /help to see the list of available commands.", invalidCommandException.getMessage());
    }
}
