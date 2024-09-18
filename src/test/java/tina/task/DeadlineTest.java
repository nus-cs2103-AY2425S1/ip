package tina.task;
import org.junit.jupiter.api.Test;
import tina.TinaException;
import tina.task.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class DeadlineTest {
    @Test
    public void deadlineDescriptionTest() {
        Deadline deadline = new Deadline("return book", "2/12/2019 1800");
        assertEquals("[D][ ] return book (by: Dec 02 2019 18:00)", deadline.getDes());
    }

    @Test
    public void deadlineTest() {
        Deadline deadline = new Deadline("return book", "2/12/2019 1800");
        assertEquals("D 0 return book | 2019-12-02T18:00", deadline.toString());
    }

    @Test
    public void deadlineTestException() {
        try {
            Deadline deadline = new Deadline("return book", "2/12/2019 800");
            assertEquals("E 0 project meeting | 2019-12-02T18:00 | 2019-12-02T19:00", deadline.toString());
            fail();
        } catch (TinaException e) {
            assertEquals("Invalid date and time format", e.getMessage());
        }
    }
}
