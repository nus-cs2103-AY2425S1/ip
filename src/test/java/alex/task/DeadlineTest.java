package alex.task;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    @Test
    public void testToStringMethod() {
        assertEquals("[D][ ] CS coding assignment (by: Aug 29 2024 10.30am)",
                new Deadline("CS coding assignment", false,
                        LocalDateTime.of(2024, 8, 29, 10, 30)).toString());
    }

    @Test
    public void testStorageStringMethod() {
        assertEquals("[D][X] CS coding assignment /by 2024-08-29 1030",
                new Deadline("CS coding assignment", true,
                        LocalDateTime.of(2024, 8, 29, 10, 30)).toStorageString());
    }
}
