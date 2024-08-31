package bob;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toText_correctFormat_success() {
        assertEquals(
                new Event(
                        "an event",
                        LocalDateTime.parse("2019-12-02 1800", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                        LocalDateTime.parse("2019-12-04 1700", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))).toText(),
                "E | 0 | an event | 2019-12-02T18:00 | 2019-12-04T17:00"
                );
    }

    @Test
    public void toText_markedEventCorrectFormat_success() {
        assertEquals(
                new Event(
                        "an event",
                        LocalDateTime.parse("2019-12-02 1800", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                        LocalDateTime.parse("2019-12-04 1700", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                        true).toText(),
                "E | 1 | an event | 2019-12-02T18:00 | 2019-12-04T17:00"
        );
    }
}
