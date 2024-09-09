package handsome.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import handsome.exception.InvalidTaskException;

public class TaskTest {
    @Test
    public void createDeadline_correctDateFormat_success() throws InvalidTaskException {
        assertEquals("[D][ ] hello (by: Tue, 23 Apr 2024, 04:00 PM)",
                new Deadline("hello", "[ ]", "2024-04-23 1600", false).toString());
    }

    @Test
    public void createDeadline_incorrectDateFormat_exceptionThrown() {
        try {
            assertEquals("[D][ ] hello (by: Tue, 23 Apr 2024, 04:00 PM)",
                    new Deadline("hello", "[ ]", "hello", false).toString());
            fail();
        } catch (InvalidTaskException error) {
            assertEquals("Date and time must be formatted correctly for deadline! "
                    + "Type prompt to check the correct date format", error.getMessage());
        }
    }

    @Test
    public void createEvent_endTimeLaterThanStartTime_success() throws InvalidTaskException {
        assertEquals("[E][ ] hello (from: Tue, 23 Apr 2024, 04:00 PM to: Tue, 23 Apr 2024, 04:15 PM)",
                new Event("hello", "[ ]", "2024-04-23 1600", "2024-04-23 1615",
                        false).toString());
    }

    @Test
    public void createEvent_endTimeEarlierThanStartTime_exceptionThrown() {
        try {
            assertEquals("[E][ ] hello (from: Tue, 23 Apr 2024, 04:00 PM to: Tue, 23 Apr 2024, 03:00 PM)",
                    new Event("hello", "[ ]", "2024-04-23 1600", "2024-04-23 1500",
                            false).toString());
            fail();
        } catch (InvalidTaskException error) {
            assertEquals("End time of event must be later than start time!", error.getMessage());
        }
    }
}
