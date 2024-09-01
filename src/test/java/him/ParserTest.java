package him;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exceptions.HimException;
import exceptions.InvalidDateTimeFormatException;
import exceptions.InvalidDeadlineFormatException;
import exceptions.InvalidDeleteFormatException;
import exceptions.InvalidEventFormatException;
import exceptions.InvalidTodoFormatException;
import exceptions.StartAfterEndException;


import command.Command;
import command.AddCommand;
import command.ListCommand;
import command.MarkCommand;
import command.DeleteCommand;
import command.UnknownCommand;

public class ParserTest {

    @Test
    public void parseUserInput_emptyInput_success() throws HimException {
        Command c = Parser.parseUserInput("");
        assertEquals(UnknownCommand.class, c.getClass());
    }

    @Test
    public void parseUserInput_invalidCommand_success() throws HimException {
        Command c = Parser.parseUserInput("a");
        assertEquals(UnknownCommand.class, c.getClass());
    }

    @Test
    public void parseUserInput_invalidFormatToDo_exceptionThrown() {
        try {
            Parser.parseUserInput("todo");
            fail();
        } catch (Exception e) {
            assertEquals(InvalidTodoFormatException.class, e.getClass());
        }
    }

    @Test
    public void parseUserInput_validToDo_success() throws HimException {
        Command c = Parser.parseUserInput("todo description");
        assertEquals(AddCommand.class, c.getClass());
    }

    @Test
    public void parseUserInput_deadlineWithoutDescription_exceptionThrown() {
        try {
            Parser.parseUserInput("deadline /by 2024-09-12");
            fail();
        } catch (Exception e) {
            assertEquals(InvalidDeadlineFormatException.class, e.getClass());
        }
    }

    @Test
    public void parseUserInput_deadlineWithoutDueDate_exceptionThrown() {
        try {
            Parser.parseUserInput("deadline description");
            fail();
        } catch (Exception e) {
            assertEquals(InvalidDeadlineFormatException.class, e.getClass());
        }
    }

    @Test
    public void parseUserInput_deadlineInvalidDateFormat_exceptionThrown() {
        try {
            Parser.parseUserInput("deadline description /by 2024-24-40");
            fail();
        } catch (Exception e) {
            assertEquals(InvalidDateTimeFormatException.class, e.getClass());
        }
    }

    @Test
    public void parseUserInput_validDeadline_success() throws HimException {
        Command c = Parser.parseUserInput("deadline description /by 2024-09-12");
        assertEquals(AddCommand.class, c.getClass());
    }

    @Test
    public void parseUserInput_eventNoDescription_exceptionThrown() {
        try {
            Parser.parseUserInput("event /start 2024-08-29 /at 18:00 /end 2024-08-29 /at 19:00");
            fail();
        } catch (Exception e) {
            assertEquals(InvalidEventFormatException.class, e.getClass());
        }
    }

    @Test
    public void parseUserInput_eventNoStart_exceptionThrown() {
        try {
            Parser.parseUserInput("event description /start /end 2024-08-29 /at 19:00");
            fail();
        } catch (Exception e) {
            assertEquals(InvalidEventFormatException.class, e.getClass());
        }
    }

    @Test
    public void parseUserInput_eventNoEnd_exceptionThrown() {
        try {
            Parser.parseUserInput("event description /start 2024-08-29 /at 18:00 /end ");
            fail();
        } catch (Exception e) {
            assertEquals(InvalidEventFormatException.class, e.getClass());
        }
    }

    @Test
    public void parseUserInput_eventNoTime_exceptionThrown() {
        try {
            Parser.parseUserInput("event description /start 2024-08-29 /end 2024-08-29");
            fail();
        } catch (Exception e) {
            assertEquals(InvalidEventFormatException.class, e.getClass());
        }
    }

    @Test
    public void parseUserInput_eventNoInterval_exceptionThrown() {
        try {
            Parser.parseUserInput("event description");
            fail();
        } catch (Exception e) {
            assertEquals(InvalidEventFormatException.class, e.getClass());
        }
    }

    @Test
    public void parseUserInput_invalidDateFormat_exceptionThrown() {
        try {
            Parser.parseUserInput("event description /start 2024/08/29 /at 18:00 /end 2024/08/29 /at 19:00");
            fail();
        } catch (Exception e) {
            assertEquals(InvalidDateTimeFormatException.class, e.getClass());
        }
    }

    @Test
    public void parseUserInput_invalidTimeFormat_exceptionThrown() {
        try {
            Parser.parseUserInput("event description /start 2024-08-29 /at 1800 /end 2024-08-29 /at 1900");
            fail();
        } catch (Exception e) {
            assertEquals(InvalidDateTimeFormatException.class, e.getClass());
        }
    }

    @Test
    public void parseUserInput_invalidInterval_exceptionThrown() {
        try {
            Parser.parseUserInput("event description /start 2024-08-29 /at 19:00 /end 2024-08-29 /at 18:00");
            fail();
        } catch (Exception e) {
            assertEquals(StartAfterEndException.class, e.getClass());
        }
    }

    @Test
    public void parseUserInput_validEvent_success() throws HimException {
        Command c = Parser.parseUserInput("event description /start 2024-08-29 /at 18:00 /end 2024-08-29 /at 19:00");
        assertEquals(AddCommand.class, c.getClass());
    }

    @Test
    public void parseUserInput_list_success() throws HimException {
        Command c = Parser.parseUserInput("list");
        assertEquals(ListCommand.class, c.getClass());
    }

    @Test
    public void parseUserInput_mark_success() throws HimException {
        Command c = Parser.parseUserInput("mark 1");
        assertEquals(MarkCommand.class, c.getClass());
    }

    @Test
    public void parseUserInput_invalidDelete_exceptionThrown() {
        try {
            Parser.parseUserInput("delete");
            fail();
        } catch (Exception e) {
            assertEquals(InvalidDeleteFormatException.class, e.getClass());
        }
    }

    @Test
    public void parseUserInput_delete_success() throws HimException {
        Command c = Parser.parseUserInput("delete 1");
        assertEquals(DeleteCommand.class, c.getClass());
    }
}