package BRMO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import BRMO.task.Event;

public class EventTest {
    private String timeL = "25-12-2024";
    private String timeR = "26-12-2024";

    @Test
    public void testEventCreation() throws ParseException, InvalidCommandException {
        Event event = new Event("Christmas Party", timeL, timeR);
        assertEquals("[E][ ] Christmas Party (from: 25 Dec 2024 to: 26 Dec 2024)", event.toString());
        assertEquals("E | 0 | Christmas Party | 25-12-2024 | 26-12-2024", event.toText());
    }

    @Test
    public void testMarkAsDone() throws ParseException, InvalidCommandException {
        String timeL = "25-12-2024";
        String timeR = "26-12-2024";
        Event event = new Event("Christmas Party", timeL, timeR);

        for (int i = 0; i < 2; i++) {
            assertEquals("[E][ ] Christmas Party (from: 25 Dec 2024 to: 26 Dec 2024)", event.toString());
            assertEquals("E | 0 | Christmas Party | 25-12-2024 | 26-12-2024", event.toText());
            event.mark();

            assertEquals("[E][X] Christmas Party (from: 25 Dec 2024 to: 26 Dec 2024)", event.toString());
            assertEquals("E | 1 | Christmas Party | 25-12-2024 | 26-12-2024", event.toText());
            event.unmark();
        }
    }

    @Test
    public void testInvalidEventCreation() {
        assertThrows(InvalidCommandException.class, () -> new Event("Christmas Party", timeR, timeL));
    }
}