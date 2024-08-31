package nah.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class TaskTest {
    /**
     * Tests isMatch method.
     */
    @Test
    public void wordMatchTest1() {
        Task t = new Task.ToDos("go to sleep");
        assertEquals(true, t.isMatch("TO"));
    }

    /**
     * Tests isMatch method.
     */
    @Test
    public void wordMatchTest2() {
        Task t = new Task.ToDos("go to school");
        assertEquals(true, t.isOneMatch("nah TODO"));
    }

    /**
     * Tests isOneMatch method.
     */
    @Test
    public void wordMatchTest3() {
        Task t = new Task.ToDos("go to school");
        assertEquals(false, t.isOneMatch("nah nha anh"));
    }
}
