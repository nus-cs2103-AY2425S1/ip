package Gumball;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void isDone_afterMarkDone_Success() throws TaskException {
        ToDo temp = new ToDo("todo test");
        temp.markDone();
        assertEquals(temp.isDone, true);
    }

    @Test
    public void isDone_noMarkDone_Success() throws TaskException {
        ToDo temp = new ToDo("todo test");
        assertEquals(temp.isDone, false);
    }
}
