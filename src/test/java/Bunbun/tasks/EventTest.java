package Bunbun.tasks;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    private static Event event = new Event("birthday party", LocalDate.parse("2024-10-10"),
            LocalDate.parse("2024-10-11"));

    @Test
    public void genFileString_task_success() {
        String expected = "event;false;birthday party;2024-10-10;2024-10-11\n";
        assertEquals(expected, event.genFileString());
    }
}
