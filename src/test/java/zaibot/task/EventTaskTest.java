package zaibot.task;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventTaskTest {

    @Test
    public void toSaveString_success() {
        LocalDateTime now = LocalDateTime.parse("2024-09-16T13:00");
        EventTask task = new EventTask("name", now, now);

        Assertions.assertEquals(task.toSaveString(),
                "E | INCOMPLETE | name | 2024-09-16T13:00 | 2024-09-16T13:00");
    }
}
