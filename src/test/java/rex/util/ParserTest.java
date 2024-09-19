package rex.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import rex.exception.InvalidInputException;

public class ParserTest {
    @Test
    public void testParseInput_validCommand() throws InvalidInputException {
        String input = "todo Read book";
        String[] parsed = Parser.parseInput(input);
        assertEquals("todo", parsed[0]);
        assertEquals("Read book", parsed[1]);
    }

    @Test
    public void testParseInput_invalidCommandThrowsException() {
        String input = "notacommand";
        assertThrows(InvalidInputException.class, () -> {
            Parser.parseInput(input);
        });
    }

    @Test
    public void testParseDateTime_validInput() {
        String dateTimeString = "12-12-23 2359";
        LocalDateTime dateTime = Parser.parseDateTime(dateTimeString);
        LocalDateTime expectedDateTime = LocalDateTime.parse("12-12-23 2359",
                DateTimeFormatter.ofPattern("dd-MM-yy HHmm"));
        assertEquals(expectedDateTime, dateTime);
    }

    @Test
    public void testParseDateTime_invalidInput() {
        String dateTimeString = "12 December 2023 1159pm";
        assertThrows(Exception.class, () -> {
            Parser.parseDateTime(dateTimeString);
        });
    }

}
