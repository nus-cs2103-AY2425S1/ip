package count.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskTest {
    @Test
    public void getDescriptionTest() {
        Task t = new Task("test description", true);
        assertEquals("test description", t.getDescription());
    }

    @Test
    public void getStatusIconTest() {
        Task t = new Task("test description", true);
        assertEquals("X", t.getStatusIcon());
    }

    @Test
    public void stringConstructorTest() {
        Task t = new Task("test description");
        assertEquals(" ", t.getStatusIcon());
    }

    @Test
    public void setCompletionStatusTest() {
        Task t = new Task("test description", false);
        t.setCompletion(true);
        assertEquals("X", t.getStatusIcon());
    }

    @Test
    public void toStringCompletionTest() {
        Task t = new Task("test description", false);
        assertEquals("[ ] test description", t.toString());
    }
}
