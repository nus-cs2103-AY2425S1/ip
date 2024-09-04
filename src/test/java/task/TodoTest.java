package task;

import org.junit.jupiter.api.Test;
import rasputin.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void toStringTest() {
        String expected = "[T][ ] read book";
        assertEquals(expected, new Todo("read book").toString());
    }

    @Test
    public void getTypeTest() {
        assertEquals("Todo", new Todo("").getType());
    }

}
