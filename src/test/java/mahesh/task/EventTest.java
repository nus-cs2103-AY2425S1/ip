package mahesh.task;

import java.util.StringTokenizer;
import org.junit.jupiter.api.Test;

import mahesh.util.MaheshException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    
    @Test
    public void testParseEvent() {
        String input = "event Party /from 2024-08-26T18:00:00 /to 2024-08-26T22:00:00";
        StringTokenizer tokenizer = new StringTokenizer(input);
        tokenizer.nextToken();

        try {
            Event event = Event.parseEvent(tokenizer);
            assertEquals("[E][ ] Party(from: Aug 26 2024, 18:00:00 to: Aug 26 2024, 22:00:00)", event.toString());
        } catch (MaheshException e) {
            fail("Exception should not be thrown for valid input");
        }
    }

    @Test void testParseEventWithIncompleteInput() {
        String input = "event Gaming /from";
        StringTokenizer tokenizer = new StringTokenizer(input);
        tokenizer.nextToken();

        assertThrows(MaheshException.class, () -> {
            Event.parseEvent(tokenizer);
        });
    }

    @Test void testParseEventWithIncorrectInput() {
        String input = "event Gaming /from 26th Aug 2024, 18:00:00 /to 26th Aug 2024, 22:00:00";
        StringTokenizer tokenizer = new StringTokenizer(input);
        tokenizer.nextToken();

        assertThrows(MaheshException.class, () -> {
            Event.parseEvent(tokenizer);
        });
    }
}
