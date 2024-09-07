package makima.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toString_emptyName() {
        assertEquals((
                new Deadline("", LocalDateTime.parse("2018-12-02T15:00"))).toString(),
                "[D][ ] (by: 2018-12-02T15:00)"
        );
    }

    @Test
    public void toFileStringTest1() {
        assertEquals((
                new Deadline("a test", LocalDateTime.parse("2018-12-02T15:00"))).toFileString(),
                "D\na test\nfalse\n2018-12-02T15:00\n"
        );
    }
}
