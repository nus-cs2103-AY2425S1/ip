package tohru.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import tohru.exception.TohruException;


public class ParserTest {

    @Test
    public void parse_byeCommand_success() {
        String prompt = ByeCommand.COMMAND_PREFIX;

        Command c = null;
        try {
            c = Parser.parse(prompt);
        } catch (TohruException e) {
            fail();
        }

        assertEquals(ByeCommand.class, c.getClass());
    }

    @Test
    public void parse_listCommand_success() {
        String prompt = ListCommand.COMMAND_PREFIX;

        Command c = null;
        try {
            c = Parser.parse(prompt);
        } catch (TohruException e) {
            fail();
        }

        assertEquals(ListCommand.class, c.getClass());
    }

    @Test
    public void parse_markCommand_success() {
        String prompt = MarkCommand.COMMAND_PREFIX;

        Command c = null;
        try {
            c = Parser.parse(prompt);
        } catch (TohruException e) {
            fail();
        }

        assertEquals(MarkCommand.class, c.getClass());
    }

    @Test
    public void parse_unmarkCommand_success() {
        String prompt = UnmarkCommand.COMMAND_PREFIX;

        Command c = null;
        try {
            c = Parser.parse(prompt);
        } catch (TohruException e) {
            fail();
        }

        assertEquals(UnmarkCommand.class, c.getClass());
    }

    @Test
    public void parse_deleteCommand_success() {
        String prompt = DeleteCommand.COMMAND_PREFIX;

        Command c = null;
        try {
            c = Parser.parse(prompt);
        } catch (TohruException e) {
            fail();
        }

        assertEquals(DeleteCommand.class, c.getClass());
    }

    @Test
    public void parse_addTodoCommand_success() {
        String prompt = AddTodoCommand.COMMAND_PREFIX;

        Command c = null;
        try {
            c = Parser.parse(prompt);
        } catch (TohruException e) {
            fail();
        }

        assertEquals(AddTodoCommand.class, c.getClass());
    }

    @Test
    public void parse_addDeadlineCommand_success() {
        String prompt = AddDeadlineCommand.COMMAND_PREFIX;

        Command c = null;
        try {
            c = Parser.parse(prompt);
        } catch (TohruException e) {
            fail();
        }

        assertEquals(AddDeadlineCommand.class, c.getClass());
    }

    @Test
    public void parse_addEventCommand_success() {
        String prompt = AddEventCommand.COMMAND_PREFIX;

        Command c = null;
        try {
            c = Parser.parse(prompt);
        } catch (TohruException e) {
            fail();
        }

        assertEquals(AddEventCommand.class, c.getClass());
    }

    @Test
    public void parse_unknownCommand_exceptionThrown() {
        String prompt = "unknown";

        TohruException e = assertThrowsExactly(TohruException.class, () -> {
            Parser.parse(prompt);
        });
        assertEquals("You have entered an invalid option! Please try again.",
                e.getMessage());
    }

}
