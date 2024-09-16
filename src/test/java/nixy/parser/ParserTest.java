package nixy.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import nixy.command.AddTaskCommand;
import nixy.command.ByeCommand;
import nixy.command.Command;
import nixy.command.CommandType;
import nixy.command.DeleteCommand;
import nixy.command.InvalidCommand;
import nixy.command.ListCommand;
import nixy.command.MarkCommand;
import nixy.exceptions.NixyException;
import nixy.task.DeadlineTask;
import nixy.task.EventTask;
import nixy.task.TodoTask;


class ParserTest {
    private final Parser parser = new Parser(null, null, null, null);

    @Test
    void parse_byeCommand_commandByeReturned() {
        Command result = parser.parse("bye");
        assertTrue(result instanceof ByeCommand);
    }

    @Test
    void parse_listCommand_commandListReturned() {
        Command result = parser.parse("list");
        assertTrue(result instanceof ListCommand);
    }

    @Test
    void parse_markCommandWithValidTaskNumber_commandMarkAndTaskNumberReturned() {
        Command result = parser.parse("mark 1");
        assertTrue(result instanceof MarkCommand);
        assertEquals(CommandType.MARK, result.getType());
        MarkCommand markCommand = (MarkCommand) result;
        assertEquals(1, markCommand.getTaskNumber());
    }

    @Test
    void parse_markCommandWithInvalidNumberFormat_throwsNixyException() {
        Exception exception = assertThrows(NixyException.class, () -> parser.parse("mark one"));
        assertEquals("BLAHH!!! The task number to mark as done must be an integer.", exception.getMessage());
    }

    @Test
    void parse_markCommandWithoutTaskNumber_throwsNixyException() {
        Exception exception = assertThrows(NixyException.class, () -> parser.parse("mark"));
        assertEquals("BLAHH!!! The task number to mark as done cannot be empty.", exception.getMessage());
    }

    @Test
    void parse_markCommandWithNegativeTaskNumber_throwsNixyException() {
        Exception exception = assertThrows(NixyException.class, () -> parser.parse("mark -1"));
        assertEquals("BLAHH!!! The task number to mark as done must be a positive integer.", exception.getMessage());
    }

    @Test
    void parse_unmarkCommandWithValidTaskNumber_commandUnmarkAndTaskNumberReturned() {
        Command result = parser.parse("unmark 5");
        assertTrue(result instanceof MarkCommand);
        assertEquals(CommandType.UNMARK, result.getType());
        MarkCommand markCommand = (MarkCommand) result;
        assertEquals(5, markCommand.getTaskNumber());
    }

    @Test
    void parse_deleteCommandWithValidTaskNumber_commandDeleteAndTaskNumberReturned() {
        Command result = parser.parse("delete 2");
        assertTrue(result instanceof DeleteCommand);
        DeleteCommand deleteCommand = (DeleteCommand) result;
        assertEquals(2, deleteCommand.getTaskNumber());
    }

    @Test
    void parse_todoCommandWithDescription_commandTodoAndTaskReturned() {
        Command result = parser.parse("todo Write JUNIT tests");
        assertTrue(result instanceof AddTaskCommand);
        assertEquals(CommandType.TODO, result.getType());
        AddTaskCommand addTaskCommand = (AddTaskCommand) result;
        assertTrue(addTaskCommand.getTask() instanceof TodoTask);
        assertEquals("Write JUNIT tests", addTaskCommand.getTask().getDescription());
    }

    @Test
    void parse_todoCommandWithoutDescription_throwsNixyException() {
        Exception exception = assertThrows(NixyException.class, () -> parser.parse("todo"));
        assertEquals("BLAHH!!! The task description cannot be empty.", exception.getMessage());
    }

    @Test
    void parse_deadlineCommandWithValidDate_commandDeadlineAndTaskReturned() {
        Command result = parser.parse("deadline Submit assignment (it's killing me) /by 2024-08-01");
        assertTrue(result instanceof AddTaskCommand);
        assertEquals(CommandType.DEADLINE, result.getType());
        AddTaskCommand addTaskCommand = (AddTaskCommand) result;
        assertTrue(addTaskCommand.getTask() instanceof DeadlineTask);
        DeadlineTask deadlineTask = (DeadlineTask) addTaskCommand.getTask();
        assertEquals("Submit assignment (it's killing me)", deadlineTask.getDescription());
        assertEquals(LocalDate.of(2024, 8, 1), deadlineTask.getDeadline());
    }

    @Test
    void parse_deadlineCommandWithoutDate_throwsNixyException() {
        Exception exception = assertThrows(NixyException.class, () -> parser.parse("deadline Submit assignment"));
        assertEquals("BLAHH!!! The deadline of a deadline task must be specified.", exception.getMessage());
    }

    @Test
    void parse_deadlineCommandWithInvalidDateFormat_throwsNixyException() {
        assertThrows(NixyException.class, () -> parser.parse(
            "deadline Submit assignment /by 2024-08-01T00:00:00"));
    }

    @Test
    void parse_eventCommandWithValidDates_commandEventAndTaskReturned() {
        Command result = parser.parse("event Brain dying /from 2024-08-01 /to 2024-08-02");
        assertTrue(result instanceof AddTaskCommand);
        assertEquals(CommandType.EVENT, result.getType());
        AddTaskCommand addTaskCommand = (AddTaskCommand) result;
        assertTrue(addTaskCommand.getTask() instanceof EventTask);
        EventTask eventTask = (EventTask) addTaskCommand.getTask();
        assertEquals("Brain dying", eventTask.getDescription());
        assertEquals(LocalDate.of(2024, 8, 1), eventTask.getStartDate());
        assertEquals(LocalDate.of(2024, 8, 2), eventTask.getEndDate());
    }

    @Test
    void parse_eventCommandWithoutStartTime_throwsNixyException() {
        Exception exception = assertThrows(NixyException.class, () -> parser.parse(
            "event Project meeting /to 2024-12-02"));
        assertEquals("BLAHH!!! The start time of an event task must be specified.", exception.getMessage());
    }

    @Test
    void parse_eventCommandWithoutEndTime_throwsNixyException() {
        Exception exception = assertThrows(NixyException.class, () -> parser.parse(
            "event Project meeting /from 2024-12-01"));
        assertEquals("BLAHH!!! The end time of an event task must be specified.", exception.getMessage());
    }

    @Test
    void parse_invalidCommand_throwsNixyException() {
        Command result = parser.parse("invalidCommand");
        assertTrue(result instanceof InvalidCommand);
    }
}
