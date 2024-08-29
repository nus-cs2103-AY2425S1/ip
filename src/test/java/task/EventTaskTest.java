package task;

import org.junit.jupiter.api.Test;
import parser.InvalidDateException;
import parser.InvalidTimeException;

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
            assertEquals("E | 0 | test | 24-08-2021 | 1200 | 1400", eventTask.simpleFormat());

            eventTask.markAsDone();
            assertEquals("E | 1 | test | 24-08-2021 | 1200 | 1400", eventTask.simpleFormat());

            eventTask.markAsNotDone();
            assertEquals("E | 0 | test | 24-08-2021 | 1200 | 1400", eventTask.simpleFormat());
        } catch (InvalidDateException | InvalidTimeException e) {
            fail();
        }
    }
}
