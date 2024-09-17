package max.main;

import max.exception.MaxException;
import max.task.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    void setUp() {
        parser = new Parser();
    }
    // Test valid date format
    @Test
    public void parseDate_validDate_success() throws MaxException {
        String date = "2/12/2024 1800";
        LocalDateTime expected = LocalDateTime.of(2024, 12, 2, 18, 0);

        LocalDateTime actual = parser.parseDate(date);

        assertEquals(expected, actual);
    }

    // Test another valid date format
    @Test
    void testParseDateValidFormat() throws MaxException {
        LocalDateTime dateTime = parser.parseDate("2/12/2024 1800");

        assertEquals(LocalDateTime.of(2024, 12, 2, 18, 0), dateTime);
    }

    // Test invalid date format
    @Test
    void testParseDateInvalidFormat() {
        MaxException exception = assertThrows(MaxException.class, () -> {
            parser.parseDate("12-02-2024 1800");
        });
        assertEquals("Invalid date format! Please use d/M/yyyy HHmm. For example, '2/12/2024 1800'",
                exception.getMessage());
    }

    // Test parsing with missing time
    @Test
    void testParseDateMissingTime() {
        MaxException exception = assertThrows(MaxException.class, () -> {
            parser.parseDate("2/12/2024");
        });
        assertEquals("Invalid date format! Please use d/M/yyyy HHmm. For example, '2/12/2024 1800'",
                exception.getMessage());
    }

    // Test parsing with empty string
    @Test
    void testParseDateEmptyString() {
        MaxException exception = assertThrows(MaxException.class, () -> {
            parser.parseDate("");
        });
        assertEquals("Invalid date format! Please use d/M/yyyy HHmm. For example, '2/12/2024 1800'",
                exception.getMessage());
    }


    // Test checkTask with valid description
    @Test
    public void checkTask_validDescription_success() throws MaxException {
        String description = "Read book";

        assertDoesNotThrow(() -> parser.checkTask(description));
    }

    // Test checkTask with empty description
    @Test
    public void checkTask_emptyDescription_throwsException() {
        String emptyDescription = "";

        MaxException exception = assertThrows(MaxException.class, () -> {
            parser.checkTask(emptyDescription);
        });

        assertEquals("Oh no!! The description of the task cannot be empty. :(", exception.getMessage());
    }

}
