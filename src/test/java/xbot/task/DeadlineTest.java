package xbot.task;

import java.util.Locale;
import java.util.TimeZone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit .jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private Deadline deadline1 = new Deadline("test case 1", "24/4/2025");
    private Deadline deadline2 = new Deadline("test case 2", "25/20/2025");
    private Deadline deadline3 = new Deadline("test case 3", "5/1/2025 0925");
    private Deadline deadline4 = new Deadline("test case 4", "25/2/2025 2525");
    private Deadline deadline5 = new Deadline("test case 5", "25/2/2025 6pm");

    @Test
    public void testCorrectDateOutput() {
        assertEquals("[D][ ] test case 1 (by: 24 Apr 2025)",
                deadline1.toString());
    }

    @BeforeAll
    public static void setUp() {
        Locale.setDefault(new Locale("en", "SG"));
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Singapore"));
    }

    @Test
    public void testCorrectDateTimeOutput() {
        assertEquals("[D][ ] test case 3 (by: 5 Jan 2025, 9:25am)",
                deadline3.toString());

    }

    @Test
    public void testIncorrectTimeOutput() {
        assertEquals(
                "[D][ ] test case 2 (by: TimeDate cannot be converted to another format :'0)",
                deadline2.toString());
    }

    @Test
    public void testInvalidTimeOutput() {
        assertEquals("[D][ ] test case 4 (by: TimeDate cannot be converted to another format :'0)",
                deadline4.toString());
    }


    @Test
    public void testInvalidTimeFormatOutput() {
        assertEquals("[D][ ] test case 5 (by: TimeDate cannot be converted to another format :'0)",
                deadline5.toString());
    }
}
