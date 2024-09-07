package makima.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void toString_emptyName() {
        assertEquals((new Event("", LocalDateTime.parse("2018-12-02T15:00"),
                LocalDateTime.parse("2018-12-02T15:00"))).toString(),
                "[E][ ] (from: 2018-12-02T15:00, to: 2018-12-02T15:00)"
        );
    }

    @Test
    public void toFileStringTest1() {
        assertEquals((new Event("a test", LocalDateTime.parse("2018-12-02T15:00"),
                LocalDateTime.parse("2018-12-02T15:00"))).toFileString(),
                "E\na test\nfalse\n2018-12-02T15:00\n2018-12-02T15:00\n"
        );
    }
}
