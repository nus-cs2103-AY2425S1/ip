package killua.util;

import killua.command.*;
import killua.task.Deadline;
import killua.task.Event;
import killua.task.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parseTodo_validInput_success() throws KilluaException {
        Todo todo = Parser.parseTodo("read book");
        assertEquals("read book", todo.getDescription());
    }

    @Test
    void parseTodo_emptyArgument_throwsKilluaException() {
        assertThrows(KilluaException.class, () -> Parser.parseTodo(""));
    }

    @Test
    void parseDeadline_validInputWithDate_success() throws KilluaException {
        Deadline deadline = Parser.parseDeadline("submit assignment /by 2024-09-01");
        assertEquals("submit assignment", deadline.getDescription());
        assertEquals(LocalDate.of(2024, 9, 1), deadline.getDate());
    }

    @Test
    void parseDeadline_validInputWithDateTime_success() throws KilluaException {
        Deadline deadline = Parser.parseDeadline("submit report /by 2024-09-01 23:59");
        assertEquals("submit report", deadline.getDescription());
        assertEquals(LocalDateTime.of(2024, 9, 1, 23, 59), deadline.getDateTime());
    }

    @Test
    void parseDeadline_invalidInput_throwsKilluaException() {
        assertThrows(KilluaException.class, () -> Parser.parseDeadline("submit report /by invalid-date"));
    }

    @Test
    void parseEvent_validInputWithDate_success() throws KilluaException {
        Event event = Parser.parseEvent("attend conference /from 2024-09-01 /to 2024-09-02");
        assertEquals("attend conference", event.getDescription());
        assertEquals(LocalDate.of(2024, 9, 1), event.getStartDate());
        assertEquals(LocalDate.of(2024, 9, 2), event.getEndDate());
    }

    @Test
    void parseEvent_validInputWithDateTime_success() throws KilluaException {
        Event event = Parser.parseEvent("project meeting /from 2024-09-01 10:00 /to 2024-09-01 12:00");
        assertEquals("project meeting", event.getDescription());
        assertEquals(LocalDateTime.of(2024, 9, 1, 10, 0), event.getStartDateTime());
        assertEquals(LocalDateTime.of(2024, 9, 1, 12, 0), event.getEndDateTime());
    }

    @Test
    void parseEvent_invalidInput_throwsKilluaException() {
        assertThrows(KilluaException.class, () -> Parser.parseEvent("meeting /from invalid-date /to invalid-date"));
    }

    @Test
    void parseIndex_validInput_success() throws KilluaException {
        int index = Parser.parseIndex("2");
        assertEquals(1, index);
    }

    @Test
    void parseIndex_invalidInput_throwsKilluaException() {
        assertThrows(KilluaException.class, () -> Parser.parseIndex("invalid-index"));
    }

    @Test
    void parseCommand_unknownCommand_throwsKilluaException() {
        assertThrows(KilluaException.class, () -> Parser.parseCommand("unknownCommand"));
    }

    @Test
    void parseCommand_emptyArgument_throwsKilluaException() {
        assertThrows(KilluaException.class, () -> Parser.parseCommand("todo "));
    }

    @Test
    void parseCommand_validTodoCommand_success() throws KilluaException {
        Command command = Parser.parseCommand("todo read book");
        assertTrue(command instanceof AddCommand);
    }
}
