package yapmeister.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testInvalidDate() {
        Deadline deadline = new Deadline("cry", "tomorrow");
        assertEquals("[D][ ] cry (by: tomorrow)", deadline.toString());
    }
}
