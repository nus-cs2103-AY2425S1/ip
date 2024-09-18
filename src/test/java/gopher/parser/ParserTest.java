package gopher.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

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
}
