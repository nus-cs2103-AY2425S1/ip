package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testConstructor1() {
        assertEquals("eat dinner tonight", new ToDo("eat dinner tonight").description);
    }

    @Test
    public void testToString() {
        assertEquals("[T][ ] do coding tonight", new ToDo("do coding tonight").toString());
    }
}
