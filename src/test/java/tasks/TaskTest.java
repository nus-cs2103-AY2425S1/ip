package tasks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
