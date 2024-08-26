package rob;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodoString() {
        Todo todo = new Todo("buy milk");

        String expectedUnmarked = "[T][ ] buy milk";
        assertEquals(expectedUnmarked, todo.toString());

        todo.markAsDone();

        String expectedMarked = "[T][X] buy milk";
        assertEquals(expectedMarked, todo.toString());
    }

    @Test
    public void testTodoSaveString() {
        Todo todo = new Todo("read script");

        String expectedUnmarked = "[T] | [ ] | read script";
        assertEquals(expectedUnmarked, todo.toSaveString());

        todo.markAsDone();

        String expectedMarked = "[T] | [X] | read script";
        assertEquals(expectedMarked, todo.toSaveString());
    }
}
