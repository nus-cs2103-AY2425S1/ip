package poChat.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toStringTest() {
        ToDo toDo = new ToDo("dummy task");
        assertEquals(toDo.toString(), "[T][ ] dummy task");
    }
}
