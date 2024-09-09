package count.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void getDescriptionTest() {
        ToDo t = new ToDo("test description", true);
        assertEquals("test description", t.getDescription());
    }

    @Test
    public void getStatusIconTest() {
        ToDo t = new ToDo("test description", true);
        assertEquals("X", t.getStatusIcon());
    }

    @Test
    public void stringConstructorTest() {
        ToDo t = new ToDo("test description");
        assertEquals(" ", t.getStatusIcon());
    }

    @Test
    public void setCompletionStatusTest() {
        ToDo t = new ToDo("test description", false);
        t.setCompletion(true);
        assertEquals("X", t.getStatusIcon());
    }

    @Test
    public void toStringCompletionTest() {
        ToDo t = new ToDo("test description", false);
        assertEquals("[T][ ] test description", t.toString());
    }
}
