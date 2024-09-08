package myapp.helperbuddy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testParsing() {
        String taskData = "D | 0 | programming assignment | 02/09/2024 2359";
        String[] parts = taskData.split(" \\| ");
        String description = parts[2].trim();
        LocalDateTime deadlineBy = null;
        if (parts.length > 3) {
            try {
                deadlineBy = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            } catch (DateTimeParseException e) {
                System.out.println("Warning: There is no date format provided");
            }
        }
        Deadline expectedTask = new Deadline(description, deadlineBy);
        Deadline actualTask = Deadline.parseTask(taskData);
        assertEquals(expectedTask.getDescription(), actualTask.getDescription());
    }

    @Test
    public void testFormatting() {
        String taskData = "D | 0 | programming assignment | 02/09/2024 2359";
        String[] parts = taskData.split(" \\| ");
        String description = parts[2].trim();
        LocalDateTime deadlineBy = Parser.parseDateTime(parts[3].trim());
        Deadline task = new Deadline(description, deadlineBy);
        String expected = "D | " + (task.getDone() ? "1" : "0") + " | " + task.getDescription()
                + " | " + deadlineBy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        String actual = "D | 0 | programming assignment | 02/09/2024 2359";
        assertEquals(expected, actual);
    }
}
