package alice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    @Test
    public void saveStringTest() {
        Todo todo = new Todo("description");
        assertEquals(todo.saveString(), "T | 0 | description");
    }
}
