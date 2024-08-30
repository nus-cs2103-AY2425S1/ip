package nixy.parse;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import nixy.Command;
import nixy.exceptions.NixyException;
import nixy.task.DeadlineTask;
import nixy.task.EventTask;
import nixy.task.TodoTask;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    @Test
    void byeCommand() {
        Parsed result = Parser.parse("bye");
        assertEquals(Command.BYE, result.getCommand());
    }

    @Test
    void listCommand() {
        Parsed result = Parser.parse("list");
        assertEquals(Command.LIST, result.getCommand());
    }

    @Test
    void parseMarkAsDone() {
        Parsed result = Parser.parse("mark 1");
        assertEquals(Command.MARK, result.getCommand());
        assertEquals(1, result.getTaskNumber());
    }

    @Test
    void markAsDoneInvalidNumberFormat() {
        Exception exception = assertThrows(NixyException.class, () -> Parser.parse("mark one"));
        assertEquals("BLAHH!!! The task number to mark as done must be an integer.", exception.getMessage());
    }

    @Test
    void markAsDoneEmptySelection() {
        Exception exception = assertThrows(NixyException.class, () -> Parser.parse("mark"));
        assertEquals("BLAHH!!! The task number to mark as done cannot be empty.", exception.getMessage());
    }

    @Test
    void markAsDoneNegativeSelection() {
        Exception exception = assertThrows(NixyException.class, () -> Parser.parse("mark -1"));
        assertEquals("BLAHH!!! The task number to mark as done must be a positive integer.", exception.getMessage());
    }

    @Test
    void parseMarkAsUndone() {
        Parsed result = Parser.parse("unmark 5");
        assertEquals(Command.UNMARK, result.getCommand());
        assertEquals(5, result.getTaskNumber());
    }

    @Test
    void parseDeleteTask() {
        Parsed result = Parser.parse("delete 2");
        assertEquals(Command.DELETE, result.getCommand());
        assertEquals(2, result.getTaskNumber());
    }

    @Test
    void parseTodo() {
        Parsed result = Parser.parse("todo Write JUNIT tests");
        assertEquals(Command.TODO, result.getCommand());
        assertTrue(result.getTask() instanceof TodoTask);
        assertEquals("Write JUNIT tests", result.getTask().getDescription());
    }

    @Test
    void todoEmptyDescription() {
        Exception exception = assertThrows(NixyException.class, () -> Parser.parse("todo"));
        assertEquals("BLAHH!!! The task description cannot be empty.", exception.getMessage());
    }

    @Test
    void parseDeadline() {
        Parsed result = Parser.parse("deadline Submit assignment (it's killing me) /by 2024-08-01");
        assertEquals(Command.DEADLINE, result.getCommand());
        assertTrue(result.getTask() instanceof DeadlineTask);
        DeadlineTask deadlineTask = (DeadlineTask) result.getTask();
        assertEquals("Submit assignment (it's killing me)", deadlineTask.getDescription());
        assertEquals(LocalDate.of(2024, 8, 1), deadlineTask.getDeadline());
    }

    @Test
    void deadlineMissingDeadline() {
        Exception exception = assertThrows(NixyException.class, () -> Parser.parse("deadline Submit assignment"));
        assertEquals("BLAHH!!! The deadline of a deadline task must be specified.", exception.getMessage());
    }

    @Test
    void deadlineInvalidDateFormat() {
        assertThrows(DateTimeParseException.class, () -> Parser.parse("deadline Submit assignment /by 2024-08-01T00:00:00"));
    }

    @Test
    void parseEvent() {
        Parsed result = Parser.parse("event Brain dying /from 2024-08-01 /to 2024-08-02");
        assertEquals(Command.EVENT, result.getCommand());
        assertTrue(result.getTask() instanceof EventTask);
        EventTask eventTask = (EventTask) result.getTask();
        assertEquals("Brain dying", eventTask.getDescription());
        assertEquals(LocalDate.of(2024, 8, 1), eventTask.getStartDate());
        assertEquals(LocalDate.of(2024, 8, 2), eventTask.getEndDate());
    }

    @Test
    void eventMissingStartTime() {
        Exception exception = assertThrows(NixyException.class, () -> Parser.parse("event Project meeting /to 2024-12-02"));
        assertEquals("BLAHH!!! The start time of an event task must be specified.", exception.getMessage());
    }

    @Test
    void eventMissingEndTime() {
        Exception exception = assertThrows(NixyException.class, () -> Parser.parse("event Project meeting /from 2024-12-01"));
        assertEquals("BLAHH!!! The end time of an event task must be specified.", exception.getMessage());
    }

    @Test
    void invalidCommand() {
        Exception exception = assertThrows(NixyException.class, () -> Parser.parse("invalidCommand"));
        assertEquals("BLAHH!!! I'm sorry, but I don't know what that means.", exception.getMessage());
    }
}
