import Alex.Command.Command;
import Alex.Exceptions.AlexException;
import Alex.Parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

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

    @Test
    public void parse_invalidBlahCommand_throwsException() {
        String input = "blah";
        AlexException exception = assertThrows(AlexException.class, () -> {
            Parser.parse(input);
        });

        // Check if the correct exception is thrown
        assertEquals("I'm Sorry! I don't know what you mean.", exception.getMessage());
    }

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
