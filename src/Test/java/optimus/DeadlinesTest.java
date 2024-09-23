package optimus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
//Used GPT for help in generating more tests. It has helped in covering more bases.
class DeadlinesTest {

    private Deadline deadline;

    @BeforeEach
    public void setUp() throws OptimusException {
        // A valid Deadline object for general tests
        deadline = new Deadline("Test deadline", "1/09/2024 12:00");
    }

    // Tests for valid date formats
    @Test
    public void parseStringDeadline_ValidFormatD_MM_yyyyHHmm_correctDateTime() throws OptimusException {
        String dateTimeInput = "15/09/2024 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, deadline.parseStringDeadline(dateTimeInput));
    }

    @Test
    public void parseStringDeadline_ValidFormatyyyy_MM_ddHHmm_correctDateTime() throws OptimusException {
        String dateTimeInput = "2024-09-15 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, deadline.parseStringDeadline(dateTimeInput));
    }

    @Test
    public void parseStringDeadline_ValidFormatD_MM_yyyyDashHHmm_correctDateTime() throws OptimusException {
        String dateTimeInput = "15-09-2024 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, deadline.parseStringDeadline(dateTimeInput));
    }

    @Test
    public void parseStringDeadline_ValidFormatyyyy_MM_ddSlashHHmm_correctDateTime() throws OptimusException {
        String dateTimeInput = "2024/09/15 15:30";
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 9, 15, 15, 30);
        assertEquals(expectedDateTime, deadline.parseStringDeadline(dateTimeInput));
    }

    // Test invalid date formats
    @Test
    public void parseStringDeadline_InvalidFormat_exceptionThrown() {
        String invalidInput = "15-09-2024";
        assertThrows(OptimusException.class, () -> deadline.parseStringDeadline(invalidInput));
    }

    @Test
    public void parseStringDeadline_EmptyString_exceptionThrown() {
        String emptyInput = "";
        assertThrows(OptimusException.class, () -> deadline.parseStringDeadline(emptyInput));
    }

    @Test
    public void parseStringDeadline_NullInput_exceptionThrown() {
        String nullInput = null;
        assertThrows(OptimusException.class, () -> deadline.parseStringDeadline(nullInput));
    }

    // Test string representation of the deadline
    @Test
    public void toString_withValidDeadlineTask_correctStringOutput() {
        String expectedOutput = "[D][ ] Test deadline (by: Sep 1 2024, 12:00pm)";
        assertEquals(expectedOutput, deadline.toString());
    }

    // Test saving format for deadline
    @Test
    public void toSaveString_withValidDeadlineTask_correctSaveStringOutput() {
        String expectedSaveString = "D | 0 | Test deadline | 1/09/2024 12:00";
        assertEquals(expectedSaveString, deadline.toSaveString());
    }

    // Test for a completed task
    @Test
    public void toSaveString_TaskMarkedAsDone_correctSaveStringOutput() throws OptimusException {
        deadline.setDone();  // Mark task as done
        String expectedSaveString = "D | 1 | Test deadline | 1/09/2024 12:00";
        assertEquals(expectedSaveString, deadline.toSaveString());
    }
}
