package blitz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import command.ByeCommand;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import exception.BlitzException;

public class ParserTest {
    @Test
    public void commandValidation_nonExistentCommand_exceptionThrown() {
        BlitzException exception1 = assertThrows(BlitzException.class, () -> Parser.parse("a"));
        assertEquals("Command does not exist!", exception1.toString());

        BlitzException exception2 = assertThrows(BlitzException.class, () -> Parser.parse("0"));
        assertEquals("Command does not exist!", exception2.toString());

        BlitzException exception3 = assertThrows(BlitzException.class, () -> Parser.parse(" "));
        assertEquals("Command does not exist!", exception3.toString());
    }

    @Test
    public void commandValidation_commandListString_returnCommandListObject() {
        try {
            assertEquals(new ListCommand("list"), Parser.parse("list"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandListStringWithAppendingWhiteSpace_returnCommandListObject() {
        try {
            assertEquals(new ListCommand("list"), Parser.parse("list "));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandListStringWithFrontWhiteSpace_exceptionThrown() {
        BlitzException exception1 = assertThrows(BlitzException.class, () -> Parser.parse(" list"));
        assertEquals("Command does not exist!", exception1.toString());
    }

    @Test
    public void commandValidation_commandByeString_returnCommandByeObject() {
        try {
            assertEquals(new ByeCommand("bye"), Parser.parse("bye"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandByeStringWithAppendingWhiteSpace_returnCommandByeObject() {
        try {
            assertEquals(new ByeCommand("bye"), Parser.parse("bye "));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandByeStringWithFrontWhiteSpace_exceptionThrown() {
        BlitzException exception1 = assertThrows(BlitzException.class, () -> Parser.parse(" bye"));
        assertEquals("Command does not exist!", exception1.toString());
    }

    @Test
    public void commandValidation_commandMarkString_returnCommandMarkObject() {
        try {
            assertEquals(new MarkCommand("mark 1", "1"), Parser.parse("mark 1"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandMarkStringNoParameter_exceptionThrown() {
        BlitzException exception1 = assertThrows(BlitzException.class, () -> Parser.parse("mark"));
        assertEquals("Missing parameter(s) for command!", exception1.toString());

        BlitzException exception2 = assertThrows(BlitzException.class, () -> Parser.parse("mark "));
        assertEquals("Missing parameter(s) for command!", exception2.toString());
    }

    @Test
    public void commandValidation_commandMarkStringExcessParameter_exceptionThrown() {
        BlitzException exception1 = assertThrows(BlitzException.class, () -> Parser.parse("mark 1 2"));
        assertEquals("Only ONE parameter is required! Please use this format \"mark [Index]\"!",
                exception1.toString());
    }

    @Test
    public void commandValidation_commandUnmarkString_returnCommandUnmarkObject() {
        try {
            assertEquals(new UnmarkCommand("unmark 1", "1"), Parser.parse("unmark 1"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandUnmarkStringNoParameter_exceptionThrown() {
        BlitzException exception1 = assertThrows(BlitzException.class, () -> Parser.parse("unmark"));
        assertEquals("Missing parameter(s) for command!", exception1.toString());

        BlitzException exception2 = assertThrows(BlitzException.class, () -> Parser.parse("unmark "));
        assertEquals("Missing parameter(s) for command!", exception2.toString());
    }

    @Test
    public void commandValidation_commandUnmarkStringExcessParameter_exceptionThrown() {
        BlitzException exception1 = assertThrows(BlitzException.class, () -> Parser.parse("unmark 1 2"));
        assertEquals("Only ONE parameter is required! Please use this format \"unmark [Index]\"!",
                exception1.toString());
    }

    @Test
    public void commandValidation_commandTodoString_returnCommandTodoObject() {
        try {
            assertEquals(new TodoCommand("todo abc", "abc"), Parser.parse("todo abc"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandTodoStringNoParameter_exceptionThrown() {
        BlitzException exception1 = assertThrows(BlitzException.class, () -> Parser.parse("todo"));
        assertEquals("Missing parameter(s) for command!", exception1.toString());

        BlitzException exception2 = assertThrows(BlitzException.class, () -> Parser.parse("todo "));
        assertEquals("Missing parameter(s) for command!", exception2.toString());
    }

    @Test
    public void commandValidation_commandDeadlineString_returnCommandDeadlineObject() {
        try {
            assertEquals(
                    new DeadlineCommand("deadline abc /by 2024-01-25 1900", "abc", "2024-01-25 1900"),
                    Parser.parse("deadline abc /by 2024-01-25 1900"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandDeadlineStringNoParameter_exceptionThrown() {
        BlitzException exception1 = assertThrows(BlitzException.class, () -> Parser.parse("deadline"));
        assertEquals("Missing parameter(s) for command!", exception1.toString());

        BlitzException exception2 = assertThrows(BlitzException.class, () -> Parser.parse("deadline "));
        assertEquals("Missing parameter(s) for command!", exception2.toString());
    }

    @Test
    public void commandValidation_commandDeadlineStringWrongParameterFormat_exceptionThrown() {
        BlitzException exception1 = assertThrows(BlitzException.class, () ->
                Parser.parse("deadline abc /by "));
        assertEquals("Wrong parameter format! Please use this format \"deadline [Task description] "
                + "/by [yyyy-mm-dd hhmm]\"!", exception1.toString());

        BlitzException exception2 = assertThrows(BlitzException.class, () ->
                Parser.parse("deadline /by 1920-12-2 1212"));
        assertEquals("Wrong parameter format! Please use this format \"deadline [Task description] "
                + "/by [yyyy-mm-dd hhmm]\"!", exception2.toString());

        BlitzException exception3 = assertThrows(BlitzException.class, () ->
                Parser.parse("deadline abc /by 1920-12-35 1212"));
        assertEquals("Wrong parameter format! Please use this format \"deadline [Task description] "
                + "/by [yyyy-mm-dd hhmm]\"!", exception3.toString());

        BlitzException exception4 = assertThrows(BlitzException.class, () ->
                Parser.parse("deadline abc /by abc"));
        assertEquals("Wrong parameter format! Please use this format \"deadline [Task description] "
                + "/by [yyyy-mm-dd hhmm]\"!", exception4.toString());

        BlitzException exception5 = assertThrows(BlitzException.class, () ->
                Parser.parse("deadline /by /by /by"));
        assertEquals("Wrong parameter format! Please use this format \"deadline [Task description] "
                        + "/by [yyyy-mm-dd hhmm]\"!", exception5.toString());
    }

    @Test
    public void commandValidation_commandEventString_returnCommandEventObject() {
        try {
            assertEquals(new EventCommand("event abc /from 2024-01-25 1900 /to 2024-01-26 1900",
                            "abc", "2024-01-25 1900", "2024-01-26 1900"),
                    Parser.parse("event abc /from 2024-01-25 1900 /to 2024-01-26 1900"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandEventStringWrongParameterFormat_exceptionThrown() {
        BlitzException exception1 = assertThrows(BlitzException.class, () ->
                Parser.parse("event  /from /to "));
        assertEquals("Wrong parameter format! Please use this format "
                        + "\"event [Task description] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]\"!",
                exception1.toString());

        BlitzException exception2 = assertThrows(BlitzException.class, () ->
                Parser.parse("event abc /from /to "));
        assertEquals("Wrong parameter format! Please use this format "
                        + "\"event [Task description] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]\"!",
                exception2.toString());

        BlitzException exception3 = assertThrows(BlitzException.class, () ->
                Parser.parse("event /from 2024-09-1 1900 /to 2024-10-09 2000"));
        assertEquals("Wrong parameter format! Please use this format "
                        + "\"event [Task description] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]\"!",
                exception3.toString());

        BlitzException exception4 = assertThrows(BlitzException.class, () ->
                Parser.parse("event abc /from 2024-09-10 1900 /to "));
        assertEquals("Wrong parameter format! Please use this format "
                        + "\"event [Task description] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]\"!",
                exception4.toString());

        BlitzException exception5 = assertThrows(BlitzException.class, () ->
                Parser.parse("event /from /to 2024-10-09 2000"));
        assertEquals("Wrong parameter format! Please use this format "
                        + "\"event [Task description] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]\"!",
                exception5.toString());

        BlitzException exception6 = assertThrows(BlitzException.class, () ->
                Parser.parse("event abc /from /to 2024-10-09 2000"));
        assertEquals("Wrong parameter format! Please use this format "
                        + "\"event [Task description] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]\"!",
                exception6.toString());
    }

    @Test
    public void commandValidation_commandDeleteString_returnCommandDeleteObject() {
        try {
            assertEquals(new DeleteCommand("delete 1", "1"), Parser.parse("delete 1"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandDeleteStringNoParameter_exceptionThrown() {
        BlitzException exception1 = assertThrows(BlitzException.class, () -> Parser.parse("delete"));
        assertEquals("Missing parameter(s) for command!", exception1.toString());

        BlitzException exception2 = assertThrows(BlitzException.class, () -> Parser.parse("delete "));
        assertEquals("Missing parameter(s) for command!", exception2.toString());
    }

    @Test
    public void commandValidation_commandDeleteStringExcessParameter_exceptionThrown() {
        BlitzException exception1 = assertThrows(BlitzException.class, () -> Parser.parse("delete 1 2"));
        assertEquals("Only ONE parameter is required! Please use this format \"delete [Index]\"!",
                exception1.toString());
    }

    @Test
    public void commandValidation_commandFindString_returnCommandFindObject() {
        try {
            assertEquals(new FindCommand("find abc", "abc"), Parser.parse("find abc"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandFindStringNoParameter_exceptionThrown() {
        BlitzException exception1 = assertThrows(BlitzException.class, () -> Parser.parse("find"));
        assertEquals("Missing parameter(s) for command!", exception1.toString());

        BlitzException exception2 = assertThrows(BlitzException.class, () -> Parser.parse("find "));
        assertEquals("Missing parameter(s) for command!", exception2.toString());
    }
}
