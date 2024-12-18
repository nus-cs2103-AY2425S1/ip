package max.main;

import max.exception.MaxException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private Parser parser;

    @Test
    public void parseDate_validDate_success() throws MaxException {
        Parser parser = new Parser();
        String validDate = "2/12/2024 1800";
        LocalDateTime expectedDate = LocalDateTime.of(2024, 12, 2, 18, 0);

        LocalDateTime parsedDate = parser.parseDate(validDate);

        assertEquals(expectedDate, parsedDate);
    }

    @Test
    public void parseDate_invalidFormat_throwsMaxException() {
        Parser parser = new Parser();
        String invalidDate = "2024-12-02 18:00";

        MaxException exception = assertThrows(MaxException.class, () -> parser.parseDate(invalidDate));

        assertEquals("Invalid date format! Please use d/M/yyyy HHmm. For example, '2/12/2024 1800'",
                exception.getMessage());
    }

    @Test
    public void parseDate_missingTime_throwsMaxException() {
        Parser parser = new Parser();
        String invalidDate = "2/12/2024";

        MaxException exception = assertThrows(MaxException.class, () -> parser.parseDate(invalidDate));

        assertEquals("Invalid date format! Please use d/M/yyyy HHmm. For example, '2/12/2024 1800'",
                exception.getMessage());
    }

    @Test
    public void parseDate_invalidDay_throwsMaxException() {
        Parser parser = new Parser();
        String invalidDate = "32/12/2024 1800"; // Invalid day

        MaxException exception = assertThrows(MaxException.class, () -> parser.parseDate(invalidDate));

        assertEquals("Invalid date format! Please use d/M/yyyy HHmm. For example, '2/12/2024 1800'",
                exception.getMessage());
    }

    @Test
    public void parseDate_invalidMonth_throwsMaxException() {
        Parser parser = new Parser();
        String invalidDate = "2/13/2024 1800"; // Invalid month

        MaxException exception = assertThrows(MaxException.class, () -> parser.parseDate(invalidDate));

        assertEquals("Invalid date format! Please use d/M/yyyy HHmm. For example, '2/12/2024 1800'",
                exception.getMessage());
    }

    @Test
    public void parseDate_leapYearDate_success() throws MaxException {
        Parser parser = new Parser();
        String leapYearDate = "29/2/2024 1800"; // Leap year date
        LocalDateTime expectedDate = LocalDateTime.of(2024, 2, 29, 18, 0);

        LocalDateTime parsedDate = parser.parseDate(leapYearDate);

        assertEquals(expectedDate, parsedDate);
    }


    @Test
    public void checkTask_validDescription_success() throws MaxException {
        Parser parser = new Parser();
        String description = "Read book";

        assertDoesNotThrow(() -> parser.checkTask(description));
    }

    @Test
    public void checkTask_emptyDescription_throwsException() {
        Parser parser = new Parser();
        String emptyDescription = "";

        MaxException exception = assertThrows(MaxException.class, () -> {
            parser.checkTask(emptyDescription);
        });

        assertEquals("Oh no!! The description of the task cannot be empty. :(", exception.getMessage());
    }

    @Test
    public void checkTask_whitespaceDescription_success() {
        Parser parser = new Parser();
        String whitespaceDescription = "   ";
        assertDoesNotThrow(() -> parser.checkTask(whitespaceDescription));
    }

}
