package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.HelpCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UnmarkCommand;
import exceptions.DownyException;
import exceptions.InvalidCommandException;
import exceptions.InvalidFormatException;
import exceptions.MissingArgumentException;

public class ParserTest {

    @Test
    void testParseByeCommand() throws DownyException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ByeCommand);
    }

    @Test
    void testParseListCommand() throws DownyException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    void testParseMarkCommand() throws DownyException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
        assertEquals("1", ((MarkCommand) command).getTaskNumber());
    }

    @Test
    void testParseMarkCommandWithoutTaskNumber() {
        Exception exception = assertThrows(MissingArgumentException.class, () -> {
            Parser.parse("mark");
        });
        assertEquals("Mark command requires a task number.\n   Usage: mark <taskNumber>", exception.getMessage());
    }

    @Test
    void testParseUnmarkCommand() throws DownyException {
        Command command = Parser.parse("unmark 1");
        assertTrue(command instanceof UnmarkCommand);
        assertEquals("1", ((UnmarkCommand) command).getTaskNumber());
    }

    @Test
    void testParseUnmarkCommandWithoutTaskNumber() {
        Exception exception = assertThrows(MissingArgumentException.class, () -> {
            Parser.parse("unmark");
        });
        assertEquals("Unmark command requires a task number.\n   Usage: unmark <taskNumber>", exception.getMessage());
    }

    @Test
    void testParseDeleteCommand() throws DownyException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
        assertEquals("1", ((DeleteCommand) command).getTaskNumber());
    }

    @Test
    void testParseDeleteCommandWithoutTaskNumber() {
        Exception exception = assertThrows(MissingArgumentException.class, () -> {
            Parser.parse("delete");
        });
        assertEquals("Delete command requires a task number.\n   Usage: delete <taskNumber>", exception.getMessage());
    }

    @Test
    void testParseTodoCommand() throws DownyException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof TodoCommand);
        assertEquals("read book", ((TodoCommand) command).getTaskDescription());
    }

    @Test
    void testParseTodoCommandWithoutDescription() {
        Exception exception = assertThrows(MissingArgumentException.class, () -> {
            Parser.parse("todo");
        });
        assertEquals("Todo command requires a task description.\n   todo <taskDescription>", exception.getMessage());
    }

    @Test
    void testParseDeadlineCommand() throws DownyException {
        Command command = Parser.parse("deadline submit report /by 2024/08/30 1800");
        assertTrue(command instanceof DeadlineCommand);
        assertEquals("submit report", ((DeadlineCommand) command).getTaskDescription());
        assertEquals("2024-08-30T18:00", ((DeadlineCommand) command).getDueDate().toString());
    }

    @Test
    void testParseDeadlineCommandWithoutDueDate() {
        Exception exception = assertThrows(InvalidFormatException.class, () -> {
            Parser.parse("deadline submit report");
        });
        assertEquals("Deadline command must follow the format: <task> /by <dueDate>.", exception.getMessage());
    }

    @Test
    void testParseDeadlineCommandWithInvalidDate() {
        Exception exception = assertThrows(InvalidFormatException.class, () -> {
            Parser.parse("deadline submit report /by 2024/13/30 1800");
        });
        assertEquals("Invalid date. Please check the year, month, and day values.", exception.getMessage());
    }

    @Test
    void testParseEventCommand() throws DownyException {
        Command command = Parser.parse("event team meeting /from 2024/08/30 0900 /to 2024/08/30 1100");
        assertTrue(command instanceof EventCommand);
        assertEquals("team meeting", ((EventCommand) command).getTaskDescription());
        assertEquals("2024-08-30T09:00", ((EventCommand) command).getStartTime().toString());
        assertEquals("2024-08-30T11:00", ((EventCommand) command).getEndTime().toString());
    }

    @Test
    void testParseEventCommandWithoutEndTime() {
        Exception exception = assertThrows(InvalidFormatException.class, () -> {
            Parser.parse("event team meeting /from 2024/08/30 0900");
        });
        assertEquals("Event command must contain the '/to' keyword with a valid end time.", exception.getMessage());
    }

    @Test
    void testParseEventCommandWithInvalidStartTime() {
        Exception exception = assertThrows(InvalidFormatException.class, () -> {
            Parser.parse("event team meeting /from 2024/08/30 2500 /to 2024/08/30 1100");
        });
        assertEquals("Invalid time. Please check the hour and minute values.", exception.getMessage());
    }

    @Test
    void testParseEventCommandWithStartAfterEndTime() {
        Exception exception = assertThrows(InvalidFormatException.class, () -> {
            Parser.parse("event team meeting /from 2024/08/30 1100 /to 2024/08/30 0900");
        });
        assertEquals("Start time must be before end time.", exception.getMessage());
    }

    @Test
    void testParseHelpCommand() throws DownyException {
        Command command = Parser.parse("help");
        assertTrue(command instanceof HelpCommand);
    }

    @Test
    void testParseInvalidCommand() {
        Exception exception = assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("unknowncommand");
        });
        assertEquals("Invalid command entered", exception.getMessage());
    }
}
