package alex.task;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {
    @Test
    public void testToStringMethod() {
        assertEquals("[E][X] Hack and Roll 2024 (from: Aug 29 2024 10.30am to: Aug 30 2024 4.15pm)",
                new Event("Hack and Roll 2024", true,
                        LocalDateTime.of(2024, 8, 29, 10, 30),
                        LocalDateTime.of(2024, 8, 30, 16, 15)).toString());
    }

    @Test
    public void testStorageStringMethod() {
        assertEquals("[E][ ] Hack and Roll 2024 /from 2024-08-29 1030 /to 2024-09-05 2311",
                new Event("Hack and Roll 2024", false,
                        LocalDateTime.of(2024, 8, 29, 10, 30),
                        LocalDateTime.of(2024, 9, 5, 23, 11)).toStorageString());
    }
}
