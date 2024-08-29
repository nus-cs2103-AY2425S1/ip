package sage.parser;

import org.junit.jupiter.api.Test;
import sage.command.Command;
import sage.command.ExitCommand;
import sage.exception.SageException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void testParse_exitCommand() {
        Parser parser = new Parser();
        try {
            Command command = parser.parse("bye");
            assertTrue(command instanceof ExitCommand,
                    "Parsed command should be an instance of Exit command");
        } catch (SageException e) {
            fail("No exception should be thrown for 'bye' command");
        }
    }

    @Test
    public void testParse_toDoCommand_emptyDescription() {
        Parser parser = new Parser();
        Exception exception = assertThrows(SageException.class, () -> parser.parse("todo"),
                "Expected SageException for empty description in 'todo' command");
        assertEquals("The description of todo task cannot be empty!", exception.getMessage());
    }

    @Test
    public void testParse_UnknownCommand() {
        Parser parser = new Parser();
        Exception exception = assertThrows(SageException.class, () -> parser.parse("unknownCommand"),
                "Expected SageException for unknown command");
        assertEquals("Sorry, what do you mean? :p", exception.getMessage());
    }
}
