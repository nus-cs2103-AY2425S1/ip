package com.nimbus;

import com.nimbus.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToString() {
        Event event = new Event("Test", true, "2024-01-01", "2025-12-12");
        assertEquals(event.toString(), "[E][X] Test (from: Jan 1 2024 to: Dec 12 2025)");
    }
}