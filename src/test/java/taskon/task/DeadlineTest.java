package taskon.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] study for test (by: Oct 25 2024, 5:00 PM)",
                new Deadline("study for test", "2024-10-25 1700").toString());
    }

}
