package lewis;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test class for Deadline in the Lewis chatbot
 */
public class DeadlineTest {
    @Test
    public void testToCsv_withValidDeadline() {
        // Arrange
        String date = "2024-09-20";
        String time = "23:59";
        Deadline deadline = Deadline.of("Submit assignment", date, time);

        // Act
        String actual = deadline.toCsv();

        // Assert
        String expected = "Deadline,Submit assignment,NOT_DONE,2024-09-20T23:59";
        assertEquals(expected, actual);
    }
    @Test
    public void testToString_withValidDeadline() {
        // Arrange
        String date = "2024-09-20";
        String time = "23:59";
        Deadline deadline = Deadline.of("Submit assignment", date, time);

        // Act
        String actual = deadline.toString();

        // Assert
        String expected = "[D][ ] Submit assignment (Deadline: Sept 20 2024 23:59)";
        assertEquals(expected, actual);
    }
}
