package luna;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import luna.command.ListCommand;

public class ParserTest {

    @Test
    public void parse_emptyInput_exceptionThrown() {
        try {
            assertEquals(new ListCommand(null), Parser.parse("", null));
            fail();
        } catch (LunaException e) {
            assertEquals("""
                   Please enter one of the following commands:
                   "todo", "deadline", "event" - add task of specified type to list
                   "mark" - mark a task as completed
                   "unmark" - unmark a task as not completed
                   "delete" - remove task from current tasks
                   "list" - show all tasks
                   "find" - search for task given description
                   "undo" - undo most recent command
                   "bye" - close chatbot
                   """, e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            assertEquals(new ListCommand(null), Parser.parse("create event", null));
            fail();
        } catch (LunaException e) {
            assertEquals("""
                    Invalid Command!
                    Please enter one of the following commands:
                    "todo", "deadline", "event" - add task of specified type to list
                    "mark" - mark a task as completed
                    "unmark" - unmark a task as not completed
                    "delete" - remove task from current tasks
                    "list" - show all tasks
                    "find" - search for task given description
                    "undo" - undo most recent command
                    "bye" - close chatbot
                    """, e.getMessage());
        }
    }

    @Test
    public void parse_markCommandWithNonIntegerInput_exceptionThrown() {
        try {
            assertEquals(new ListCommand(null), Parser.parse("mark task 1", null));
            fail();
        } catch (LunaException e) {
            assertEquals("Invalid task reference. Use integers only.", e.getMessage());
        }
    }

    @Test
    public void parse_markCommandWithoutTaskNumber_exceptionThrown() {
        try {
            assertEquals(new ListCommand(null), Parser.parse("mark", null));
            fail();
        } catch (LunaException e) {
            assertEquals("Indicate the task number to mark as done\n"
                    + "e.g. mark 2", e.getMessage());
        }
    }

    @Test
    public void parse_unmarkCommandWithNonIntegerInput_exceptionThrown() {
        try {
            assertEquals(new ListCommand(null), Parser.parse("unmark task 1", null));
            fail();
        } catch (LunaException e) {
            assertEquals("Invalid task reference. Use integers only.", e.getMessage());
        }
    }

    @Test
    public void parse_unmarkCommandWithoutTaskNumber_exceptionThrown() {
        try {
            assertEquals(new ListCommand(null), Parser.parse("unmark", null));
            fail();
        } catch (LunaException e) {
            assertEquals("Indicate the task number to unmark\n"
                    + "e.g. unmark 2", e.getMessage());
        }
    }

    @Test
    public void parse_deleteCommandWithNonIntegerInput_exceptionThrown() {
        try {
            assertEquals(new ListCommand(null), Parser.parse("delete task 1", null));
            fail();
        } catch (LunaException e) {
            assertEquals("Invalid task reference. Use integers only.", e.getMessage());
        }
    }

    @Test
    public void parse_deleteCommandWithoutTaskNumber_exceptionThrown() {
        try {
            assertEquals(new ListCommand(null), Parser.parse("delete", null));
            fail();
        } catch (LunaException e) {
            assertEquals("Indicate the task number to delete\n"
                    + "e.g. delete 2", e.getMessage());
        }
    }

    @Test
    public void parse_todoCommandWithoutDescription_exceptionThrown() {
        try {
            assertEquals(new ListCommand(null), Parser.parse("todo  ", null));
            fail();
        } catch (LunaException e) {
            assertEquals("Enter description for todo\n"
                    + "e.g. todo [description]", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineCommandWithoutDescription_exceptionThrown() {
        try {
            assertEquals(new ListCommand(null), Parser.parse("deadline  ", null));
            fail();
        } catch (LunaException e) {
            assertEquals("Enter description for deadline\n"
                    + "e.g. deadline return book /by 12/12/2024 12:00", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineCommandWithoutDeadline_exceptionThrown() {
        try {
            assertEquals(new ListCommand(null), Parser.parse("deadline return book ", null));
            fail();
        } catch (LunaException e) {
            assertEquals("Enter deadline for task\n"
                    + "e.g. deadline [task] /by [deadline]", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineCommandWithInvalidDeadlineFormat_exceptionThrown() {
        try {
            assertEquals(new ListCommand(null), Parser.parse("deadline return book /by Sunday", null));
            fail();
        } catch (LunaException e) {
            assertEquals("Enter deadline using format: [dd/MM/yyyy HH:mm]\n"
                    + "e.g. 14/02/2024 14:30", e.getMessage());
        }
    }

    @Test
    public void parse_eventCommandWithoutDescription_exceptionThrown() {
        try {
            assertEquals(new ListCommand(null), Parser.parse("event  ", null));
            fail();
        } catch (LunaException e) {
            assertEquals("Enter description for event\n"
                    + "e.g. event project meeting /from 12/12/2024 12:00 /to 12/12/2024 16:00",
                    e.getMessage());
        }
    }

    @Test
    public void parse_eventCommandWithoutStartTime_exceptionThrown() {
        try {
            assertEquals(new ListCommand(null), Parser.parse("event Meeting /to 12/12/2025 13:00", null));
        } catch (LunaException e) {
            assertEquals("Enter start and end time for event\n"
                    + "e.g. event [task] /from [startTime] /to [endTime]", e.getMessage());
        }
    }

    @Test
    public void parse_eventCommandStartAfterEnd_exceptionThrown() {
        try {
            assertEquals(new ListCommand(null), Parser.parse("event Meeting /from 12/12/2025 14:00 "
                    + "/to 12/12/2025 13:00", null));
        } catch (LunaException e) {
            assertEquals("Invalid Event: Start is after End", e.getMessage());
        }
    }

    @Test
    public void parse_eventCommandInvalidTimeFormat_exceptionThrown() {
        try {
            assertEquals(new ListCommand(null), Parser.parse("event Meeting /from 12/12/2024 2pm "
                    + "/to 12/12/2025 13:00", null));
        } catch (LunaException e) {
            assertEquals("Enter start and end time using format: [dd/MM/yyyy HH:mm]\n"
                    + "eg. 14/02/2024 14:30", e.getMessage());
        }
    }


}
