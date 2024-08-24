package bobbybot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void testConstructor_invalidDateFormat_exceptionThrown() {
        assertThrowsExactly(BobbyBotException.class, () -> new Event("test", "2021-01-99", "2021-11-01"));
    }

    @Test
    public void testConstructor_startDateAfterEndDate_exceptionThrown() {
        assertThrowsExactly(BobbyBotException.class, () -> new Event("test", "2021-01-01", "2020-01-01"));
    }

    @Test
    public void testGetTaskType() {
        try {
            Event event = new Event("test", "2021-01-01", "2021-01-01");
            assertEquals("E", event.getTaskType());
        } catch (BobbyBotException e) {
            fail();
        }
    }
}
