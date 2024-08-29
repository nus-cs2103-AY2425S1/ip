package Jard;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testDeadlineCreation() {
        Deadline deadline = new Deadline("Submit assignment", "2024-08-30 2359");
        String expectedDescription = "Submit assignment";
        LocalDateTime expectedDateTime = LocalDateTime.parse("2024-08-30 2359", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        String expectedString = "[D][ ] Submit assignment (by: Aug 30 2024, 11:59PM)";
        assertEquals(expectedDescription, deadline.getDescription());
        assertEquals(expectedDateTime, deadline.getBy());
        assertEquals(expectedString, deadline.toString());
    }
}
