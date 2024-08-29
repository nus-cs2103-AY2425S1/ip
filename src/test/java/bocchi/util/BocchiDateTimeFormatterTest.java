package bocchi.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BocchiDateTimeFormatterTest {
    @Test
    void parse_variousFormats_success() {
        // format yyyy-M-d HH:mm:ss
        assertEquals(
                LocalDateTime.of(2024, 8, 20, 12, 20, 10),
                BocchiDateTimeFormatter.parse("2024-8-20 12:20:10")
        );

        // format yyyy-MM-dd HH:mm
        assertEquals(
                LocalDateTime.of(2024, 8, 20, 12, 20),
                BocchiDateTimeFormatter.parse("2024-08-20 12:20")
        );

        // format MM-dd
        assertEquals(
                LocalDateTime.of(
                        LocalDate.of(LocalDate.now().getYear(), 8, 20),
                        LocalTime.of(0, 0, 0)
                ),
                BocchiDateTimeFormatter.parse("08-20")
        );

        // format HH:mm
        assertEquals(
                LocalDateTime.of(
                        LocalDate.now(),
                        LocalTime.of(12, 20)
                ),
                BocchiDateTimeFormatter.parse("12:20")
        );

        // format M/d
        assertEquals(
                LocalDateTime.of(
                        LocalDate.now().getYear(),
                        8,
                        1,
                        0,
                        0
                ),
                BocchiDateTimeFormatter.parse("8/1")
        );
    }
}
