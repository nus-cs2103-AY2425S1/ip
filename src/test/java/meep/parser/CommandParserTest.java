package meep.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CommandParserTest {

    @Test
    public void checkCommand_deadlineCommand_true() {
        assertTrue(CommandParser.checkCommand("deadline return book /by 2021-08-25", "deadline"));
    }

    @Test
    public void checkCommand_deadlineCommand_false() {
        assertFalse(CommandParser.checkCommand("deadline return book /by 2021-08-25", "event"));
    }

    @Test
    public void checkCommand_eventCommand_true() {
        assertTrue(CommandParser.checkCommand("event project meeting /at 2021-08-25", "event"));
    }

    @Test
    public void checkCommand_eventCommand_false() {
        assertFalse(CommandParser.checkCommand("event project meeting /at 2021-08-25", "deadline"));
    }

    @Test
    public void checkCommand_todoCommand_true() {
        assertTrue(CommandParser.checkCommand("todo read book", "todo"));
    }

    @Test
    public void checkCommand_todoCommand_false() {
        assertFalse(CommandParser.checkCommand("todo read book", "deadline"));
    }

    @Test
    public void checkEqualCommand_listCommand_true() {
        assertTrue(CommandParser.checkEqualCommand("list", "list"));
    }

    @Test
    public void checkEqualCommand_listCommand_false() {
        assertFalse(CommandParser.checkEqualCommand("list", "todo"));
    }

    @Test
    public void checkEqualCommand_caseSensitiveCommand_true() {
        assertTrue(CommandParser.checkEqualCommand("LIST", "list"));
    }

    @Test
    public void checkEqualCommand_caseSensitiveCommand_false() {
        assertFalse(CommandParser.checkEqualCommand("LIST", "todo"));
    }

    @Test
    public void checkCommand_markCommand_true() {
        assertTrue(CommandParser.checkCommand("mark 1", "mark"));
    }

    @Test
    public void checkCommand_markCommand_false() {
        assertFalse(CommandParser.checkCommand("mark 1", "list"));
    }

    @Test
    public void checkCommand_unmarkCommand_true() {
        assertTrue(CommandParser.checkCommand("unmark 1", "unmark"));
    }

    @Test
    public void checkCommand_unmarkCommand_false() {
        assertFalse(CommandParser.checkCommand("unmark 1", "list"));
    }

    @Test
    public void checkCommand_deleteCommand_true() {
        assertTrue(CommandParser.checkCommand("delete 1", "delete"));
    }

    @Test
    public void checkCommand_deleteCommand_false() {
        assertFalse(CommandParser.checkCommand("delete 1", "list"));
    }
}
