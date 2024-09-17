package tick.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import tick.commands.AddCommand;
import tick.commands.ExitCommand;
import tick.commands.ListCommand;
import tick.exceptions.TickException;


public class ParserTest {
    @Test
    public void parseByeCommand() throws TickException {
        String input = "bye";
        assert(Parser.parse(input) instanceof ExitCommand);
    }

    @Test
    public void parseListCommand() throws TickException {
        String input = "list";
        assert(Parser.parse(input) instanceof ListCommand);
    }

    @Test
    public void parseCorrectTodoFormat() throws TickException {
        String input = "todo sleep";
        assert(Parser.parse(input) instanceof AddCommand);
    }

    @Test
    public void parseCorrectDeadlineFormat() throws TickException {
        String input = "deadline do homework /by 2024-08-28";
        assert(Parser.parse(input) instanceof AddCommand);
    }

    @Test
    public void parseCorrectEventFormat() throws TickException {
        String input = "event holiday /from 2024-08-28 /to 2024-08-29";
        assert(Parser.parse(input) instanceof AddCommand);
    }

    @Test
    public void parseInvalidCommand() {
        String input = "blah";
        assertThrows(TickException.class, () -> Parser.parse(input));
    }

    @Test
    public void parseWrongMarkFormat() throws TickException {
        String input = "mark";
        assertThrows(TickException.class, () -> Parser.parse(input));
    }

    @Test
    public void parseWrongUnmarkFormat() throws TickException {
        String input = "unmark";
        assertThrows(TickException.class, () -> Parser.parse(input));
    }

    @Test
    public void parseWrongDeleteFormat() throws TickException {
        String input = "delete";
        assertThrows(TickException.class, () -> Parser.parse(input));
    }
}
