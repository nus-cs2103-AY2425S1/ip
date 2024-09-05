import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import nathanbot.tasks.ToDo;

public class ToDoTest {

    @Test
    public void testToString() {
        ToDo toDo = new ToDo("Submit assignment");
        String expected = "[T][ ] Submit assignment";
        assertEquals(expected, toDo.toString());
    }
}
