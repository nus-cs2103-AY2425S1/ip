package friday.util;

import friday.command.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import friday.util.FridayException;
import friday.task.TaskList;

class ParserTest {

    private final TaskList tasks = new TaskList();

    @Test
    void parse_validMarkCommand_returnsMarkCommand() throws FridayException {
        String input = "mark 1";
        Command command = Parser.parse(input);

        assertTrue(command instanceof MarkUnmarkCommand);
    }

    @Test
    void parse_validUnmarkCommand_returnsUnmarkCommand() throws FridayException {
        String input = "unmark 2";
        Command command = Parser.parse(input);

        assertTrue(command instanceof MarkUnmarkCommand);
    }

    @Test
    void parse_validHelpCommand_returnsHelpCommand() throws FridayException {
        String input = "help";
        Command command = Parser.parse(input);

        assertTrue(command instanceof HelpCommand);
    }

    @Test
    void parse_validListCommand_returnsListCommand() throws FridayException {
        String input = "list";
        Command command = Parser.parse(input);

        assertTrue(command instanceof ListCommand);
    }

    @Test
    void parse_validByeCommand_returnsByeCommand() throws FridayException {
        String input = "bye";
        Command command = Parser.parse(input);

        assertTrue(command instanceof ByeCommand);
    }

    @Test
    void parse_invalidCommand_throwsFridayException() {
        String input = "invalid command";
        assertThrows(FridayException.class, () -> Parser.parse(input));
    }
}
