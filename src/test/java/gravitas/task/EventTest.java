package gravitas.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void createEvent_instantiateObject_returnEventObject() {
        try {
            String description = "This is a test";
            String startDate = "01/01/2000 0000";
            String endDate = "01/01/2000 2359";
            Event task = new Event(description, startDate, endDate);
            assertInstanceOf(Event.class, task);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void createEvent_invalidStartDateFormat_exceptionThrown() {
        try {
            String description = "This is a test";
            String startDate = "01/0000/20 0000";
            String endDate = "01/01/2000 2359";
            Event task = new Event(description, startDate, endDate);
        } catch (Exception e) {
            assertEquals("Please enter a valid date and time.", e.getMessage());
        }
    }

    @Test
    public void createEvent_invalidEndDateFormat_exceptionThrown() {
        try {
            String description = "This is a test";
            String startDate = "01/01/2000 0000";
            String endDate = "01/0000/20 2359";
            Event task = new Event(description, startDate, endDate);
        } catch (Exception e) {
            assertEquals("Please enter a valid date and time.", e.getMessage());
        }
    }

    @Test
    public void createEvent_missingStartTime_exceptionThrown() {
        try {
            String description = "This is a test";
            String startDate = "01/0000/20";
            String endDate = "01/01/2000 2359";
            Event task = new Event(description, startDate, endDate);
        } catch (Exception e) {
            assertEquals("Please enter a valid date and time.", e.getMessage());
        }
    }

    @Test
    public void createEvent_missingEndTime_exceptionThrown() {
        try {
            String description = "This is a test";
            String startDate = "01/01/2000 0000";
            String endDate = "01/0000/20";
            Event task = new Event(description, startDate, endDate);
        } catch (Exception e) {
            assertEquals("Please enter a valid date and time.", e.getMessage());
        }
    }
}
