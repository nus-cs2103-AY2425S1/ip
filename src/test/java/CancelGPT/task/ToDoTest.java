package CancelGPT.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ToDoTest {

    @Test
    public void status_mark_success() {
        Task todo = new ToDo(false, "test");
        assertEquals(0, todo.getStatusInt());
        todo.mark();
        assertEquals(1, todo.getStatusInt());
    }
}
