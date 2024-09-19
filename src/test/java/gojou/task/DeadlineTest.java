package gojou.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testToStringMethod() {
        assertEquals("[D][HIGH][ ] CS coding assignment (by: Aug 29 2024 10.30am)",
                new Deadline("CS coding assignment", false, Priority.HIGH,
                        LocalDateTime.of(2024, 8, 29, 10, 30)).toString());
    }

    @Test
    public void testStorageStringMethod() {
        assertEquals("[D][X] CS coding assignment //high /by 2024-08-29 1030",
                new Deadline("CS coding assignment", true, Priority.HIGH,
                        LocalDateTime.of(2024, 8, 29, 10, 30)).toStorageString());
    }
}
