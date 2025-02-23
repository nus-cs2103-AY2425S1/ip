package phenex.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import phenex.task.Task.TaskType;

/**
 * Unit tests for the Event class.
 */
public class EventTest {

    private Event event1;
    private Event event2;
    private Event event3;

    @BeforeEach
    public void setUp() {
        // Initialize test Event objects with different dates
        event1 = new Event("test1",
                            LocalDate.parse("2023-09-15"),
                            LocalDate.parse("2023-09-17"),
                            TaskType.OTHERS);
        event2 = new Event("test2",
                            LocalDate.parse("2023-09-18"),
                            LocalDate.parse("2023-09-19"),
                            TaskType.OTHERS);
        event3 = new Event("test3",
                            LocalDate.parse("2023-09-15"),
                            LocalDate.parse("2023-09-16"),
                            TaskType.OTHERS);
    }

    /**
     * Tests that an Event is created with the correct name, start date, and end date.
     */
    @Test
    public void createEvent_validInputs_success() {
        assertEquals("test1", event1.getName());
        assertEquals(LocalDate.parse("2023-09-15"), event1.getEventStartDate());
        assertEquals(LocalDate.parse("2023-09-17"), event1.getEventEndDate());
    }

    /**
     * Tests that the overlapsWith method correctly detects overlapping dates.
     */
    @Test
    public void overlapsWith_overlappingDates_returnsTrue() {
        assertTrue(event1.overlapsWith(LocalDate.parse("2023-09-16")));
        assertTrue(event1.overlapsWith(LocalDate.parse("2023-09-15")));
        assertTrue(event1.overlapsWith(LocalDate.parse("2023-09-17")));
    }

    /**
     * Tests that the overlapsWith method correctly detects non-overlapping dates.
     */
    @Test
    public void overlapsWith_nonOverlappingDates_returnsFalse() {
        // No overlap with dates outside the range of event1
        assertFalse(event1.overlapsWith(LocalDate.parse("2023-09-18")));
        assertFalse(event1.overlapsWith(LocalDate.parse("2023-09-14")));
    }

    /**
     * Tests that the formatDate method correctly formats a LocalDate.
     */
    @Test
    public void formatDate_correctFormatting_success() {
        // Format should be "MMM dd yyyy"
        assertEquals("Sep 15 2023", event1.formatDate(LocalDate.parse("2023-09-15")));
        assertEquals("Sep 17 2023", event1.formatDate(LocalDate.parse("2023-09-17")));
    }

    /**
     * Tests that two Event objects are considered equal when they have the same name and date range.
     */
    @Test
    public void equals_sameEvent_returnsTrue() {
        Event duplicateEvent = new Event("test1",
                                         LocalDate.parse("2023-09-15"), LocalDate.parse("2023-09-17"),
                                         TaskType.OTHERS);
        assertTrue(event1.equals(duplicateEvent));
    }

    /**
     * Tests that two Event objects are not considered equal when they have different name or date ranges.
     */
    @Test
    public void equals_differentEvent_returnsFalse() {
        assertFalse(event1.equals(event2));
        assertFalse(event1.equals(event3));
    }
}
