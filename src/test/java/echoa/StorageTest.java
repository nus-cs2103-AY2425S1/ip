package echoa;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    @Test
    void testCreateDate_ValidDateAndTime() {
        LocalDateTime dateTime = Storage.createDateTime("2024-09-18", "15:30");
        LocalDateTime expected = LocalDateTime.of(2024, 9, 18, 15, 30);
        assertEquals(expected, dateTime);
    }

    @Test
    void testCreateDate_InvalidDate() {
        assertThrows(DateTimeParseException.class, () -> {
            Storage.createDateTime("invalid-date", "15:30");
        });
    }

    @Test
    void testCreateDate_InvalidTime() {
        assertThrows(DateTimeParseException.class, () -> {
            Storage.createDateTime("2024-09-18", "invalid-time");
        });
    }
}