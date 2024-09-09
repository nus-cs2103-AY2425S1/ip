package util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import command.AddCommand;
import command.Command;
import exception.ScheduloException;

/**
 * Unit test for the Parser class.
 */
public class ParserTest {

    /**
     * Tests parsing a valid "todo" command and verifies that the resulting command is an instance of AddCommand.
     *
     * @throws ScheduloException If the command parsing fails.
     */
    @Test
    public void testParseValidTodoCommand() throws ScheduloException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddCommand); // Check if it's an AddCommand
    }

    /**
     * Tests parsing an invalid command and verifies that a ScheduloException is thrown.
     */
    @Test
    public void testParseInvalidCommand() {
        assertThrows(ScheduloException.class, () -> {
            Parser.parse("invalid command");
        });
    }
}
