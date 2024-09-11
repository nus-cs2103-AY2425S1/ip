package muffin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void dateFormatTest() {
        Event e = new Event("festival", "2024-09-15", "2024-09-16");
        String s = "[E][ ] festival (from: Sun, Sep 15 2024 to: Mon, Sep 16 2024)";
        assertEquals(s, e.toString());
    }
}
