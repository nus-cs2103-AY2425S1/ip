package optimus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
//GPT helped with refining the tests as well as documentation reducing time needed overall
/**
 * Unit tests for the {@link Deadline} class.
 * The tests cover various date formats for deadline parsing, edge cases, and the string representations
 * used for saving and displaying deadline tasks.
 */
class DeadlinesTest {

    private Deadline deadline;

    /**
     * Sets up a valid {@link Deadline} object for testing purposes.
     *
     * @throws OptimusException if the deadline creation fails due to an invalid input.
     */
    @BeforeEach
    public void setUp() throws OptimusException {
        // A valid Deadline object for general tests
        deadline = new Deadline("Test deadline", "1/09/2024 12:00");
    }

    /**
     * Tests the parsing of the deadline string with format "d/MM/yyyy HH:mm".
     *
     * @throws OptimusException if the date string is invalid.
     */
    @Test
    public void parseStringDeadline_ValidFormatD_MM_yyyyHHmm_correctDateTime() throws OptimusException {
        String dateTimeInput = "15/09/2024 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, deadline.parseStringDeadline(dateTimeInput));
    }

    /**
     * Tests the parsing of the deadline string with format "yyyy-MM-dd HH:mm".
     *
     * @throws OptimusException if the date string is invalid.
     */
    @Test
    public void parseStringDeadline_ValidFormatyyyy_MM_ddHHmm_correctDateTime() throws OptimusException {
        String dateTimeInput = "2024-09-15 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, deadline.parseStringDeadline(dateTimeInput));
    }

    /**
     * Tests the parsing of the deadline string with format "d-MM-yyyy HH:mm".
     *
     * @throws OptimusException if the date string is invalid.
     */
    @Test
    public void parseStringDeadline_ValidFormatD_MM_yyyyDashHHmm_correctDateTime() throws OptimusException {
        String dateTimeInput = "15-09-2024 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, deadline.parseStringDeadline(dateTimeInput));
    }

    /**
     * Tests the parsing of the deadline string with format "yyyy/MM/dd HH:mm".
     *
     * @throws OptimusException if the date string is invalid.
     */
    @Test
    public void parseStringDeadline_ValidFormatyyyy_MM_ddSlashHHmm_correctDateTime() throws OptimusException {
        String dateTimeInput = "2024/09/15 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, deadline.parseStringDeadline(dateTimeInput));
    }

    /**
     * Tests the behavior when an invalid date format is provided to the deadline parsing method.
     * Verifies that an {@link OptimusException} is thrown.
     */
    @Test
    public void parseStringDeadline_InvalidFormat_exceptionThrown() {
        String invalidInput = "15-09-2024";
        assertThrows(OptimusException.class, () -> deadline.parseStringDeadline(invalidInput));
    }

    /**
     * Tests the behavior when an empty string is provided to the deadline parsing method.
     * Verifies that an {@link OptimusException} is thrown.
     */
    @Test
    public void parseStringDeadline_EmptyString_exceptionThrown() {
        String emptyInput = "";
        assertThrows(OptimusException.class, () -> deadline.parseStringDeadline(emptyInput));
    }

    /**
     * Tests the behavior when a null value is provided to the deadline parsing method.
     * Verifies that an {@link OptimusException} is thrown.
     */
    @Test
    public void parseStringDeadline_NullInput_exceptionThrown() {
        String nullInput = null;
        assertThrows(OptimusException.class, () -> deadline.parseStringDeadline(nullInput));
    }

    /**
     * Tests the string representation of a valid deadline task.
     * Ensures the string output matches the expected format.
     */
    @Test
    public void toString_withValidDeadlineTask_correctStringOutput() {
        String expectedOutput = "[D][ ] Test deadline (by: Sep 1 2024, 12:00pm)";
        assertEquals(expectedOutput, deadline.toString());
    }

    /**
     * Tests the save string format for a valid deadline task.
     * Ensures the save string output matches the expected format.
     */
    @Test
    public void toSaveString_withValidDeadlineTask_correctSaveStringOutput() {
        String expectedSaveString = "D | 0 | Test deadline | 1/09/2024 12:00";
        assertEquals(expectedSaveString, deadline.toSaveString());
    }

    /**
     * Tests the save string format for a deadline task that is marked as done.
     * Ensures the save string output reflects the completed task status.
     *
     * @throws OptimusException if marking the task as done fails.
     */
    @Test
    public void toSaveString_TaskMarkedAsDone_correctSaveStringOutput() throws OptimusException {
        deadline.setDone();  // Mark task as done
        String expectedSaveString = "D | 1 | Test deadline | 1/09/2024 12:00";
        assertEquals(expectedSaveString, deadline.toSaveString());
    }
}
