package mahesh.task;

import java.util.StringTokenizer;
import org.junit.jupiter.api.Test;

import mahesh.util.MaheshException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    
    @Test
    public void testParseDeadline() {
        String input = "deadline Homework /by 2024-08-26T23:59:00";
        StringTokenizer tokenizer = new StringTokenizer(input);
        tokenizer.nextToken();

        try {
            Deadline deadline = Deadline.parseDeadline(tokenizer);
            assertEquals("[D][ ] Homework (by: Aug 26 2024, 23:59:00)", deadline.toString());
        } catch (MaheshException e) {
            fail("Exception should not be thrown for valid input");
        }
    }

    @Test void testParseDeadlineWithIncompleteInput() {
        String input = "deadline Homework /by";
        StringTokenizer tokenizer = new StringTokenizer(input);
        tokenizer.nextToken();

        assertThrows(MaheshException.class, () -> {
            Deadline.parseDeadline(tokenizer);
        });
    }

    @Test void testParseDeadlineWithIncorrectInput() {
        String input = "deadline Homework /by Aug 26 2024, 23:59:00";
        StringTokenizer tokenizer = new StringTokenizer(input);
        tokenizer.nextToken();

        assertThrows(MaheshException.class, () -> {
            Deadline.parseDeadline(tokenizer);
        });
    }
}
