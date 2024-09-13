package jeff.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import jeff.command.AddDeadlineCommand;
import jeff.command.AddEventCommand;
import jeff.command.AddToDoCommand;
import jeff.command.Command;
import jeff.command.DateCommand;
import jeff.command.DeleteCommand;
import jeff.command.ExitCommand;
import jeff.command.FindCommand;
import jeff.command.ListCommand;
import jeff.command.MarkCommand;
import jeff.command.UnmarkCommand;
import jeff.exception.JeffException;

public class ParserTest {
    private static final String ERROR_MSG = "Sorry, I don't know what this means.";

    @Test
    public void parse_listCommand() throws JeffException {
        Command c1 = Parser.parse("list");
        assertInstanceOf(ListCommand.class, c1);

        Command c2 = Parser.parse("l");
        assertInstanceOf(ListCommand.class, c2);
    }

    @Test
    public void parse_listCommandWithMoreWords_throwsException() throws JeffException {
        JeffException e1 = assertThrows(JeffException.class, () -> Parser.parse("listhello"));
        assertEquals(ERROR_MSG, e1.toString());

        JeffException e2 = assertThrows(JeffException.class, () -> Parser.parse("lhello"));
        assertEquals(ERROR_MSG, e2.toString());
    }

    @Test
    public void parse_exitCommand() throws JeffException {
        Command c1 = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, c1);

        Command c2 = Parser.parse("b");
        assertInstanceOf(ExitCommand.class, c2);
    }

    @Test
    public void parse_exitCommandWithMoreWords_throwsException() throws JeffException {
        JeffException e1 = assertThrows(JeffException.class, () -> Parser.parse("byehello"));
        assertEquals(ERROR_MSG, e1.toString());

        JeffException e2 = assertThrows(JeffException.class, () -> Parser.parse("bhello"));
        assertEquals(ERROR_MSG, e2.toString());
    }

    @Test
    public void parse_markCommand() throws JeffException {
        Command c1 = Parser.parse("mark");
        assertInstanceOf(MarkCommand.class, c1);

        Command c2 = Parser.parse("m");
        assertInstanceOf(MarkCommand.class, c2);
    }

    @Test
    public void parse_unmarkCommand() throws JeffException {
        Command c1 = Parser.parse("unmark");
        assertInstanceOf(UnmarkCommand.class, c1);

        Command c2 = Parser.parse("u");
        assertInstanceOf(UnmarkCommand.class, c2);
    }

    @Test
    public void parse_deleteCommand() throws JeffException {
        Command c1 = Parser.parse("delete");
        assertInstanceOf(DeleteCommand.class, c1);

        Command c2 = Parser.parse("dd");
        assertInstanceOf(DeleteCommand.class, c2);
    }

    @Test
    public void parse_addTodoCommand() throws JeffException {
        Command c1 = Parser.parse("todo");
        assertInstanceOf(AddToDoCommand.class, c1);

        Command c2 = Parser.parse("t");
        assertInstanceOf(AddToDoCommand.class, c2);
    }

    @Test
    public void parse_addDeadlineCommand() throws JeffException {
        Command c1 = Parser.parse("deadline");
        assertInstanceOf(AddDeadlineCommand.class, c1);

        Command c2 = Parser.parse("dl");
        assertInstanceOf(AddDeadlineCommand.class, c2);
    }

    @Test
    public void parse_addEventCommand() throws JeffException {
        Command c1 = Parser.parse("event");
        assertInstanceOf(AddEventCommand.class, c1);

        Command c2 = Parser.parse("e");
        assertInstanceOf(AddEventCommand.class, c2);
    }

    @Test
    public void parse_dateCommand() throws JeffException {
        Command c1 = Parser.parse("date");
        assertInstanceOf(DateCommand.class, c1);

        Command c2 = Parser.parse("dt");
        assertInstanceOf(DateCommand.class, c2);
    }

    @Test
    public void parse_findCommand() throws JeffException {
        Command c1 = Parser.parse("find");
        assertInstanceOf(FindCommand.class, c1);

        Command c2 = Parser.parse("f");
        assertInstanceOf(FindCommand.class, c2);
    }

    @Test
    public void parse_nonExistentCommand_throwsException() throws JeffException {
        JeffException e1 = assertThrows(JeffException.class, () -> Parser.parse("hello"));
        assertEquals(ERROR_MSG, e1.toString());

        JeffException e2 = assertThrows(JeffException.class, () -> Parser.parse(""));
        assertEquals(ERROR_MSG, e2.toString());
    }
}
