package nixy.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import nixy.Command;
import nixy.exceptions.NixyException;
import nixy.task.DeadlineTask;
import nixy.task.EventTask;
import nixy.task.TodoTask;


class ParserTest {

    @Test
    void parse_byeCommand_commandByeReturned() {
        Parsed result = Parser.parse("bye");
        assertEquals(Command.BYE, result.getCommand());
    }

    @Test
    void parse_listCommand_commandListReturned() {
        Parsed result = Parser.parse("list");
        assertEquals(Command.LIST, result.getCommand());
    }

    @Test
    void parse_markCommandWithValidTaskNumber_commandMarkAndTaskNumberReturned() {
        Parsed result = Parser.parse("mark 1");
        assertEquals(Command.MARK, result.getCommand());
        assertEquals(1, result.getTaskNumber());
    }

    @Test
    void parse_markCommandWithInvalidNumberFormat_throwsNixyException() {
        Exception exception = assertThrows(NixyException.class, () -> Parser.parse("mark one"));
        assertEquals("BLAHH!!! The task number to mark as done must be an integer.", exception.getMessage());
    }

    @Test
    void parse_markCommandWithoutTaskNumber_throwsNixyException() {
        Exception exception = assertThrows(NixyException.class, () -> Parser.parse("mark"));
        assertEquals("BLAHH!!! The task number to mark as done cannot be empty.", exception.getMessage());
    }

    @Test
    void parse_markCommandWithNegativeTaskNumber_throwsNixyException() {
        Exception exception = assertThrows(NixyException.class, () -> Parser.parse("mark -1"));
        assertEquals("BLAHH!!! The task number to mark as done must be a positive integer.", exception.getMessage());
    }

    @Test
    void parse_unmarkCommandWithValidTaskNumber_commandUnmarkAndTaskNumberReturned() {
        Parsed result = Parser.parse("unmark 5");
        assertEquals(Command.UNMARK, result.getCommand());
        assertEquals(5, result.getTaskNumber());
    }

    @Test
    void parse_deleteCommandWithValidTaskNumber_commandDeleteAndTaskNumberReturned() {
        Parsed result = Parser.parse("delete 2");
        assertEquals(Command.DELETE, result.getCommand());
        assertEquals(2, result.getTaskNumber());
    }

    @Test
    void parse_todoCommandWithDescription_commandTodoAndTaskReturned() {
        Parsed result = Parser.parse("todo Write JUNIT tests");
        assertEquals(Command.TODO, result.getCommand());
        assertTrue(result.getTask() instanceof TodoTask);
        assertEquals("Write JUNIT tests", result.getTask().getDescription());
    }

    @Test
    void parse_todoCommandWithoutDescription_throwsNixyException() {
        Exception exception = assertThrows(NixyException.class, () -> Parser.parse("todo"));
        assertEquals("BLAHH!!! The task description cannot be empty.", exception.getMessage());
    }

    @Test
    void parse_deadlineCommandWithValidDate_commandDeadlineAndTaskReturned() {
        Parsed result = Parser.parse("deadline Submit assignment (it's killing me) /by 2024-08-01");
        assertEquals(Command.DEADLINE, result.getCommand());
        assertTrue(result.getTask() instanceof DeadlineTask);
        DeadlineTask deadlineTask = (DeadlineTask) result.getTask();
        assertEquals("Submit assignment (it's killing me)", deadlineTask.getDescription());
        assertEquals(LocalDate.of(2024, 8, 1), deadlineTask.getDeadline());
    }

    @Test
    void parse_deadlineCommandWithoutDate_throwsNixyException() {
        Exception exception = assertThrows(NixyException.class, () -> Parser.parse("deadline Submit assignment"));
        assertEquals("BLAHH!!! The deadline of a deadline task must be specified.", exception.getMessage());
    }

    @Test
    void parse_deadlineCommandWithInvalidDateFormat_throwsNixyException() {
        assertThrows(NixyException.class, () -> Parser.parse(
            "deadline Submit assignment /by 2024-08-01T00:00:00"));
    }

    @Test
    void parse_eventCommandWithValidDates_commandEventAndTaskReturned() {
        Parsed result = Parser.parse("event Brain dying /from 2024-08-01 /to 2024-08-02");
        assertEquals(Command.EVENT, result.getCommand());
        assertTrue(result.getTask() instanceof EventTask);
        EventTask eventTask = (EventTask) result.getTask();
        assertEquals("Brain dying", eventTask.getDescription());
        assertEquals(LocalDate.of(2024, 8, 1), eventTask.getStartDate());
        assertEquals(LocalDate.of(2024, 8, 2), eventTask.getEndDate());
    }

    @Test
    void parse_eventCommandWithoutStartTime_throwsNixyException() {
        Exception exception = assertThrows(NixyException.class, () -> Parser.parse(
            "event Project meeting /to 2024-12-02"));
        assertEquals("BLAHH!!! The start time of an event task must be specified.", exception.getMessage());
    }

    @Test
    void parse_eventCommandWithoutEndTime_throwsNixyException() {
        Exception exception = assertThrows(NixyException.class, () -> Parser.parse(
            "event Project meeting /from 2024-12-01"));
        assertEquals("BLAHH!!! The end time of an event task must be specified.", exception.getMessage());
    }

    @Test
    void parse_invalidCommand_throwsNixyException() {
        Exception exception = assertThrows(NixyException.class, () -> Parser.parse("invalidCommand"));
        assertEquals("BLAHH!!! I'm sorry, but I don't know what that means.", exception.getMessage());
    }
}
