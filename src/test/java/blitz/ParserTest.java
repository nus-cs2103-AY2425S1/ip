package blitz;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

public class ParserTest {
    @Test
    public void commandValidation_nonExistentCommand_exceptionThrown() {
        try {
            Parser.parse("a");
            Parser.parse("0");
            Parser.parse(" ");
            fail();
        } catch (Exception e) {
            assertEquals("Command does not exist!", e.toString());
        }
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
        try {
            assertEquals(new ListCommand("list"), Parser.parse(" list"));
            fail();
        } catch (Exception e) {
            assertEquals("Command does not exist!", e.toString());
        }
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
        try {
            assertEquals(new ByeCommand("bye"), Parser.parse(" bye"));
            fail();
        } catch (Exception e) {
            assertEquals("Command does not exist!", e.toString());
        }
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
        try {
            assertEquals(new MarkCommand("mark", ""), Parser.parse("mark"));
            assertEquals(new MarkCommand("mark ", ""), Parser.parse("mark "));
            fail();
        } catch (Exception e) {
            assertEquals("Missing parameter(s) for command!", e.toString());
        }
    }

    @Test
    public void commandValidation_commandMarkStringExcessParameter_exceptionThrown() {
        try {
            assertEquals(new MarkCommand("mark 1 2", "1 2"), Parser.parse("mark 1 2"));
            fail();
        } catch (Exception e) {
            assertEquals("Only ONE parameter is required! Please use this format \"mark [Integer]\"!", e.toString());
        }
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
        try {
            assertEquals(new UnmarkCommand("unmark", ""), Parser.parse("unmark"));
            assertEquals(new UnmarkCommand("unmark ", ""), Parser.parse("unmark "));
            fail();
        } catch (Exception e) {
            assertEquals("Missing parameter(s) for command!", e.toString());
        }
    }

    @Test
    public void commandValidation_commandunMarkStringExcessParameter_exceptionThrown() {
        try {
            assertEquals(new UnmarkCommand("unmark 1 2", "1 2"), Parser.parse("unmark 1 2"));
            fail();
        } catch (Exception e) {
            assertEquals("Only ONE parameter is required! Please use this format \"unmark [Integer]\"!", e.toString());
        }
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
        try {
            assertEquals(new TodoCommand("todo", ""), Parser.parse("todo"));
            assertEquals(new TodoCommand("todo ", ""), Parser.parse("todo "));
            fail();
        } catch (Exception e) {
            assertEquals("Missing parameter(s) for command!", e.toString());
        }
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
        try {
            assertEquals(new DeadlineCommand("deadline", "", ""), Parser.parse("deadline"));
            assertEquals(new DeadlineCommand("deadline ", "", ""), Parser.parse("deadline "));
            fail();
        } catch (Exception e) {
            assertEquals("Missing parameter(s) for command!", e.toString());
        }
    }

    @Test
    public void commandValidation_commandDeadlineStringWrongParameterFormat_exceptionThrown() {
        try {
            assertEquals(new DeadlineCommand("deadline abc /by ", "", ""),
                    Parser.parse("deadline abc /by "));
            assertEquals(new DeadlineCommand("deadline /by 1920-12-12 1212", "", ""),
                    Parser.parse("deadline /by 1920-12-12 1212"));
            assertEquals(new DeadlineCommand("deadline abc /by 1920-12-35 1212", "", ""),
                    Parser.parse("deadline abc /by 1920-12-35 1212"));
            assertEquals(new DeadlineCommand("deadline abc /by ", "", ""),
                    Parser.parse("deadline abc /by abc"));
            assertEquals(new DeadlineCommand("deadline /by /by /by", "", ""),
                    Parser.parse("deadline /by /by /by"));
            fail();
        } catch (Exception e) {
            assertEquals(
                    "Wrong parameter format! Please use this format \"deadline [Task name] /by [yyyy-mm-dd hhmm]\"!",
                    e.toString());
        }
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
        try {
            assertEquals(new EventCommand("event  /from /to ", "", "", ""),
                    Parser.parse("event  /from /to "));
            assertEquals(new EventCommand("event abc /from /to ", "", "", ""),
                    Parser.parse("event abc /from /to "));
            assertEquals(new EventCommand("event /from 2024-09-10 1900 /to 2024-10-09 2000", "", "", ""),
                    Parser.parse("event /from 2024-09-10 1900 /to 2024-10-09 2000"));
            assertEquals(new EventCommand("event abc /from 2024-09-10 1900 /to ", "", "", ""),
                    Parser.parse("event abc /from 2024-09-10 1900 /to "));
            assertEquals(new EventCommand("event /from /to 2024-10-09 2000", "", "", ""),
                    Parser.parse("event /from /to 2024-10-09 2000"));
            assertEquals(new EventCommand("event abc /from /to 2024-10-09 2000", "", "", ""),
                    Parser.parse("event abc /from /to 2024-10-09 2000"));
            fail();
        } catch (Exception e) {
            assertEquals(
                    "Wrong parameter format! Please use this format "
                            + "\"event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]\"!",
                    e.toString());
        }
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
        try {
            assertEquals(new DeleteCommand("delete", ""), Parser.parse("delete"));
            assertEquals(new DeleteCommand("delete ", ""), Parser.parse("delete "));
            fail();
        } catch (Exception e) {
            assertEquals("Missing parameter(s) for command!", e.toString());
        }
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
        try {
            assertEquals(new FindCommand("find", ""), Parser.parse("find"));
            assertEquals(new FindCommand("find ", ""), Parser.parse("find "));
            fail();
        } catch (Exception e) {
            assertEquals("Missing parameter(s) for command!", e.toString());
        }
    }
}
