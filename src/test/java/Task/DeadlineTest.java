package Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void deadline_successTest() {
        String expectedWithTime = "[D][ ] deadline test (by: 30 Aug 2024 1500)";
        String expectedWithoutTime = "[D][ ] deadline no time test (by: 1 Sep 2024)";
        assertEquals(expectedWithTime,
                new Deadline("deadline test", "2024-08-30 1500").toString());
        assertEquals(expectedWithoutTime,
                new Deadline("deadline no time test", "2024-09-01").toString());
    }

    @Test
    public void deadline_failTest() {
        String expected = "ERROR! Invalid deadline format.";
        try {
            assertEquals(expected,
                    new Deadline("deadline fail test", "1 Sep 2024").toString());
            fail();
        } catch (InvalidTaskException e) {
            assertEquals(expected, e.getMessage());
        }

    }

    @Test
    public void getTypeTest() {
        assertEquals("Deadline", new Deadline("", "2024-08-30").getType());
    }
}
