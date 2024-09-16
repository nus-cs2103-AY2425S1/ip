package tasktypes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class DeadlineTest {
    @Test
    public void descriptionTest() {
        Deadline testDeadline = new Deadline("Testing Deadline", LocalDate.now());
        assertEquals("Testing Deadline", testDeadline.description);
    }
}
