package LBot.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    @Test
    public void testDeadlineMarkAsComplete() {
        Task deadline = new Deadline("testing deadline is marked", false, LocalDateTime.parse("2024-08-29 2303", dateTimeFormat));
        deadline.mark();
        assertEquals(deadline.toString(), "[D][x] testing deadline is marked (by: 2024-08-29 2303)");
    }

    @Test
    public void testDeadlineMarkAsIncomplete() {
        Task deadline = new Deadline("testing deadline is unmarked", true, LocalDateTime.parse("2024-08-29 2303", dateTimeFormat));
        deadline.mark();
        assertEquals(deadline.toString(), "[D][ ] testing deadline is unmarked (by: 2024-08-29 2303)");
    }
}
