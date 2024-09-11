package JayTest.task;

import jay.task.EventTask;
import jay.task.Task;
import org.junit.jupiter.api.Test;
import jay.parser.InvalidDateException;
import jay.parser.InvalidTimeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTaskTest {
    @Test
    public void testToString() {
        try {
            EventTask eventTask = new EventTask("test", false, Task.Priority.Low,
                    "24-08-2021", "1200", "1400");
            assertEquals("[E][ ] test { Priority: Low } (from: 24 Aug 2021 12:00 PM to: 02:00 PM)",
                    eventTask.toString());

            eventTask.markAsDone();
            assertEquals("[E][X] test { Priority: Low } (from: 24 Aug 2021 12:00 PM to: 02:00 PM)",
                    eventTask.toString());

            eventTask.markAsNotDone();
            assertEquals("[E][ ] test { Priority: Low } (from: 24 Aug 2021 12:00 PM to: 02:00 PM)",
                    eventTask.toString());
        } catch (InvalidDateException | InvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void testSimpleFormat() {
        try {
            EventTask eventTask = new EventTask("test", false, Task.Priority.Low,
                    "24-08-2021", "1200", "1400");
            assertEquals("E | 0 | test | Low | 24-08-2021 | 1200 | 1400", eventTask.getSimpleFormat());

            eventTask.markAsDone();
            assertEquals("E | 1 | test | Low | 24-08-2021 | 1200 | 1400", eventTask.getSimpleFormat());

            eventTask.markAsNotDone();
            assertEquals("E | 0 | test | Low | 24-08-2021 | 1200 | 1400", eventTask.getSimpleFormat());
        } catch (InvalidDateException | InvalidTimeException e) {
            fail();
        }
    }
}
