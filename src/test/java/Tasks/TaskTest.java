package Tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    public void taskTest() {
        Task bazinga = new Task("badingee");
        try {
            assertFalse(bazinga.isCompleted());
            bazinga.mark();
            assertTrue(bazinga.isCompleted());
            bazinga.mark();
            assertTrue(bazinga.isCompleted());
            bazinga.unmark();
            assertFalse(bazinga.isCompleted());
            bazinga.unmark();
            assertFalse(bazinga.isCompleted());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}