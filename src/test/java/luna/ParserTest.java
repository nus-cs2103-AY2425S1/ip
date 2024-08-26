package luna;

import luna.command.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_emptyInput_exceptionThrown(){
        try {
            assertEquals(new ListCommand(), Parser.parse(""));
            fail();
        } catch (LunaException e) {
            assertEquals("""
                    Please enter one of the following commands:
                    "todo", "deadline", "event"
                    "mark", "unmark", "delete"
                    "list", "bye"
                    """, e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommand_exceptionThrown(){
        try {
            assertEquals(new ListCommand(), Parser.parse("create event"));
            fail();
        } catch (LunaException e) {
            assertEquals("""
                    Invalid Command!
                    Please enter one of the following commands:
                    "todo", "deadline", "event"
                    "mark", "unmark", "delete"
                    "list", "bye"
                    """, e.getMessage());
        }
    }

    @Test
    public void parse_markCommandWithNonIntegerInput_exceptionThrown(){
        try {
            assertEquals(new ListCommand(), Parser.parse("mark task 1"));
            fail();
        } catch (LunaException e) {
            assertEquals("Invalid task reference. Use integers only.", e.getMessage());
        }
    }

    @Test
    public void parse_markCommandWithoutTaskNumber_exceptionThrown(){
        try {
            assertEquals(new ListCommand(), Parser.parse("mark"));
            fail();
        } catch (LunaException e) {
            assertEquals("Indicate the task number to mark as done e.g. mark 2", e.getMessage());
        }
    }

    @Test
    public void parse_unmarkCommandWithNonIntegerInput_exceptionThrown(){
        try {
            assertEquals(new ListCommand(), Parser.parse("unmark task 1"));
            fail();
        } catch (LunaException e) {
            assertEquals("Invalid task reference. Use integers only.", e.getMessage());
        }
    }

    @Test
    public void parse_unmarkCommandWithoutTaskNumber_exceptionThrown(){
        try {
            assertEquals(new ListCommand(), Parser.parse("unmark"));
            fail();
        } catch (LunaException e) {
            assertEquals("Indicate the task number to unmark e.g. unmark 2", e.getMessage());
        }
    }

    @Test
    public void parse_deleteCommandWithNonIntegerInput_exceptionThrown(){
        try {
            assertEquals(new ListCommand(), Parser.parse("delete task 1"));
            fail();
        } catch (LunaException e) {
            assertEquals("Invalid task reference. Use integers only.", e.getMessage());
        }
    }

    @Test
    public void parse_deleteCommandWithoutTaskNumber_exceptionThrown(){
        try {
            assertEquals(new ListCommand(), Parser.parse("delete"));
            fail();
        } catch (LunaException e) {
            assertEquals("Indicate the task number to delete e.g. delete 2", e.getMessage());
        }
    }

    @Test
    public void parse_todoCommandWithoutDescription_exceptionThrown(){
        try {
            assertEquals(new ListCommand(), Parser.parse("todo  "));
            fail();
        } catch (LunaException e) {
            assertEquals("Enter description for todo e.g. todo [description]", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineCommandWithoutDescription_exceptionThrown(){
        try {
            assertEquals(new ListCommand(), Parser.parse("deadline  "));
            fail();
        } catch (LunaException e) {
            assertEquals("Enter description for deadline e.g. deadline return book /by Sunday",
                    e.getMessage());
        }
    }

    @Test
    public void parse_deadlineCommandWithoutDeadline_exceptionThrown(){
        try {
            assertEquals(new ListCommand(), Parser.parse("deadline return book "));
            fail();
        } catch (LunaException e) {
            assertEquals("Enter deadline for task e.g. deadline [task] /by [deadline]",
                    e.getMessage());
        }
    }

    @Test
    public void parse_deadlineCommandWithInvalidDeadlineFormat_exceptionThrown(){
        try {
            assertEquals(new ListCommand(), Parser.parse("deadline return book /by Sunday"));
            fail();
        } catch (LunaException e) {
            assertEquals("Enter deadline using format: dd/MM/yyyy HH:mm. eg. 14/02/2024 14:30",
                    e.getMessage());
        }
    }

    @Test
    public void parse_eventCommandWithoutDescription_exceptionThrown(){
        try {
            assertEquals(new ListCommand(), Parser.parse("event  "));
            fail();
        } catch (LunaException e) {
            assertEquals("Enter description for event e.g. event project meeting /from Mon 2pm /to 4pm",
                    e.getMessage());
        }
    }

    @Test
    public void parse_eventCommandWithoutStartTime_exceptionThrown(){
        try {
            assertEquals(new ListCommand(), Parser.parse("event Meeting /to 12/12/2025 13:00"));
        } catch (LunaException e) {
            assertEquals("Enter start and end time for event e.g. event [task] " +
                    "/from [startTime] /to [endTime]", e.getMessage());
        }
    }

    @Test
    public void parse_eventCommandStartAfterEnd_exceptionThrown(){
        try {
            assertEquals(new ListCommand(), Parser.parse("event Meeting /from 12/12/2025 14:00 " +
                    "/to 12/12/2025 13:00"));
        } catch (LunaException e) {
            assertEquals("Invalid Event: Start is after End", e.getMessage());
        }
    }

    @Test
    public void parse_eventCommandInvalidTimeFormat_exceptionThrown(){
        try {
            assertEquals(new ListCommand(), Parser.parse("event Meeting /from 12/12/2024 2pm " +
                    "/to 12/12/2025 13:00"));
        } catch (LunaException e) {
            assertEquals("Enter start and end time using format: dd/MM/yyyy HH:mm. eg. 14/02/2024 14:30", e.getMessage());
        }
    }


}
