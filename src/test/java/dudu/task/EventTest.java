package dudu.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testEventToString() {
        Event task = new Event("this", LocalDate.parse("2024-08-30"), LocalDate.parse("2024-08-31"));
        assertEquals("[E] [ ] this (from: Aug 30 2024 to: Aug 31 2024)", task.toString());
    }

    @Test
    public void testEventFormatString() {
        Event task = new Event("this", LocalDate.parse("2024-07-20"), LocalDate.parse("2024-09-13"));
        assertEquals("E | 0 | this | 2024-07-20 | 2024-09-13", task.formatString());
    }
}
