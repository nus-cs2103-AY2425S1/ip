package totoro.parser;

import totoro.command.CommandType;
import totoro.exception.TotoroMissingArgException;
import totoro.exception.TotoroUnknownCommandException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void testParse_ValidCommand() throws TotoroUnknownCommandException {
        String userCommand = "todo read book";
        CommandType expectedCommand = CommandType.TODO;

        CommandType parsedCommand = Parser.parseCommand(userCommand);

        assertEquals(expectedCommand, parsedCommand);
    }

    @Test
    public void testParse_InvalidCommand_ThrowsException() {
        String userCommand = "fly high";

        assertThrows(TotoroUnknownCommandException.class, () -> {
            Parser.parseCommand(userCommand);
        });
    }

    @Test
    public void testParse_ValidArgs() throws TotoroMissingArgException {
        String userInput = "todo read book";
        CommandType command = CommandType.TODO;

        String expectedArgs = "read book";

        String parsedArgs = Parser.parseArgs(command, userInput);

        assertEquals(expectedArgs, parsedArgs);
    }

    @Test
    public void testParse_MissingArgs_ThrowsException() {
        String userInput = "todo";
        CommandType command = CommandType.TODO;

        assertThrows(TotoroMissingArgException.class, () -> {
            Parser.parseArgs(command, userInput);
        });
    }
}
