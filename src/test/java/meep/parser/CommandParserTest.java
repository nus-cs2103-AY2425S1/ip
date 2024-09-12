package meep.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandParserTest {

    @Test
    public void checkCommand_deadlineCommandWithArgument_true() {
        assertTrue(CommandParser.checkCommandWithArgument("deadline return book /by 2021-08-25", "deadline"));
    }

    @Test
    public void checkCommand_deadlineCommandWithArgument_false() {
        assertFalse(CommandParser.checkCommandWithArgument("deadline return book /by 2021-08-25", "event"));
    }

    @Test
    public void checkCommand_eventCommandWithArgument_true() {
        assertTrue(CommandParser.checkCommandWithArgument("event project meeting /at 2021-08-25", "event"));
    }

    @Test
    public void checkCommand_eventCommandWithArgument_false() {
        assertFalse(CommandParser.checkCommandWithArgument("event project meeting /at 2021-08-25", "deadline"));
    }

    @Test
    public void checkCommand_todoCommandWithArgument_true() {
        assertTrue(CommandParser.checkCommandWithArgument("todo read book", "todo"));
    }

    @Test
    public void checkCommand_todoCommandWithArgument_false() {
        assertFalse(CommandParser.checkCommandWithArgument("todo read book", "deadline"));
    }

    @Test
    public void checkCommand_listCommandWithoutArgument_true() {
        assertTrue(CommandParser.checkCommandWithoutArgument("list", "list"));
    }

    @Test
    public void checkCommand_listCommandWithoutArgument_false() {
        assertFalse(CommandParser.checkCommandWithoutArgument("list", "todo"));
    }

    @Test
    public void checkCommand_caseSensitiveCommandWithoutArgument_true() {
        assertTrue(CommandParser.checkCommandWithoutArgument("LIST", "list"));
    }

    @Test
    public void checkCommand_caseSensitiveCommandWithoutArgument_false() {
        assertFalse(CommandParser.checkCommandWithoutArgument("LIST", "todo"));
    }

    @Test
    public void checkCommand_markCommandWithArgument_true() {
        assertTrue(CommandParser.checkCommandWithArgument("mark 1", "mark"));
    }

    @Test
    public void checkCommand_markCommandWithArgument_false() {
        assertFalse(CommandParser.checkCommandWithArgument("mark 1", "list"));
    }

    @Test
    public void checkCommand_unmarkCommandWithArgument_true() {
        assertTrue(CommandParser.checkCommandWithArgument("unmark 1", "unmark"));
    }

    @Test
    public void checkCommand_unmarkCommandWithArgument_false() {
        assertFalse(CommandParser.checkCommandWithArgument("unmark 1", "list"));
    }

    @Test
    public void checkCommand_deleteCommandWithArgument_true() {
        assertTrue(CommandParser.checkCommandWithArgument("delete 1", "delete"));
    }

    @Test
    public void checkCommand_deleteCommandWithArgument_false() {
        assertFalse(CommandParser.checkCommandWithArgument("delete 1", "list"));
    }
}
