package fence.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] some task (by: Aug 31 2024)",
                new Deadline("some task", LocalDate.parse("2024-08-31")).toString());
    }

    @Test
    public void testTxtConversion() {
        assertEquals("DEADLINE (UNDONE) some task /by 2024-08-31",
                new Deadline("some task", LocalDate.parse("2024-08-31")).toTxt());
    }
}
