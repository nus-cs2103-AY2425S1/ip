package pixy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import pixy.tasks.ToDos;

public class ToDosTest {

    @Test
    public void testToDosInitialization() {
        ToDos todo = new ToDos("Finish homework");
        assertEquals("Finish homework", todo.getDescription());
    }

    @Test
    public void testToString() {
        ToDos todo = new ToDos("Finish homework");
        assertEquals("[T][ ] Finish homework", todo.toString());
    }
}
