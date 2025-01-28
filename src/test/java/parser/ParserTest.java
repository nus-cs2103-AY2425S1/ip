package parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import command.AddCommand;
import command.ExitCommand;
import command.InvalidCommand;
import command.ListCommand;
import exception.DynamikeException;

public class ParserTest {

    @Test
    public void parseInvalidCommand() throws DynamikeException {
        String input = "invalid command";
        assert(Parser.parse(input) instanceof InvalidCommand);
    }

    @Test
    public void parseEmptyCommand() throws DynamikeException {
        String input = "";
        assert(Parser.parse(input) instanceof InvalidCommand);
    }

    @Test
    public void parseByeCommand() throws DynamikeException {
        String input = "bye";
        assert(Parser.parse(input) instanceof ExitCommand);
    }

    @Test
    public void parseListCommand() throws DynamikeException {
        String input = "list";
        assert(Parser.parse(input) instanceof ListCommand);
    }

    @Test
    public void parseWrongDeadlineFormat() throws DynamikeException {
        String input = "deadline read book by tomorrow";
        assertThrows(DynamikeException.class, () -> Parser.parse(input));
    }

    @Test
    public void parseWrongEventFormat() throws DynamikeException {
        String input = "event meeting from tomorrow to next week";
        assertThrows(DynamikeException.class, () -> Parser.parse(input));
    }

    @Test
    public void parseWrongMarkFormat() throws DynamikeException {
        String input = "mark";
        assertThrows(DynamikeException.class, () -> Parser.parse(input));
    }

    @Test
    public void parseWrongUnmarkFormat() throws DynamikeException {
        String input = "unmark";
        assertThrows(DynamikeException.class, () -> Parser.parse(input));
    }

    @Test
    public void parseWrongDeleteFormat() throws DynamikeException {
        String input = "delete";
        assertThrows(DynamikeException.class, () -> Parser.parse(input));
    }

    @Test
    public void parseWrongTodoFormat() throws DynamikeException {
        String input = "todo";
        assertThrows(DynamikeException.class, () -> Parser.parse(input));
    }

    @Test
    public void parseCorrectDeadlineFormat() throws DynamikeException {
        String input = "deadline return book /by 2021-08-24 12:00";
        assert(Parser.parse(input) instanceof AddCommand);
    }

    @Test
    public void parseCorrectEventFormat() throws DynamikeException {
        String input = "event meeting /from 2021-08-24 12:00 /to 2021-08-24 12:30";
        assert(Parser.parse(input) instanceof AddCommand);
    }
}
