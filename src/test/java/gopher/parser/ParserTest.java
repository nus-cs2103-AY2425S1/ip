package gopher.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import gopher.exception.FileCorruptedException;
import org.junit.jupiter.api.Test;

import gopher.exception.InvalidTokenException;
import gopher.exception.MissingTaskNumberException;

public class ParserTest {
    @Test
    public void parseDateString_validDate_parseSuccess() {
        assertEquals(LocalDateTime.of(2024, 8, 30, 0, 0),
                Parser.parseDateString("2024-08-30"));
    }

    @Test
    public void parseDateString_validDateTime_parseSuccess() {
        assertEquals(LocalDateTime.of(2024, 8, 30, 16, 0),
                Parser.parseDateString("2024-08-30 16:00"));
    }

    @Test
    public void parseDateString_invalidFormat_exceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> {
            Parser.parseDateString("2024-8-30");
        });
    }

    @Test
    public void parseDateString_invalidDate_exceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> {
            Parser.parseDateString("2024-08-32");
        });
    }

    @Test
    public void parseDateString_emptyString_exceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> {
            Parser.parseDateString("");
        });
    }

    @Test
    public void parseLocalDateTime_inputLocalDate_parseSuccess() {
        assertEquals("2024-08-30 00:00",
                Parser.parseLocalDateTime(LocalDateTime.of(2024, 8, 30, 0, 0)));
    }

    @Test
    public void parseLocalDateTime_inputLocalDateTime_parseSuccess() {
        assertEquals("2024-08-30 23:00",
                Parser.parseLocalDateTime(LocalDateTime.of(2024, 8, 30, 23, 0)));
    }

    @Test
    public void displayDate_inputLocalDate_display() {
        assertEquals("Aug 30 2024 00:00",
                Parser.displayDate(LocalDateTime.of(2024, 8, 30, 0, 0)));
    }

    @Test
    public void displayDate_inputLocalDateTime_display() {
        assertEquals("Aug 30 2024 05:00",
                Parser.displayDate(LocalDateTime.of(2024, 8, 30, 5, 0)));
    }

    @Test
    public void parseMarkCommand_singleTaskNumber_parseSuccess()
            throws MissingTaskNumberException {
        assertArrayEquals(new int[]{2},
                Parser.parseMarkCommand("mark 2"));
    }

    @Test
    public void parseMarkCommand_multipleTaskNumbers_parseSuccess()
            throws MissingTaskNumberException {
        assertArrayEquals(new int[]{1, 2, 3},
                Parser.parseMarkCommand("mark 1 2 3"));
    }

    @Test
    public void parseUnmarkCommand_singleTaskNumber_parseSuccess()
            throws MissingTaskNumberException {
        assertArrayEquals(new int[]{2},
                Parser.parseUnmarkCommand("unmark 2"));
    }

    @Test
    public void parseUnmarkCommand_multipleTaskNumbers_parseSuccess()
            throws MissingTaskNumberException {
        assertArrayEquals(new int[]{1, 2, 3},
                Parser.parseUnmarkCommand("unmark 1 2 3"));
    }

    @Test
    public void parseDeleteCommand_singleTaskNumbers_parseSuccess()
            throws MissingTaskNumberException {
        assertArrayEquals(new int[]{1}, Parser.parseDeleteCommand("delete 1"));
    }

    @Test
    public void parseDeleteCommand_multipleTaskNumber_parseSuccess()
            throws MissingTaskNumberException {
        assertArrayEquals(new int[]{1, 2, 3}, Parser.parseDeleteCommand("delete 1 2 3"));
    }

    @Test
    public void parseUpdateTodoCommand_validCommand_parseSuccess()
            throws InvalidTokenException {
        String[] tokens = new String[]{"update", "2", "Hello", "World"};
        assertArrayEquals(new String[]{"Hello World"}, Parser.parseUpdateTodoTaskCommand(tokens));
    }

    @Test
    public void parseUpdateTodoCommand_inputWithByToken_exceptionThrown() {
        String[] tokens = new String[]{"update", "4", "Test", "/by", "2024-09-11"};
        assertThrows(InvalidTokenException.class, () -> {
            Parser.parseUpdateTodoTaskCommand(tokens);
        });
    }

    @Test
    public void parseUpdateDeadlineCommand_validCommand_parseSuccess()
            throws InvalidTokenException {
        String[] tokens = new String[]{"update", "3", "/by", "2024-09-11"};
        assertArrayEquals(new String[]{"", "2024-09-11"},
                Parser.parseUpdateDeadlineTaskCommand(tokens));
    }

    @Test
    public void parseUpdateDeadlineCommand_inputWithToToken_exceptionThrown() {
        assertThrows(InvalidTokenException.class, () -> {
            String[] tokens = new String[]{"update", "3", "/to", "2024"};
            Parser.parseUpdateDeadlineTaskCommand(tokens);
        });
    }

    @Test
    public void parseUpdateEventCommand_validCommand_parseSuccess()
            throws InvalidTokenException {
        String[] tokens = new String[]{"update", "5", "Event", "1", "/from",
                                       "2024-09-11", "15:00", "/to", "2024-09-12", "16:00"};
        assertArrayEquals(new String[]{"Event 1", "2024-09-11 15:00", "2024-09-12 16:00"},
                Parser.parseUpdateEventTaskCommand(tokens));
    }

    @Test
    public void parseUpdateEventCommand_inputWithByToken_exceptionThrown() {
        assertThrows(InvalidTokenException.class, () -> {
            String[] tokens = new String[]{"update", "5", "Event", "1", "/from",
                                           "2024-09-11", "15:00", "/to", "2024-09-12",
                                           "16:00", "/by", "2024-09-15"};
            Parser.parseUpdateEventTaskCommand(tokens);
        });
    }

    @Test
    public void parseSavedTaskData_corruptedFile_exceptionThrown() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            Parser.parseSavedTaskData("""
                    Hello World
                    """);
        });
    }

    @Test
    public void parseMarkCommand_validCommand_parseSuccess()
            throws MissingTaskNumberException {
        assertArrayEquals(new int[]{1, 3, 5}, Parser.parseMarkCommand("mark 1 3 5"));
    }

    @Test
    public void parseMarkCommand_missingTaskNumber_exceptionThrown() {
        assertThrows(MissingTaskNumberException.class, () -> {
            Parser.parseMarkCommand("mark");
        });
    }

    @Test
    public void parseUnmarkCommand_validCommand_parseSuccess()
            throws MissingTaskNumberException {
        assertArrayEquals(new int[]{2, 4, 6}, Parser.parseMarkCommand("unmark 2 4 6"));
    }

    @Test
    public void parseUnmarkCommand_missingTaskNumber_exceptionThrown() {
        assertThrows(MissingTaskNumberException.class, () -> {
            Parser.parseMarkCommand("unmark");
        });
    }

    @Test
    public void parseDeleteCommand_validCommand_parseSuccess()
            throws MissingTaskNumberException {
        assertArrayEquals(new int[]{1, 2, 3}, Parser.parseMarkCommand("delete 1 2 3"));
    }

    @Test
    public void parseDeleteCommand_missingTaskNumber_exceptionThrown() {
        assertThrows(MissingTaskNumberException.class, () -> {
            Parser.parseMarkCommand("delete");
        });
    }

    @Test
    public void parseFindCommand_validCommand_parseSuccess() {
        assertEquals("Hello World 123", Parser.parseFindCommand("find Hello World 123"));
    }
}
