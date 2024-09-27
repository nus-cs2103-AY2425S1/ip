package zaibot.task;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {

    @Test
    public void toSaveString_success() {
        LocalDateTime now = LocalDateTime.parse("2024-09-16T13:00");
        DeadlineTask task = new DeadlineTask("name", now);

        Assertions.assertEquals(task.toSaveString(),
                "D | INCOMPLETE | name | 2024-09-16T13:00");
    }
}
