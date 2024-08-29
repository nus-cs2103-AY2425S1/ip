package lict.task;

import lict.LictException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void testToString() throws LictException {
        Event event = new Event("Project meeting", "2024-09-01 1400", "2024-10-01 2200");
        assertEquals("[E][ ] Project meeting (from: Sept 1 2024 2:00pm to: Oct 1 2024 10:00pm)", event.toString());

        event.isMarked(true);
        assertEquals("[E][X] Project meeting (from: Sept 1 2024 2:00pm to: Oct 1 2024 10:00pm)", event.toString());
    }

    @Test
    public void testToData() throws LictException {
        Event event = new Event("Project meeting", "2024-09-01 0800", "2024-10-01 1200");
        assertEquals("EVENT | 0 | Project meeting | 2024-09-01 0800 | 2024-10-01 1200\n", event.toData());

        event.isMarked(true);
        assertEquals("EVENT | 1 | Project meeting | 2024-09-01 0800 | 2024-10-01 1200\n", event.toData());
    }

    @Test
    public void testInvalidArgumentsFormat() {
        try {
            new Event("Project meeting", "2024-09-01", "anytime");
            fail("LictException was not thrown");
        } catch (LictException e) {
            assertEquals("Invalid format for event start date or event end date. Please ensure that Event date and time information is in the form 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.", e.getMessage());
        }

        try {
            new Event("Project meeting", "09/01/2024 1400", "2024-09-01 1600");
            fail("LictException was not thrown");
        } catch (LictException e) {
            assertEquals("Invalid format for event start date or event end date. Please ensure that Event date and time information is in the form 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.", e.getMessage());
        }
    }

    @Test
    public void testLoadData() throws LictException {
        String data = "Project meeting | 2019-01-01 1100 | 2020-04-04 2300";
        Event event = Event.loadTask(data);
        assertEquals("[E][ ] Project meeting (from: Jan 1 2019 11:00am to: Apr 4 2020 11:00pm)", event.toString());
    }

    @Test
    public void testLoadEmptyDescription() {
        String invalidData = "| 2019-01-01 | 2019-01-01";
        try {
            Event.loadTask(invalidData);
            fail("LictException was not thrown");
        } catch (LictException e) {
            // Customize the error message based on what your code throws
            assertEquals("Data is faulty. Discarding...", e.getMessage());
        }
    }

    @Test
    public void testLoadFaultyData() {
        String invalidDateData = "Faulty | 09/01/20 | 2019-01-01";
        try {
            Event.loadTask(invalidDateData);
            fail("LictException was not thrown");
        } catch (LictException e) {
            assertEquals("Invalid format for event start date or event end date. Please ensure that Event date and time information is in the form 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.", e.getMessage());
        }

        String missingData = "Faulty | 2019-01-01";
        try {
            Event.loadTask(missingData);
            fail("LictException was not thrown");
        } catch (LictException e) {
            assertEquals("Data is faulty. Discarding...", e.getMessage());
        }

        String extraData = "Faulty | 2019-01-01 | 2019-01-01 | 2019-02-02";
        try {
            Event.loadTask(extraData);
            fail("LictException was not thrown");
        } catch (LictException e) {
            assertEquals("Invalid format for event start date or event end date. Please ensure that Event date and time information is in the form 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.", e.getMessage());
        }
    }

    @Test
    public void testEventAutoDate() throws LictException {
        Event event = new Event("Team meeting", "2024-09-01 1000", "1200");
        assertEquals("[E][ ] Team meeting (from: Sept 1 2024 10:00am to: Sept 1 2024 12:00pm)", event.toString());
        assertEquals("EVENT | 0 | Team meeting | 2024-09-01 1000 | 2024-09-01 1200\n", event.toData());
    }
}
