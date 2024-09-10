package JayTest.task;

import jay.task.EventTask;
import org.junit.jupiter.api.Test;
import jay.parser.InvalidDateException;
import jay.parser.InvalidTimeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTaskTest {
    @Test
    public void testToString() {
        try {
            EventTask eventTask = new EventTask("test", false, "24-08-2021", "1200", "1400");
            assertEquals("[E][ ] test (from: 24 Aug 2021 12:00 PM to: 02:00 PM)", eventTask.toString());

            eventTask.markAsDone();
            assertEquals("[E][X] test (from: 24 Aug 2021 12:00 PM to: 02:00 PM)", eventTask.toString());

            eventTask.markAsNotDone();
            assertEquals("[E][ ] test (from: 24 Aug 2021 12:00 PM to: 02:00 PM)", eventTask.toString());
        } catch (InvalidDateException | InvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void testSimpleFormat() {
        try {
            EventTask eventTask = new EventTask("test", false, "24-08-2021", "1200", "1400");
            assertEquals("E | 0 | test | 24-08-2021 | 1200 | 1400", eventTask.getSimpleFormat());

            eventTask.markAsDone();
            assertEquals("E | 1 | test | 24-08-2021 | 1200 | 1400", eventTask.getSimpleFormat());

            eventTask.markAsNotDone();
            assertEquals("E | 0 | test | 24-08-2021 | 1200 | 1400", eventTask.getSimpleFormat());
        } catch (InvalidDateException | InvalidTimeException e) {
            fail();
        }
    }
}
