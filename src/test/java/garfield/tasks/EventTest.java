package garfield.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import garfield.exceptions.GarfieldException;

public class EventTest {
    private Event event;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @BeforeEach
    void setUp() throws GarfieldException {
        this.event = new Event("Test Event Description",
                LocalDateTime.parse("2024-03-25 00:00", formatter),
                LocalDateTime.parse("2024-03-25 23:59", formatter));
    }

    @Test
    void constructor_validDescriptionAndDateTimes_taskInitialized() {
        assertNotNull(event);
        assertEquals("Test Event Description", event.getTaskDescription());
        assertEquals("[E][ ] Test Event Description (from: Mar 25 2024 12:00AM to: Mar 25 2024 11:59PM)",
                event.toString());
    }

    @Test
    void markAsDone_taskMarkedDone_statusUpdated() {
        event.markAsDone();
        assertEquals("[E][X] Test Event Description (from: Mar 25 2024 12:00AM to: Mar 25 2024 11:59PM)",
                event.toString());
    }

    @Test
    void markAsUndone_taskPreviouslyMarkedDone_statusReverted() {
        event.markAsDone(); // Mark as done first
        event.markAsUndone();
        assertEquals("[E][ ] Test Event Description (from: Mar 25 2024 12:00AM to: Mar 25 2024 11:59PM)",
                event.toString());
    }

    @Test
    void toSaveRepresentation_taskNotDone_correctSaveFormat() {
        assertEquals("E | 0 | Test Event Description | 2024-03-25 00:00 | 2024-03-25 23:59",
                event.toSaveRepresentation());
    }

    @Test
    void toSaveRepresentation_taskDone_correctSaveFormat() {
        event.markAsDone();
        assertEquals("E | 1 | Test Event Description | 2024-03-25 00:00 | 2024-03-25 23:59",
                event.toSaveRepresentation());
    }

    @Test
    void invalidDateHandling_startAfterEnd_exceptionThrown() {
        LocalDateTime from = LocalDateTime.parse("2024-03-25 23:59", formatter);
        LocalDateTime to = LocalDateTime.parse("2024-03-25 00:00", formatter);

        GarfieldException exception = org.junit.jupiter.api.Assertions.assertThrows(GarfieldException.class, () -> {
            new Event("Invalid Event", from, to);
        });

        assertEquals("Your event /from time should be before the /to time!", exception.getMessage());
    }
}
