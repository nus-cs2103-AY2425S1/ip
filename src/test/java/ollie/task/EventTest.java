package ollie.task;

// Static Imports
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Standard Java Packages
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Third-Party Libraries (JUnit in this case)
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Project-Specific Imports
import ollie.exception.OllieException;

/**
 * Tests for the {@link Event} class.
 */
public class EventTest {

    private Event eventTask;

    /**
     * Initializes an {@link Event} task instance for testing.
     */
    @BeforeEach
    public void setUp() {
        LocalDateTime start = LocalDateTime.of(2023, 8, 31, 14, 0);
        LocalDateTime end = LocalDateTime.of(2023, 8, 31, 16, 0);
        eventTask = new Event("Project meeting", start, end);
    }

    /**
     * Tests the {@link Event#createTask(String)} method with valid command input.
     * Verifies that an {@link Event} task is correctly created.
     */
    @Test
    public void createTask_validCommand_eventTaskCreated() throws OllieException {
        String command = "event Project meeting /from: 2023-09-30 14:00 /to: 2023-09-30 16:00";
        Event task = Event.createTask(command);
        assertEquals("Project meeting", task.getDescription());
        assertEquals(LocalDateTime.of(2023, 9, 30, 14, 0), task.getStart());
        assertEquals(LocalDateTime.of(2023, 9, 30, 16, 0), task.getEnd());
    }

    /** Tests the {@link Event#createTask(String)} method with missing description. */
    @Test
    public void createTask_missingDescription_exceptionThrown() {
        String command = "event ";
        OllieException exception = assertThrows(OllieException.class, () -> Event.createTask(command));
        assertEquals("Please add a name for a event task! ☺", exception.getMessage());
    }

    /** Tests the {@link Event#createTask(String)} method with missing start time. */
    @Test
    public void createTask_missingStartTime_exceptionThrown() {
        String command = "event meeting /from: /to: 2021-09-30 15:00";
        OllieException exception = assertThrows(OllieException.class, () -> Event.createTask(command));
        assertEquals("Please enter a start time for the task! ☺", exception.getMessage());
    }

    /** Tests the {@link Event#createTask(String)} method with missing end time. */
    @Test
    public void createTask_missingEndTime_exceptionThrown() {
        String command = "event meeting /from: 2021-09-30 14:00 /to: ";
        OllieException exception = assertThrows(OllieException.class, () -> Event.createTask(command));
        assertEquals("Please enter an end time for the task! ☺", exception.getMessage());
    }

    /** Tests the {@link Event#createTask(String)} method with a date in the wrong format. */
    @Test
    public void createTask_invalidDateFormat_exceptionThrown() {
        String command = "event Project meeting /from: 2023/09/30 14:00 /to: 2023-09-30 16:00";
        OllieException exception = assertThrows(OllieException.class, () -> Event.createTask(command));
        assertEquals(
                "Please enter the date in the format: yyyy-MM-dd HH:mm", exception.getMessage());
    }

    /**
     * Tests the {@link Event#validateDescription(String)} method with valid command input.
     * Verifies that the description is correctly validated.
     */
    @Test
    public void validateDescription_missingParts_exceptionThrown() {
        String command = "Project meeting /from: 2023-09-30 14:00";
        OllieException exception = assertThrows(
                OllieException.class, () -> eventTask.validateDescription(command));
        assertEquals("Please enter in the format:\n"
                + "event <description> /from: <start time> /to: <end time>\n"
                + "Example: event meeting /from: 2021-09-30 14:00 /to: 2021-09-30 15:00",
                exception.getMessage());
    }

    /**
     * Tests the {@link Event#saveAsString()} method using a unit test.
     * Verifies that the task is correctly formatted for saving.
     */
    @Test
    public void saveAsString_event_correctFormatReturned() {
        String expectedString = "EVENT |   | Project meeting | Aug 31 2023 14:00 | Aug 31 2023 16:00";
        assertEquals(expectedString, eventTask.saveAsString());
    }

    /**
     * Tests the {@link Event#saveAsString()} method.
     * Verifies that the task is correctly formatted for saving.
     */
    @Test
    public void saveAsString_validEvent_correctFormatReturned() {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String expectedString = String.format("EVENT |   | Project meeting | %s | %s",
                eventTask.getStart().format(formatDate),
                eventTask.getEnd().format(formatDate));
        assertEquals(expectedString, eventTask.saveAsString());
    }

    /**
     * Tests the {@link Event#toString()} method.
     * Verifies that the task is correctly represented as a string.
     */
    @Test
    public void toString_validEvent_correctStringRepresentationReturned() {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String expectedString = String.format("[ ] [EVENT] Project meeting (from: %s to: %s)",
                eventTask.getStart().format(formatDate),
                eventTask.getEnd().format(formatDate));
        assertEquals(expectedString, eventTask.toString());
    }

    /**
     * Tests the {@link Event#toString()} method using a unit test.
     * Verifies that the task is correctly represented as a string.
     */
    @Test
    public void toString_event_correctStringRepresentationReturned() {
        String expectedString = "[ ] [EVENT] Project meeting (from: Aug 31 2023 14:00 to: Aug 31 2023 16:00)";
        assertEquals(expectedString, eventTask.toString());
    }

    /**
     * Tests the {@link Event#toString()} method.
     * Verifies that the task is correctly represented as a string.
     */
    @Test
    public void eventTest() {
        Event event = new Event("Event task",
                LocalDateTime.of(2023, 8, 31, 14, 0),
                LocalDateTime.of(2023, 8, 31, 16, 0));
        assertEquals("[ ] [EVENT] Event task (from: Aug 31 2023 14:00 to: Aug 31 2023 16:00)",
                event.toString());
    }
}
