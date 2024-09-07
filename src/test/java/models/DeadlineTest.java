package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    private Deadline deadline;

    @BeforeEach
    void setUp() {
        // Assume Deadline takes a description and date string in the constructor
        deadline = new Deadline("Submit report", "2024-09-15");
    }

    @Test
    void testConvertDate_ValidDate() {
        // Arrange
        String validDate = "2024-09-15";

        // Act
        LocalDate result = deadline.convertDate(validDate);

        // Assert
        assertEquals(LocalDate.of(2024, 9, 15), result);
    }

    @Test
    void testConvertDate_InvalidDate() {
        // Arrange
        String invalidDate = "invalid-date";

        // Act
        LocalDate result = deadline.convertDate(invalidDate);

        // Assert
        assertEquals(LocalDate.EPOCH, result); // Expecting EPOCH for invalid date
    }

    @Test
    void testConvertDate_EmptyString() {
        // Arrange
        String emptyString = "";

        // Act
        LocalDate result = deadline.convertDate(emptyString);

        // Assert
        assertEquals(LocalDate.EPOCH, result); // Expecting EPOCH for empty string
    }

    @Test
    void testConvertDate_NullString() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            deadline.convertDate(null);  // Expecting NullPointerException when passing null
        });
    }

    @Test
    void testGetDescription() {
        // Act
        String description = deadline.getDescription();

        // Assert
        assertEquals("Submit report", description);
    }

    @Test
    void testGetBy() {
        // Act
        LocalDate dueDate = deadline.getBy();

        // Assert
        assertEquals("2024-09-15", dueDate.toString()); // Checking if the 'by' date is returned correctly as a string
    }

    @Test
    void testMarkAsDone() {
        // Act
        deadline.mark();

        // Assert
        assertTrue(deadline.getIsDone()); // Check if the deadline is marked as done
    }

    @Test
    void testIsNotDoneInitially() {
        // Assert
        assertFalse(deadline.getIsDone()); // By default, the task should not be marked as done
    }
}
