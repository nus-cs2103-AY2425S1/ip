package echoa;

import echoa.main.Storage;
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

    // Solution below is inspired by AI: assertThrows
    @Test
    void testCreateDate_InvalidDate() {
        assertThrows(DateTimeParseException.class, () -> {
            Storage.createDateTime("invalid-date", "15:30");
        });
    }

    // Solution below is inspired by AI: assertThrows
    @Test
    void testCreateDate_InvalidTime() {
        assertThrows(DateTimeParseException.class, () -> {
            Storage.createDateTime("2024-09-18", "invalid-time");
        });
    }
}