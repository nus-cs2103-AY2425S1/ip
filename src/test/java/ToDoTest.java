import opus.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class ToDoTest {

    @Test
    public void testToDo() {
        ToDo todo = new ToDo("buy groceries");
        assertEquals("[T][ ] buy groceries", todo.toString());
    }
}
