package carly.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import carly.exception.CarlyException;

public class DateTimeParserTest {
    @Test
    public void testFormatDateTime_validDate() {
        DateTimeParser parser = new DateTimeParser("2024-09-01");
        String formattedDate = parser.formatDateTime();
        assertEquals("Sep 01 2024", formattedDate);
    }

    @Test
    public void testFormatDateTime_invalidDate(){
        DateTimeParser parser = new DateTimeParser("2024-09-0");
        String formattedDate = parser.formatDateTime();
        assertNull(formattedDate, "The formatted date should be null for invalid input.");
    }

    @Test
    public void testGetLocalDate_validDate() throws CarlyException{
        DateTimeParser parser = new DateTimeParser("2024-09-18");
        LocalDate localDate = parser.getLocalDate();
        assertEquals(LocalDate.of(2024, 9, 18), localDate);
    }

    @Test
    public void testGetLocalDate_invalidDate() {
        DateTimeParser parser = new DateTimeParser("2024-09-0");

        CarlyException thrown = assertThrows(CarlyException.class, parser::getLocalDate,
                "Expecting getLocalDate() to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Error parsing the formatted date"));
    }
}