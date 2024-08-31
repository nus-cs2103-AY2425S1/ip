package zbot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testParseDateTime() {
        LocalDateTime dateTime = Parser.parseDateTime("01/04/2024 0159");
        assertEquals("1 Apr 2024 01:59", dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm")));
    }

    @Test
    public void testFormatDateTimeToOutput() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 4, 1, 1, 59);
        assertEquals("1 Apr 2024 01:59", Parser.formatDateTimeToOutput(dateTime));
    }

    @Test
    public void testFormatDateTimeToInput() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 4, 1, 1, 59);
        assertEquals("01/04/2024 0159", Parser.formatDateTimeToInput(dateTime));
    }
}
