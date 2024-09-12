package lict.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import lict.LictException;

/**
 * The {@code EventTest} class provides unit tests for the {@code Event} class.
 * It tests the functionality of the {@code Event} class, including the
 * {@code toString()}, {@code toData()}, and data loading methods.
 */
public class EventTest {

    /**
     * Tests the {@code toString()} method of the {@code Event} class.
     * Ensures that the string representation of the event is correctly formatted.
     *
     * @throws LictException if there is an error in the event creation.
     */
    @Test
    public void testToString() throws LictException {
        Event event = new Event("Project meeting", "2024-09-01 1400", "2024-10-01 2200");
        assertEquals("[E][ ] Project meeting (from: Sept 1 2024 2:00pm to: Oct 1 2024 10:00pm)", event.toString());

        event.isMarked(true);
        assertEquals("[E][X] Project meeting (from: Sept 1 2024 2:00pm to: Oct 1 2024 10:00pm)", event.toString());
    }

    /**
     * Tests the {@code toData()} method of the {@code Event} class.
     * Ensures that the data representation of the event is correctly formatted.
     *
     * @throws LictException if there is an error in the event creation.
     */
    @Test
    public void testToData() throws LictException {
        Event event = new Event("Project meeting", "2024-09-01 0800", "2024-10-01 1200");
        assertEquals("EVENT | 0 | Project meeting | 2024-09-01 0800 | 2024-10-01 1200\n", event.toData());

        event.isMarked(true);
        assertEquals("EVENT | 1 | Project meeting | 2024-09-01 0800 | 2024-10-01 1200\n", event.toData());
    }

    /**
     * Tests the creation of an {@code Event} object with invalid date formats.
     * Verifies that the correct exception messages are thrown.
     */
    @Test
    public void testInvalidArgumentsFormat() {
        try {
            new Event("Project meeting", "2024-09-01", "anytime");
            fail("LictException was not thrown");
        } catch (LictException e) {
            assertEquals(
                    """
                            Invalid format for event start date or event end date. Please ensure that Event date and
                             time information is in the form 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.""",
                    e.getMessage());
        }

        try {
            new Event("Project meeting", "09/01/2024 1400", "2024-09-01 1600");
            fail("LictException was not thrown");
        } catch (LictException e) {
            assertEquals(
                    """
                            Invalid format for event start date or event end date. Please ensure that Event date and
                             time information is in the form 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.""",
                    e.getMessage());
        }
    }

    /**
     * Tests loading an event from a data string and verifies that the event is correctly loaded.
     *
     * @throws LictException if there is an error in loading the event.
     */
    @Test
    public void testLoadData() throws LictException {
        String data = "Project meeting | 2019-01-01 1100 | 2020-04-04 2300";
        Event event = Event.loadTask(data);
        assertEquals("[E][ ] Project meeting (from: Jan 1 2019 11:00am to: Apr 4 2020 11:00pm)", event.toString());
    }

    /**
     * Tests loading an event with an empty description and verifies that the correct exception is thrown.
     */
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

    /**
     * Tests loading an event with tampered data and verifies that the correct exception messages are thrown.
     */
    @Test
    public void testLoadFaultyData() {
        String invalidDateData = "Faulty | 09/01/20 | 2019-01-01";
        try {
            Event.loadTask(invalidDateData);
            fail("LictException was not thrown");
        } catch (LictException e) {
            assertEquals(
                    """
                            Invalid format for event start date or event end date. Please ensure that Event date and
                             time information is in the form 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.""",
                    e.getMessage());
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
            assertEquals(
                    """
                            Invalid format for event start date or event end date. Please ensure that Event date and
                             time information is in the form 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.""",
                    e.getMessage());
        }
    }

    /**
     * Tests the automatic date handling in the {@code Event} class when the end time is provided without a date.
     *
     * @throws LictException if there is an error in the event creation.
     */
    @Test
    public void testEventAutoDate() throws LictException {
        Event event = new Event("Team meeting", "2024-09-01 1000", "1200");
        assertEquals("[E][ ] Team meeting (from: Sept 1 2024 10:00am to: Sept 1 2024 12:00pm)", event.toString());
        assertEquals("EVENT | 0 | Team meeting | 2024-09-01 1000 | 2024-09-01 1200\n", event.toData());
    }
}
