package wenjiebot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventTest {

    private Event event;

    @BeforeEach
    public void setUp() {
        event = new Event("celebrate birthday", "2/12/2019 1800", "2/12/2019 2000");
    }

    @Test
    public void testToPrettierString() {
        String expected = "E | 0 | celebrate birthday /from 2/12/2019 1800 /to 2/12/2019 2000";
        assertEquals(expected, event.toPrettierString());
    }

    @Test
    public void testToString() {

        String expected = "[E][ ] celebrate birthday(from: Mon, 02 Dec 2019 18:00 to: Mon, 02 Dec 2019 20:00)";
        assertEquals(expected, event.toString());
    }
}
