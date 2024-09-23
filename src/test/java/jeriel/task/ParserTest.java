package jeriel.util;

import jeriel.command.Command;
import jeriel.command.AddTodoCommand;
import jeriel.command.ExitCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_validCommand_todoCommand() throws JerielException {
        Command command = Parser.parse("todo Read book");
        assertTrue(command instanceof AddTodoCommand);
    }

    @Test
    public void parse_invalidCommand_throwsException() {
        assertThrows(JerielException.class, () -> {
            Parser.parse("invalidCommand");
        });
    }

    @Test
    public void parse_validCommand_byeCommand() throws JerielException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }
}
