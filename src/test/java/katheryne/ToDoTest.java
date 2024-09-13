package katheryne;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ToDoTest {
    @Test
    public void testToStringMethod() {
        assertEquals("[T][ ] read book", new ToDo("read book").toString());
    }
}
