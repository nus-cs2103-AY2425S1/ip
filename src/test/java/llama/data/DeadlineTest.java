package llama.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] return book (by: 2024-08-28 12:00)",
                new Deadline("return book ",
                        LocalDateTime.parse("2024-08-28 12:00", FORMATTER),
                        false).toString());
    }

    @Test
    public void testSaveStringConversion() {
        assertEquals("D|0|return book |2024-08-28 12:00",
                new Deadline("return book ",
                        LocalDateTime.parse("2024-08-28 12:00", FORMATTER),
                        false).getSaveTaskString());
        assertEquals("D|1|return book |2024-08-28 12:00",
                new Deadline("return book ",
                        LocalDateTime.parse("2024-08-28 12:00", FORMATTER),
                        true).getSaveTaskString());
    }

    @Test
    public void markDone_success() {
        Task task = new Deadline("return book ",
                LocalDateTime.parse("2024-08-28 12:00", FORMATTER),
                false);
        task.markDone();
        assertEquals("[D][X] return book (by: 2024-08-28 12:00)", task.toString());
    }

    @Test
    public void markNotDone_success() {
        Task task = new Deadline("yes ",
                LocalDateTime.parse("2024-08-28 17:00", FORMATTER),
                false);
        task.markDone();
        task.markNotDone();
        assertEquals("[D][ ] yes (by: 2024-08-28 17:00)", task.toString());
    }
}
