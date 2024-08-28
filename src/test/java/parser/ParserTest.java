package parser;

import command.AddCommand;
import command.ExitCommand;
import command.ListCommand;
import exception.DudeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parseInvalidCommand() {
        String input = "invalid command";
        assertThrows(DudeException.class, () -> Parser.parse(input));
    }

    @Test
    public void parseEmptyCommand() {
        String input = "";
        assertThrows(DudeException.class, () -> Parser.parse(input));
    }

    @Test
    public void parseByeCommand() throws DudeException {
        String input = "bye";
        assert(Parser.parse(input) instanceof ExitCommand);
    }

    @Test
    public void parseListCommand() throws DudeException {
        String input = "list";
        assert(Parser.parse(input) instanceof ListCommand);
    }

    @Test
    public void parseWrongDeadlineFormat() throws DudeException {
        String input = "read book by tomorrow";
        assertThrows(DudeException.class, () -> Parser.parse(input));
    }

    @Test
    public void parseWrongEventFormat() throws DudeException {
        String input = "meeting from tomorrow to next week";
        assertThrows(DudeException.class, () -> Parser.parse(input));
    }

    @Test
    public void parseWrongMarkFormat() throws DudeException {
        String input = "mark";
        assertThrows(DudeException.class, () -> Parser.parse(input));
    }

    @Test
    public void parseWrongUnmarkFormat() throws DudeException {
        String input = "unmark";
        assertThrows(DudeException.class, () -> Parser.parse(input));
    }

    @Test
    public void parseWrongDeleteFormat() throws DudeException {
        String input = "delete";
        assertThrows(DudeException.class, () -> Parser.parse(input));
    }

    @Test
    public void parseWrongTodoFormat() throws DudeException {
        String input = "todo";
        assertThrows(DudeException.class, () -> Parser.parse(input));
    }

    @Test
    public void parseCorrectDeadlineFormat() throws DudeException {
        String input = "deadline return book /by 2021-08-24 12:00";
        assert(Parser.parse(input) instanceof AddCommand);
    }

    @Test
    public void parseCorrectEventFormat() throws DudeException {
        String input = "event meeting /from 2021-08-24 12:00 /to 2021-08-24 12:30";
        assert(Parser.parse(input) instanceof AddCommand);
    }
}
