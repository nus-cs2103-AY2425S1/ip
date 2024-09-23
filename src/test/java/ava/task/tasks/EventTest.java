package ava.task.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;




class EventTest {

    @Test
    void testToString() {
        Event event = new Event("Meeting", "2023-10-01T10:00", "2023-10-01T12:00");
        String expected = "Event: ❌ Pending | Meeting [From: Sun, 01 Oct 2023 10:00:00 To: Sun, 01 Oct 2023 12:00:00]";
        assertEquals(expected, event.toString());
    }

    @Test
    void serialize() {
        Event event = new Event("Meeting", "2023-10-01T10:00", "2023-10-01T12:00");
        String expected = "E,0,Meeting,2023-10-01T10:00,2023-10-01T12:00";
        assertEquals(expected, event.serialize());
    }
}
