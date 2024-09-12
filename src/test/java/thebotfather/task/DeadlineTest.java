package thebotfather.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.StringTokenizer;

import org.junit.jupiter.api.Test;

import thebotfather.util.TheBotFatherException;

/**
 * A test suite for the {@link Deadline} class.
 * Tests the parsing and creation of deadlines, ensuring that deadlines are correctly instantiated and exceptions are properly thrown for invalid inputs.
 */
public class DeadlineTest {

    /**
     * Tests the parsing of a valid deadline input string.
     * Verifies that the {@link Deadline} object is correctly created and formatted.
     */
    @Test
    public void testParseDeadline() {
        String input = "Assignment /by 30-08-2024 16:00";
        StringTokenizer tokenizer = new StringTokenizer(input);

        try {
            Deadline deadline = Deadline.makeDeadline(tokenizer);
            assertEquals("[D][ ] Assignment (by: 30 Aug 2024, 16:00)", deadline.toString());
        } catch (TheBotFatherException e) {
            fail("Exception should not be thrown for valid input");
        }
    }

    /**
     * Tests the parsing of an incomplete deadline input string.
     * Verifies that a {@link TheBotFatherException} is thrown with the correct error message.
     */
    @Test
    void testParseDeadlineWithIncompleteInput() {
        String input = "Assignment /by";
        StringTokenizer tokenizer = new StringTokenizer(input);

        TheBotFatherException exception = assertThrows(TheBotFatherException.class, () -> {
            Deadline.makeDeadline(tokenizer);
        });

        assertEquals("If you have a deadline, type : \"deadline <description> /by DD-MM-YY HH:MM\"",
                exception.getMessage());
    }

    /**
     * Tests the parsing of a deadline input string with incorrect date format.
     * Verifies that a {@link TheBotFatherException} is thrown when the date format is invalid.
     */
    @Test
    void testParseDeadlineWithIncorrectInput() {
        String input = "Assignment /by Aug 30 2024, 16:00";
        StringTokenizer tokenizer = new StringTokenizer(input);

        assertThrows(TheBotFatherException.class, () -> {
            Deadline.makeDeadline(tokenizer);
        });
    }
}