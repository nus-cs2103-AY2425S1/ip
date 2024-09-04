package gopher.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

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
        assertThrows(IllegalArgumentException.class, () -> {
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
    public void parseMarkCommand_validCommand_parseSuccess() {
        assertEquals(2,
                Parser.parseMarkCommand("mark 2"));
    }

    @Test
    public void parseUnmarkCommand_validCommand_parseSuccess() {
        assertEquals(3,
                Parser.parseUnMarkCommand("unmark 3"));
    }

    @Test
    public void parseDeleteCommand_validCommand_parseSuccess() {
        assertEquals(1,
                Parser.parseDeleteCommand("delete 1"));
    }
}
