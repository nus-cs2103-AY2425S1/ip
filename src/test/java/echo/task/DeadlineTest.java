package echo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class DeadlineTest {

    @Test
    public void testToString() {
        String[] deadlineArray = { "work", "11-11-1111 1111" };
        Deadline deadline = new Deadline(deadlineArray);
        String expected = deadline.toString();
        String actual = "[D][ ] work (by: 11 Nov 1111, 11:11:00 am)";
        assertEquals(expected, actual);
    }

    @Test
    public void testToFancyString() {
        String[] deadlineArray = { "work", "12-12-1212 1212" };
        Deadline deadline = new Deadline(deadlineArray);
        String expected = deadline.toFancyString();
        String actual = "Deadline | 0 | work | /by 12-12-1212 1212";
        assertEquals(expected, actual);
    }
}
