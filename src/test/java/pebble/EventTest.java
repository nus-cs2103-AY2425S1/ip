package pebble;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testValidDateRange() {
        Event event = new Event("Conference", "2024-09-10", "2024-09-12");
        assertEquals("[E][ ] Conference (from: Sep 10 2024 to: Sep 12 2024)", event.toString());
    }

    @Test
    public void testInvalidFromDate() {
        Event event = new Event("Conference", "10th September", "2024-09-12");
        assertEquals("[E][ ] Conference (from: 10th September to: Sep 12 2024)", event.toString());
    }

    @Test
    public void testInvalidToDate() {
        Event event = new Event("Conference", "2024-09-10", "12th September");
        assertEquals("[E][ ] Conference (from: Sep 10 2024 to: 12th September)", event.toString());
    }

    @Test
    public void testBothDatesInvalid() {
        Event event = new Event("Conference", "10th September", "12th September");
        assertEquals("[E][ ] Conference (from: 10th September to: 12th September)", event.toString());
    }

    @Test
    public void testEmptyFromDate() {
        Event event = new Event("Conference", "", "2024-09-12");
        assertEquals("[E][ ] Conference (from:  to: Sep 12 2024)", event.toString());
    }

    @Test
    public void testEmptyToDate() {
        Event event = new Event("Conference", "2024-09-10", "");
        assertEquals("[E][ ] Conference (from: Sep 10 2024 to: )", event.toString());
    }

    @Test
    public void testNullFromDate() {
        Event event = new Event("Conference", null, "2024-09-12");
        assertEquals("[E][ ] Conference (from: null to: Sep 12 2024)", event.toString());
    }

    @Test
    public void testNullToDate() {
        Event event = new Event("Conference", "2024-09-10", null);
        assertEquals("[E][ ] Conference (from: Sep 10 2024 to: null)", event.toString());
    }

    @Test
    public void testDateWithExtraSpaces() {
        Event event = new Event("Conference", "   2024-09-10   ", "   2024-09-12   ");
        assertEquals("[E][ ] Conference (from: Sep 10 2024 to: Sep 12 2024)", event.toString());
    }

    @Test
    public void testWhitespaceInDescription() {
        Event event = new Event("  Conference  ", "2024-09-10", "2024-09-12");
        assertEquals("[E][ ]   Conference   (from: Sep 10 2024 to: Sep 12 2024)", event.toString());
    }

    @Test
    public void testNonDateStrings() {
        Event event = new Event("Meeting", "early next week", "end of the month");
        assertEquals("[E][ ] Meeting (from: early next week to: end of the month)", event.toString());
    }
}
