package bobby.ui;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
/**
 * Tests the functionality of the {@link ToDos} class.
 * This class verifies the correct behavior of the bobby.ui.ToDos task class
 * regarding its initialization, string representation, and storage format.
 */
public class ToDosTest {

    //test setup
    private ToDos todo1 = new ToDos("Read book");
    private ToDos todo2 = new ToDos(null);
    @Test
    public void statusInitializeTest1() {
        assertEquals(todo1.getStatus(), "0");
    }
    @Test
    public void toStringTest1() {
        assertEquals(todo1.toString(), "[T][ ] Read book");
    }

    @Test
    public void toStoreTest1() {
        assertEquals(todo1.toStore(), "T/0/Read book");
    }
    @Test
    public void statusInitializeTest2() {
        assertEquals(todo2.getStatus(), "0");
    }
    @Test
    public void toStringTest2() {
        assertEquals(todo2.toString(), "[T][ ] null");
    }

    @Test
    public void toStoreTest2() {
        assertEquals(todo2.toStore(), "T/0/null");
    }
}