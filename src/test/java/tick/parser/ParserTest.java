package tick.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import tick.commands.AddCommand;
import tick.commands.DeleteCommand;
import tick.commands.ExitCommand;
import tick.commands.FindCommand;
import tick.commands.ListCommand;
import tick.commands.MarkCommand;
import tick.commands.UnmarkCommand;
import tick.exceptions.TickException;

public class ParserTest {
    @Test
    public void parseByeCommand_correctInput() throws TickException {
        String input = "bye";
        assert(Parser.parse(input) instanceof ExitCommand);
    }

    @Test
    public void parseListCommand_correctInput() throws TickException {
        String input = "list";
        assert(Parser.parse(input) instanceof ListCommand);
    }

    @Test
    public void parseMark_correctInput() throws TickException {
        String input = "mark 1";
        assert(Parser.parse(input) instanceof MarkCommand);
    }

    @Test
    public void parseUnmark_correctInput() throws TickException {
        String input = "unmark 1";
        assert(Parser.parse(input) instanceof UnmarkCommand);
    }

    @Test
    public void parseDelete_correctInput() throws TickException {
        String input = "delete 1";
        assert(Parser.parse(input) instanceof DeleteCommand);
    }

    @Test
    public void parseTodo_correctInput() throws TickException {
        String input = "todo sleep";
        assert(Parser.parse(input) instanceof AddCommand);
    }

    @Test
    public void parseDeadline_correctInput() throws TickException {
        String input = "deadline do homework /by 2024-08-28";
        assert(Parser.parse(input) instanceof AddCommand);
    }

    @Test
    public void parseEvent_correctInput() throws TickException {
        String input = "event holiday /from 2024-08-28 /to 2024-08-29";
        assert(Parser.parse(input) instanceof AddCommand);
    }

    @Test
    public void parseFind_correctInput() throws TickException {
        String input = "find homework";
        assert(Parser.parse(input) instanceof FindCommand);
    }

    @Test
    public void parseInvalidCommand() {
        // Unknown command
        String input = "blah";
        assertThrows(TickException.class, () -> Parser.parse(input));

        // Empty command
        String input2 = "";
        assertThrows(TickException.class, () -> Parser.parse(input2));
    }

    @Test
    public void parseMark_wrongFormat() throws TickException {
        // Missing index
        String input = "mark";
        assertThrows(TickException.class, () -> Parser.parse(input));

        // Invalid index
        String input2 = "mark abc";
        assertThrows(TickException.class, () -> Parser.parse(input2));
    }

    @Test
    public void parseUnmark_wrongFormat() throws TickException {
        // Missing index
        String input = "unmark";
        assertThrows(TickException.class, () -> Parser.parse(input));

        // Invalid index
        String input2 = "unmark abc";
        assertThrows(TickException.class, () -> Parser.parse(input2));
    }

    @Test
    public void parseDelete_wrongFormat() throws TickException {
        // Missing index
        String input = "delete";
        assertThrows(TickException.class, () -> Parser.parse(input));

        // Invalid index
        String input2 = "delete abc";
        assertThrows(TickException.class, () -> Parser.parse(input2));
    }

    @Test
    public void parseTodo_wrongFormat() throws TickException {
        // Missing description
        String input = "todo";
        assertThrows(TickException.class, () -> Parser.parse(input));

        // Blank description
        String input2 = "todo ";
        assertThrows(TickException.class, () -> Parser.parse(input2));
    }

    @Test
    public void parseDeadline_wrongFormat() throws TickException {
        // Missing description
        String input = "deadline";
        assertThrows(TickException.class, () -> Parser.parse(input));

        // Blank description
        String input2 = "deadline ";
        assertThrows(TickException.class, () -> Parser.parse(input2));

        // Missing /by
        String input3 = "deadline do homework";
        assertThrows(TickException.class, () -> Parser.parse(input3));

        // Missing date
        String input4 = "deadline do homework /by";
        assertThrows(TickException.class, () -> Parser.parse(input4));

        // Invalid date
        String input5 = "deadline do homework /by abc";
        assertThrows(TickException.class, () -> Parser.parse(input5));
    }

    @Test
    public void parseEvent_wrongFormat() throws TickException {
        // Missing description
        String input = "event";
        assertThrows(TickException.class, () -> Parser.parse(input));

        // Blank description
        String input2 = "event ";
        assertThrows(TickException.class, () -> Parser.parse(input2));

        // Missing /from
        String input3 = "event holiday";
        assertThrows(TickException.class, () -> Parser.parse(input3));

        // Missing start date
        String input4 = "event holiday /from";
        assertThrows(TickException.class, () -> Parser.parse(input4));

        // Invalid start date
        String input5 = "event holiday /from abc";
        assertThrows(TickException.class, () -> Parser.parse(input5));

        // Missing /to
        String input6 = "event holiday /from 2024-08-28";
        assertThrows(TickException.class, () -> Parser.parse(input6));

        // Missing end date
        String input7 = "event holiday /from 2024-08-28 /to";
        assertThrows(TickException.class, () -> Parser.parse(input7));

        // Invalid end date
        String input8 = "event holiday /from 2024-08-28 /to abc";
        assertThrows(TickException.class, () -> Parser.parse(input8));
    }

    @Test
    public void parseFind_wrongFormat() throws TickException {
        // Missing keyword
        String input = "find";
        assertThrows(TickException.class, () -> Parser.parse(input));

        // Blank keyword
        String input2 = "find ";
        assertThrows(TickException.class, () -> Parser.parse(input2));
    }
}
