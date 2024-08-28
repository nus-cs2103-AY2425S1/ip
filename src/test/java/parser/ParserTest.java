package parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import commands.*;
import exceptions.*;

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
        assertEquals("Mark command requires a task number.\n   Usage: mark <taskNumber> ", exception.getMessage());
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
        assertEquals("Unmark command requires a task number.\n   Usage: unmark <taskNumber> ", exception.getMessage());
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
    void testParseEventCommand() throws DownyException {
        Command command = Parser.parse("event team meeting /from 2024/08/30 0900 /to 2024/08/30 1100");
        assertTrue(command instanceof EventCommand);
        assertEquals("team meeting", ((EventCommand) command).getTaskDescription());
        assertEquals("2024-08-30T09:00", ((EventCommand) command).getStartTime().toString());
        assertEquals("2024-08-30T11:00", ((EventCommand) command).getEndTime().toString());
    }

    @Test
    void testParseEventCommandWithoutStartOrEndTime() {
        Exception exception = assertThrows(InvalidFormatException.class, () -> {
            Parser.parse("event team meeting /from 2024/08/30 0900");
        });
        assertEquals("Event command must follow the format: <task> /from <startTime> /to <endTime>.", exception.getMessage());
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
        assertEquals("Invalid command entered", exception.getMessage()); // Assuming this is the message in InvalidCommandException
    }
}
