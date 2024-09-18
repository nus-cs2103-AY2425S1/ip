package Johnson.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EventTest {

    @Test
    void testEventWithDateAndTime() {
        Event event = new Event("Test Event", "2024-12-12", "12:00", new String[] {"tag1", "tag2"});
        assertEquals("Test Event", event.getTaskName());
        assertEquals(LocalDate.of(2024, 12, 12), event.getDate());
        assertEquals(LocalTime.of(12, 0), event.getTime());
        String tag1 = "tag1";
        String tag2 = "tag2";
        assertEquals(tag1, event.getTags().toArray()[0].toString());
        assertEquals(tag2, event.getTags().toArray()[1].toString());
    }

    @Test
    void testEventWithDateOnly() {
        Event event = new Event("Test Event", "2024-12-12", new String[] {"tag1", "tag2"});
        assertEquals("Test Event", event.getTaskName());
        assertEquals(LocalDate.of(2024, 12, 12), event.getDate());
        assertNull(event.getTime());
        String tag1 = "tag1";
        String tag2 = "tag2";
        assertEquals(tag1, event.getTags().toArray()[0].toString());
        assertEquals(tag2, event.getTags().toArray()[1].toString());
    }

    @Test
    void testSetDate() {
        Event event = new Event("Test Event", "2024-12-12");
        event.setDate("2025-01-01");
        assertEquals(LocalDate.of(2025, 1, 1), event.getDate());
    }

    @Test
    void testSetTime() {
        Event event = new Event("Test Event", "2024-12-12");
        event.setTime("14:30");
        assertEquals(LocalTime.of(14, 30), event.getTime());
    }

    @Test
    void testSetDateWithLocalDate() {
        Event event = new Event("Test Event", "2024-12-12");
        event.setDate(LocalDate.of(2025, 1, 1));
        assertEquals(LocalDate.of(2025, 1, 1), event.getDate());
    }

    @Test
    void testSetTimeWithLocalTime() {
        Event event = new Event("Test Event", "2024-12-12");
        event.setTime(LocalTime.of(14, 30));
        assertEquals(LocalTime.of(14, 30), event.getTime());
    }

    @Test
    void testAddTag() {
        Event event = new Event("Test Event", "2024-12-12");
        event.addTag("newTag");
        String newTag = "newTag";
        assertEquals(newTag, event.getTags().toArray()[0].toString());
    }

    @Test
    void testToString() {
        Event event = new Event("Test Event", "2024-12-12", "12:00", new String[] {});
        assertEquals("[E][ ] Test Event [] (from: 2024-12-12 12:00)", event.toString());
    }
}