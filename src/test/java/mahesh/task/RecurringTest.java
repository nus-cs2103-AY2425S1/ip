package mahesh.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.StringTokenizer;

import org.junit.jupiter.api.Test;

import mahesh.util.MaheshException;

public class RecurringTest {

    @Test
    public void testParseRecurring() {
        String input = "recurring Water plants /every Monday";
        StringTokenizer tokenizer = new StringTokenizer(input);
        tokenizer.nextToken(); // Skip the "recurring" part

        try {
            Recurring recurring = Recurring.parseRecurring(tokenizer);
            assertEquals("[R][ ] Water plants every Monday", recurring.toString());
        } catch (MaheshException e) {
            fail("Exception should not be thrown for valid input");
        }
    }

    @Test
    public void testParseRecurringWithInvalidInput() {
        String input = "recurring Water plants";
        StringTokenizer tokenizer = new StringTokenizer(input);
        tokenizer.nextToken(); // Skip the "recurring" part

        assertThrows(MaheshException.class, () -> {
            Recurring.parseRecurring(tokenizer);
        });
    }
}
