package alexer.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DeadlineTest {
    @Test
    public void loadDeadline_invalidDate_error() {
        String taskString = "D|0|loading deadline|xx";
        Deadline deadline = Deadline.fromTaskString(taskString);
        assertNull(deadline);
    }

    @Test
    public void loadDeadline_dateNoTime_error() {
        String taskString = "D|1|date no time?|06-09-2024";
        Deadline deadline = Deadline.fromTaskString(taskString);
        assertNull(deadline);
    }

    @Test
    public void loadDeadline_validDateTime_created() {
        String taskString = "D|1|some deadline|06-09-2024 1234";
        Deadline deadline = Deadline.fromTaskString(taskString);
        assertNotNull(deadline);
    }
}
