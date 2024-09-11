package bob.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {
    @Test
    public void getBy_success() {
        // getBy() should return String object
        assertEquals(LocalDate.of(2024, 9, 15).atStartOfDay().toString(), new DeadlineTask("do assignment",
                LocalDate.of(2024, 9, 15).atStartOfDay()).getBy());
    }
    @Test
    public void getDate_success() {
        // getDate() should return LocalDate object
        assertEquals(LocalDate.of(2024, 9, 15), new DeadlineTask("do assignment",
                LocalDate.of(2024, 9, 15).atStartOfDay()).getDate());
    }

    @Test
    public void getType_success() {
        // getType() should return "D" for DeadlineTask
        assertEquals("D", new DeadlineTask("do assignment", LocalDate.of(2024, 9, 15).atStartOfDay()).getType());
    }

    @Test
    public void parseDateTime_success() {
        // parseDateTime() should return LocalDateTime object
        assertEquals(LocalDate.of(2024, 9, 15).atStartOfDay(), DeadlineTask.parseDateTime("2024-09-15 00:00"));
    }

    @Test
    public void toString_success() {
        // toString() should return "[D][ ] do assignment (by: 2024-09-15)" for DeadlineTask
        assertEquals("[D][ ] do assignment (by: 15 September 2024 12:00am)", new DeadlineTask("do assignment",
                LocalDate.of(2024, 9, 15).atStartOfDay()).toString());
    }
}
