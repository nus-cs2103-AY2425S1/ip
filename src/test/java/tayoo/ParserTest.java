package tayoo;

import tayoo.command.ExitCommand;
import tayoo.exception.ParserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommand_exitCode_success() throws Exception {
        assertInstanceOf(ExitCommand.class, Parser.parseCommand("bye"));
        assertInstanceOf(ExitCommand.class, Parser.parseCommand("clOse"));
        assertInstanceOf(ExitCommand.class, Parser.parseCommand("GOODBYe"));
        assertInstanceOf(ExitCommand.class, Parser.parseCommand("qUIT"));
        assertInstanceOf(ExitCommand.class, Parser.parseCommand("EXIT"));
    }

    @Test
    public void parseCommand_unknownCommand_failure() throws Exception {
        assertThrows(ParserException.class, () -> Parser.parseCommand("./quit"));
    }

    @Test
    public void parseCommand_deadlineTask_success() throws Exception {
        String commandStr = "deadline return book /by 13/01/2024 18:30";
        assertEquals("[D][ ] return book (by: 13 Jan 2024 18:30)",
                     Parser.parseCommand(commandStr.trim()).toString());
    }


}
