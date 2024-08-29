package tudee.task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    private ToDo toDo;

    @BeforeEach
    void setUp() {
        toDo = new ToDo("Play game");
    }

    @Test
    void testToString() {
        String expectedString = "[T][ ] Play game";
        assertEquals(expectedString, toDo.toString());
    }

    @Test
    void testToFileString() {
        String expectedFileString = "T | 0 | Play game";
        assertEquals(expectedFileString, toDo.toFileString());
    }
}
