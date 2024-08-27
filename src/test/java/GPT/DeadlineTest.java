package GPT;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testToFileFormat_withValidDate() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 8, 26, 18, 0);
        Deadline deadline = new Deadline("Submit assignment", dateTime);

        String expectedFormat = "D | 0 | Submit assignment | 2023-08-26 1800";
        assertEquals(expectedFormat, deadline.toFileFormat());
    }

    @Test
    public void testToFileFormat_withInvalidDate() {
        Deadline deadline = new Deadline("Submit assignment", null);

        String expectedFormat = "D | 0 | Submit assignment | [Invalid Date]";
        assertEquals(expectedFormat, deadline.toFileFormat());
    }
}
