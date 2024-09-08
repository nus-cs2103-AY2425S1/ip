package bobbybot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import bobbybot.tasks.Event;

public class EventTest {

    @Test
    public void testConstructor_invalidDateFormat_exceptionThrown() {
        assertThrowsExactly(BobbyBotException.class, () -> new Event("", "2021-01-99", "2021-11-01"));
    }

    @Test
    public void testConstructor_startDateAfterEndDate_exceptionThrown() {
        assertThrowsExactly(BobbyBotException.class, () -> new Event("", "2021-01-01", "2020-01-01"));
    }

    @Test
    public void testGetFileString() {
        try {
            Event event = new Event("test", "2021-01-01", "2021-01-02");
            assertEquals("E | 0 | test | 2021-01-01 | 2021-01-02", event.getFileString());
        } catch (BobbyBotException e) {
            // It should never reach here
            fail();
        }
    }
}
