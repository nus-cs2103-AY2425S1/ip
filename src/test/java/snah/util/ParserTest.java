package snah.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void getEventPayloadTest() {
        // Test valid input
        String input = "event meeting /from now /to later";
        String[] expected = new String[] { "meeting", "now", "later" };
        String[] actual = Parser.getEventPayload(input);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }

        // Test error input
        input = "event meeting /from now /to";
        actual = Parser.getEventPayload(input);
        assertEquals(null, actual);

        input = "event meeting /from now";
        actual = Parser.getEventPayload(input);
        assertEquals(null, actual);

        input = "event meeting";
        actual = Parser.getEventPayload(input);
        assertEquals(null, actual);

        input = "event";
        actual = Parser.getEventPayload(input);
        assertEquals(null, actual);
    }

    @Test
    public void getDeadlinePayloadTest() {
        // Test valid input
        String input = "deadline assignment /by tomorrow";
        String[] expected = new String[] { "assignment", "tomorrow" };
        String[] actual = Parser.getDeadlinePayload(input);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }

        // Test error input
        input = "deadline assignment /by";
        actual = Parser.getDeadlinePayload(input);
        assertEquals(null, actual);

        input = "deadline assignment";
        actual = Parser.getDeadlinePayload(input);
        assertEquals(null, actual);

        input = "deadline";
        actual = Parser.getDeadlinePayload(input);
        assertEquals(null, actual);
    }

}
