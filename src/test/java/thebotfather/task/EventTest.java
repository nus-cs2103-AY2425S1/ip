package thebotfather.task;

import java.util.StringTokenizer;
import org.junit.jupiter.api.Test;
import thebotfather.util.TheBotFatherException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    @Test
    public void testParseEvent() {
        String input = "read book /from 30-08-2024 16:00 /to 31-08-2024 23:59";
        StringTokenizer tokenizer = new StringTokenizer(input);

        try {
            Event event = Event.makeEvent(tokenizer);
            assertEquals("[E][ ] read book (from: 30 Aug 2024, 16:00 to: 31 Aug 2024, 23:59)",
                    event.toString());
        } catch (TheBotFatherException e) {
            fail("Exception should not be thrown for valid input");
        }
    }

    @Test void testParseEventWithIncompleteInput() {
        String input = "read book /from today /to tomorrow";
        StringTokenizer tokenizer = new StringTokenizer(input);

        TheBotFatherException exception = assertThrows(TheBotFatherException.class, () -> {
            Event.makeEvent(tokenizer);
        });

        assertEquals("Kid, look at what you have  written... is that a valid event?? *sigh*\n" +
                "\tIf you have an event, type \"event <description> /from DD-MM-YY HH:MM /to DD-MM-YY HH:MM\"",
                exception.getMessage());
    }

    @Test void testParseEventWithIncorrectInput() {
        String input = "read book /from Aug 30 2024, 16:00 /to 31-08-2024 23:59";
        StringTokenizer tokenizer = new StringTokenizer(input);

        assertThrows(TheBotFatherException.class, () -> {
            Deadline.makeDeadline(tokenizer);
        });
    }
}
