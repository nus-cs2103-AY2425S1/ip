package opus;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {

    @Test
    public void testToDo() {
        ToDo todo = new ToDo("buy groceries");
        assertEquals("[T][ ] buy groceries", todo.toString());
    }
}
