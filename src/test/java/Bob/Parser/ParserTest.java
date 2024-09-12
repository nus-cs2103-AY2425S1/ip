package bob.Parser;

import bob.command.Command;
import bob.command.TodoCommand;
import bob.Exception.BobException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parse_validTodoCommand_parsedCorrectly() throws BobException {
        Parser parser = new Parser();
        Command command = parser.parse("todo Read book");
        assertTrue(command instanceof TodoCommand);
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        Parser parser = new Parser();
        assertThrows(BobException.class, () -> {
            parser.parse("invalidCommand");
        });
    }
}
