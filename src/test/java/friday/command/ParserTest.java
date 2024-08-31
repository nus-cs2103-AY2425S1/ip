package friday.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import friday.util.FridayException;

class ParserTest {

    @Test
    void parse_validMarkCommand_returnsMarkCommand() throws FridayException {
        String input = "mark 1";
        Command command = Parser.parse(input);

        Assertions.assertInstanceOf(MarkUnmarkCommand.class, command);
    }

    @Test
    void parse_validUnmarkCommand_returnsUnmarkCommand() throws FridayException {
        String input = "unmark 2";
        Command command = Parser.parse(input);

        Assertions.assertInstanceOf(MarkUnmarkCommand.class, command);
    }

    @Test
    void parse_validHelpCommand_returnsHelpCommand() throws FridayException {
        String input = "help";
        Command command = Parser.parse(input);

        Assertions.assertInstanceOf(HelpCommand.class, command);
    }

    @Test
    void parse_validListCommand_returnsListCommand() throws FridayException {
        String input = "list";
        Command command = Parser.parse(input);

        Assertions.assertInstanceOf(ListCommand.class, command);
    }

    @Test
    void parse_validByeCommand_returnsByeCommand() throws FridayException {
        String input = "bye";
        Command command = Parser.parse(input);

        Assertions.assertInstanceOf(ByeCommand.class, command);
    }

    @Test
    void parse_invalidCommand_throwsFridayException() {
        String input = "invalid command";
        Assertions.assertThrows(FridayException.class, () -> Parser.parse(input));
    }
}
