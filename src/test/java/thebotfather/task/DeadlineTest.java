package thebotfather.task;

import java.util.StringTokenizer;
import org.junit.jupiter.api.Test;
import thebotfather.util.TheBotFatherException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

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

    @Test void testParseDeadlineWithIncompleteInput() {
        String input = "Assignment /by";
        StringTokenizer tokenizer = new StringTokenizer(input);

        TheBotFatherException exception = assertThrows(TheBotFatherException.class, () -> {
            Deadline.makeDeadline(tokenizer);
        });

        assertEquals("If you have a deadline, type : \"deadline <description> /by DD-MM-YY HH:MM\"",
                exception.getMessage());
    }

    @Test void testParseDeadlineWithIncorrectInput() {
        String input = "Assignment /by Aug 30 2024, 16:00";
        StringTokenizer tokenizer = new StringTokenizer(input);

        assertThrows(TheBotFatherException.class, () -> {
            Deadline.makeDeadline(tokenizer);
        });
    }
}
