package llama.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] hang out with friends (from: 2024-08-28 12:00|to: 2024-08-28 16:00)",
                new Event("hang out with friends ",
                        LocalDateTime.parse("2024-08-28 12:00", FORMATTER),
                        LocalDateTime.parse("2024-08-28 16:00", FORMATTER),
                        false).toString());
    }

    @Test
    public void testSaveStringConversion() {
        assertEquals("E|0|hang out with friends |2024-08-28 12:00|2024-08-28 16:00",
                new Event("hang out with friends ",
                        LocalDateTime.parse("2024-08-28 12:00", FORMATTER),
                        LocalDateTime.parse("2024-08-28 16:00", FORMATTER),
                        false).getSaveTaskString());
        assertEquals("E|1|hang out with friends |2024-08-28 12:00|2024-08-28 16:00",
                new Event("hang out with friends ",
                        LocalDateTime.parse("2024-08-28 12:00", FORMATTER),
                        LocalDateTime.parse("2024-08-28 16:00", FORMATTER),
                        true).getSaveTaskString());
    }

    @Test
    public void markDone_success() {
        Task task = new Event("hang out with friends ",
                LocalDateTime.parse("2024-08-28 12:00", FORMATTER),
                LocalDateTime.parse("2024-08-28 16:00", FORMATTER),
                false);
        task.markDone();
        assertEquals("[E][X] hang out with friends (from: 2024-08-28 12:00|to: 2024-08-28 16:00)", task.toString());
    }

    @Test
    public void markNotDone_success() {
        Task task = new Event("yes ",
                LocalDateTime.parse("2024-08-28 12:00", FORMATTER),
                LocalDateTime.parse("2024-08-28 16:00", FORMATTER),
                true);
        task.markDone();
        task.markNotDone();
        assertEquals("[E][ ] yes (from: 2024-08-28 12:00|to: 2024-08-28 16:00)", task.toString());
    }
}
