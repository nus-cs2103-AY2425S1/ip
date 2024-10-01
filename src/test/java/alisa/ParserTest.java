package alisa;

import alisa.command.MarkCommand;
import alisa.command.UnmarkCommand;
import alisa.command.AddCommand;
import alisa.command.DeleteCommand;
import alisa.command.ListCommand;
import alisa.command.ExitCommand;
import alisa.exception.AlisaException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {
    @Test
    public void parse_markInput_markCommand() throws AlisaException {
        assertInstanceOf(MarkCommand.class, Parser.parse("mark 1"), "This command should return a mark command");
    }

    @Test
    public void parse_unmarkInput_unmarkCommand() throws AlisaException {
        assertInstanceOf(UnmarkCommand.class, Parser.parse("unmark 1"), "This command should return an unmark command");
    }

    @Test
    public void parse_todoInput_addCommand() throws AlisaException {
        assertInstanceOf(AddCommand.class, Parser.parse("todo finish homework"), "This command should return an add command");
    }

    @Test
    public void parse_eventInput_addCommand() throws AlisaException {
        assertInstanceOf(AddCommand.class, Parser.parse("event /from 2024-08-08 14:00 /to 2024-08-08 16:00"), "This command should return an add command");
    }

    @Test
    public void parse_deadlineInput_addCommand() throws AlisaException {
        assertInstanceOf(AddCommand.class, Parser.parse("deadline /by 2024-08-08 14:00"), "This command should return an add command");
    }

    @Test
    public void parse_listInput_listCommand() throws AlisaException {
        assertInstanceOf(ListCommand.class, Parser.parse("list"), "This command should return a list command");
    }

    @Test
    public void parse_deleteInput_deleteCommand() throws AlisaException {
        assertInstanceOf(DeleteCommand.class, Parser.parse("delete 1"), "This command should return a delete command");
    }

    @Test
    public void parse_byeInput_exitCommand() throws AlisaException {
        assertInstanceOf(ExitCommand.class, Parser.parse("bye"), "This command should return an exit command");
    }

    @Test
    public void parse_invalidInput_exceptionThrown() throws AlisaException {
        assertInstanceOf(AlisaException.class, Parser.parse("hello"), "This command should return an exception");
    }
}
