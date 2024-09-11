package hypebot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void Deadline_invalidDateFormat_exceptionThrown() {
        try {
            Deadline temp1 = new Deadline("temp1", "2019");
            Deadline temp2 = new Deadline("temp2", "2019-09");
            Deadline temp3 = new Deadline("temp3", "Sunday");
            Deadline temp4 = new Deadline("temp4", "September 8th 2024");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals(e.getClass(), DueDateParseException.class);
        }
    }

    @Test
    public void Deadline_dueDatePassed_exceptionThrown() {
        try {
            Deadline temp1 = new Deadline("temp1", "2019-09-15");
            fail();
        } catch (Exception e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
        }
    }
}
