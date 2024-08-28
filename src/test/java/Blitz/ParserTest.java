package blitz;

/* My import */
import command.CommandBye;
import command.CommandDeadline;
import command.CommandDelete;
import command.CommandEvent;
import command.CommandList;
import command.CommandMark;
import command.CommandTodo;
import command.CommandUnmark;

/* System import */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
            assertEquals(new CommandList("list"), Parser.parse("list"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandListStringWithAppendingWhiteSpace_returnCommandListObject() {
        try {
            assertEquals(new CommandList("list"), Parser.parse("list "));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandListStringWithFrontWhiteSpace_exceptionThrown() {
        try {
            assertEquals(new CommandList("list"), Parser.parse(" list"));
            fail();
        } catch (Exception e) {
            assertEquals("Command does not exist!", e.toString());
        }
    }

    @Test
    public void commandValidation_commandByeString_returnCommandByeObject() {
        try {
            assertEquals(new CommandBye("bye"), Parser.parse("bye"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandByeStringWithAppendingWhiteSpace_returnCommandByeObject() {
        try {
            assertEquals(new CommandBye("bye"), Parser.parse("bye "));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandByeStringWithFrontWhiteSpace_exceptionThrown() {
        try {
            assertEquals(new CommandBye("bye"), Parser.parse(" bye"));
            fail();
        } catch (Exception e) {
            assertEquals("Command does not exist!", e.toString());
        }
    }

    @Test
    public void commandValidation_commandMarkString_returnCommandMarkObject() {
        try {
            assertEquals(new CommandMark("mark 1", "1"), Parser.parse("mark 1"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandMarkStringNoParameter_exceptionThrown() {
        try {
            assertEquals(new CommandMark("mark", ""), Parser.parse("mark"));
            assertEquals(new CommandMark("mark ", ""), Parser.parse("mark "));
            fail();
        } catch (Exception e) {
            assertEquals("Missing parameter(s) for command!", e.toString());
        }
    }

    @Test
    public void commandValidation_commandMarkStringExcessParameter_exceptionThrown() {
        try {
            assertEquals(new CommandMark("mark 1 2", "1 2"), Parser.parse("mark 1 2"));
            fail();
        } catch (Exception e) {
            assertEquals("Only ONE parameter is required! Please use this format \"mark [Integer]\"!", e.toString());
        }
    }

    @Test
    public void commandValidation_commandUnmarkString_returnCommandUnmarkObject() {
        try {
            assertEquals(new CommandUnmark("unmark 1", "1"), Parser.parse("unmark 1"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandUnmarkStringNoParameter_exceptionThrown() {
        try {
            assertEquals(new CommandUnmark("unmark", ""), Parser.parse("unmark"));
            assertEquals(new CommandUnmark("unmark ", ""), Parser.parse("unmark "));
            fail();
        } catch (Exception e) {
            assertEquals("Missing parameter(s) for command!", e.toString());
        }
    }

    @Test
    public void commandValidation_commandunMarkStringExcessParameter_exceptionThrown() {
        try {
            assertEquals(new CommandUnmark("unmark 1 2", "1 2"), Parser.parse("unmark 1 2"));
            fail();
        } catch (Exception e) {
            assertEquals("Only ONE parameter is required! Please use this format \"unmark [Integer]\"!", e.toString());
        }
    }

    @Test
    public void commandValidation_commandTodoString_returnCommandTodoObject() {
        try {
            assertEquals(new CommandTodo("todo abc", "abc"), Parser.parse("todo abc"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandTodoStringNoParameter_exceptionThrown() {
        try {
            assertEquals(new CommandTodo("todo", ""), Parser.parse("todo"));
            assertEquals(new CommandTodo("todo ", ""), Parser.parse("todo "));
            fail();
        } catch (Exception e) {
            assertEquals("Missing parameter(s) for command!", e.toString());
        }
    }

    @Test
    public void commandValidation_commandDeadlineString_returnCommandDeadlineObject() {
        try {
            assertEquals(new CommandDeadline("deadline abc /by 2024-01-25 1900", new String[]{"abc", "2024-01-25 1900"}), Parser.parse("deadline abc /by 2024-01-25 1900"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandDeadlineStringNoParameter_exceptionThrown() {
        try {
            assertEquals(new CommandDeadline("deadline", new String[]{""}), Parser.parse("deadline"));
            assertEquals(new CommandDeadline("deadline ", new String[]{""}), Parser.parse("deadline "));
            fail();
        } catch (Exception e) {
            assertEquals("Missing parameter(s) for command!", e.toString());
        }
    }

    @Test
    public void commandValidation_commandDeadlineStringWrongParameterFormat_exceptionThrown() {
        try {
            assertEquals(new CommandDeadline("deadline abc /by ", new String[]{""}), Parser.parse("deadline abc /by "));
            assertEquals(new CommandDeadline("deadline /by 1920-12-12 1212", new String[]{""}), Parser.parse("deadline /by 1920-12-12 1212"));
            assertEquals(new CommandDeadline("deadline abc /by 1920-12-35 1212", new String[]{""}), Parser.parse("deadline abc /by 1920-12-35 1212"));
            assertEquals(new CommandDeadline("deadline abc /by ", new String[]{""}), Parser.parse("deadline abc /by abc"));
            assertEquals(new CommandDeadline("deadline /by /by /by", new String[]{""}), Parser.parse("deadline /by /by /by"));
            fail();
        } catch (Exception e) {
            assertEquals("Wrong parameter format! Please use this format \"deadline [Task name] /by [yyyy-mm-dd hhmm]\"!", e.toString());
        }
    }

    @Test
    public void commandValidation_commandEventString_returnCommandEventObject() {
        try {
            assertEquals(new CommandEvent("event abc /from 2024-01-25 1900 /to 2024-01-26 1900", new String[]{"abc", "2024-01-25 1900", "2024-01-26 1900"}), Parser.parse("event abc /from 2024-01-25 1900 /to 2024-01-26 1900"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandEventStringWrongParameterFormat_exceptionThrown() {
        try {
            assertEquals(new CommandEvent("event  /from /to ", new String[]{""}), Parser.parse("event  /from /to "));
            assertEquals(new CommandEvent("event abc /from /to ", new String[]{""}), Parser.parse("event abc /from /to "));
            assertEquals(new CommandEvent("event /from 2024-09-10 1900 /to 2024-10-09 2000", new String[]{""}), Parser.parse("event /from 2024-09-10 1900 /to 2024-10-09 2000"));
            assertEquals(new CommandEvent("event abc /from 2024-09-10 1900 /to ", new String[]{""}), Parser.parse("event abc /from 2024-09-10 1900 /to "));
            assertEquals(new CommandEvent("event /from /to 2024-10-09 2000", new String[]{""}), Parser.parse("event /from /to 2024-10-09 2000"));
            assertEquals(new CommandEvent("event abc /from /to 2024-10-09 2000", new String[]{""}), Parser.parse("event abc /from /to 2024-10-09 2000"));
            fail();
        } catch (Exception e) {
            assertEquals("Wrong parameter format! Please use this format \"event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]\"!", e.toString());
        }
    }

    @Test
    public void commandValidation_commandDeleteString_returnCommandDeleteObject() {
        try {
            assertEquals(new CommandDelete("delete 1", "1"), Parser.parse("delete 1"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void commandValidation_commandDeleteStringNoParameter_exceptionThrown() {
        try {
            assertEquals(new CommandDelete("delete", ""), Parser.parse("delete"));
            assertEquals(new CommandDelete("delete ", ""), Parser.parse("delete "));
            fail();
        } catch (Exception e) {
            assertEquals("Missing parameter(s) for command!", e.toString());
        }
    }
}
