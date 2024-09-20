package Alex.Parser;
import Alex.Command.Command;
import Alex.Exceptions.AlexException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the Parser class.
 * It ensures that valid commands are correctly parsed and
 * that appropriate exceptions are thrown for invalid commands.
 */
public class ParserTest {

    /**
     * Tests if the parse method successfully processes a valid "todo" command.
     * Ensures that the command is not null and does not trigger an exit.
     */
    @Test
    public void parse_validCommand_success() {
        try {
            String input = "todo read book";
            Command command = Parser.parse(input);

            // Check if the command is parsed correctly
            assertNotNull(command);
            assertFalse(command.isExit());
        } catch (AlexException e) {
            fail("Parse should not throw an exception for a valid command.");
        }
    }

    /**
     * Tests if the parse method throws an AlexException for the invalid "blah" command.
     * Verifies that the exception message is as expected.
     */
    @Test
    public void parse_invalidBlahCommand_throwsException() {
        String input = "blah";
        AlexException exception = assertThrows(AlexException.class, () -> {
            Parser.parse(input);
        });

        // Check if the correct exception is thrown
        assertEquals("I'm Sorry! I don't know what you mean.", exception.getMessage());
    }

    /**
     * Tests if the parse method throws an AlexException for an incomplete "todo" command.
     * Ensures that the exception message corresponds to an empty todo description.
     */
    @Test
    public void parse_invalidTodoCommand_throwsException() {
        String input = "todo";
        AlexException exception = assertThrows(AlexException.class, () -> {
            Parser.parse(input);
        });

        // Check if the correct exception is thrown
        assertEquals("Oops! Description of a todo cannot be empty!", exception.getMessage());
    }
}
