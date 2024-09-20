package snipe.util;

import org.junit.jupiter.api.Test;
import snipe.command.AddCommand;
import snipe.command.Command;
import snipe.exception.SnipeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    /**
     * Tests the {@link Parser#parse(String)} method to ensure that a "todo" command
     * is correctly parsed into an instance of {@link snipe.command.AddCommand}.
     * Verifies that the new command is properly initialized with the correct type.
     */
    @Test
    public void parseAddCommandToDo() throws SnipeException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void testParseInvalidCommand() {
        SnipeException thrown = assertThrows(SnipeException.class, () -> {
            Parser.parse("Invalid command");
        });
        // Verify that the exception message is as expected
        assertEquals("I'm sorry, I don't understand that command.", thrown.getMessage());
    }

    /*
    @Test
    public void testParseInvalidDeleteCommand() {
        SnipeException thrown = assertThrows(SnipeException.class, () -> {
            Parser.parse("delete ");
        });
        // Verify that the exception message is as expected
        assertEquals("Please input a number. Use 'help for correct syntax", thrown.getMessage());
    }
    */

    @Test
    public void testParseInvalidTaskCommand() {
        SnipeException thrown = assertThrows(SnipeException.class, () -> {
            Parser.parse("event invalid command");
        });
        // Verify that the exception message is as expected
        assertEquals("An event requires a description, a /from date, and a /to date.", thrown.getMessage());
    }
}
