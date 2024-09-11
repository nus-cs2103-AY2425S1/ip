import task.ToDo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    
    @Test
    void testToDo() {
        ToDo todo = new ToDo("Ride 10km");
        assertEquals("[T][ ] Ride 10km", todo.toString());
        todo.mark();
        assertEquals("[T][X] Ride 10km", todo.toString());
        todo.unmark();
        assertEquals("[T][ ] Ride 10km", todo.toString());
    }
}
