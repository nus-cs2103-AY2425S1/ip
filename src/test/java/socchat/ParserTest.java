package socchat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import parser.DateParser;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parseDate_correctFormat_success() throws SocchatException {
        String validDate = "2024-12-27";
        LocalDate expectedDate = LocalDate.of(2024, 12, 27);
        assertEquals(expectedDate, DateParser.parseDate(validDate));
    }

    @Test
    public void parseDate_incorrectFormat_exceptionThrown() {
        try {
            String invalidDate = "2024-27-12";
            LocalDate expectedDate = LocalDate.of(2024, 12, 27);
            assertEquals(expectedDate, DateParser.parseDate(invalidDate));
            fail();
        } catch (Exception e) {
            assertEquals("Please enter your date as this format --- yyyy-MM-dd", e.getMessage());
        }
    }

    @Test
    public void testDateToString() throws SocchatException {
        String expectedDate = "2024-12-27";
        LocalDate validDate = LocalDate.of(2024, 12, 27);
        assertEquals(expectedDate, DateParser.dateToString(validDate));
    }

}
