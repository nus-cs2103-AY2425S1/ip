package socchat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import Parser.Parser;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parseDate_correctFormat_success() throws SocchatException {
        String validDate = "2024-12-27 14:00";
        LocalDateTime expectedDate = LocalDateTime.of(2024, 12, 27, 14, 0);
        assertEquals(expectedDate, Parser.parseDate(validDate));
    }

    @Test
    public void parseDate_incorrectFormat_exceptionThrown() {
        try {
            String invalidDate = "2024-27-12 14:00";
            LocalDateTime expectedDate = LocalDateTime.of(2024, 12, 27, 14, 0);
            assertEquals(expectedDate, Parser.parseDate(invalidDate));
            fail();
        } catch (Exception e) {
            assertEquals("Please enter your dateTime as this format --- yyyy-MM-dd HH:mm", e.getMessage());
        }
    }

    @Test
    public void testDateToString() throws SocchatException {
        String expectedDate = "2024-12-27 14:00";
        LocalDateTime validDate = LocalDateTime.of(2024, 12, 27, 14, 0);
        assertEquals(expectedDate, Parser.dateToString(validDate));
    }

}
