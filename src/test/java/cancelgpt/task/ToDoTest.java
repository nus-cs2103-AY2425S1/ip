package cancelgpt.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void status_mark_success() {
        Task todo = new ToDo(false, "test");
        assertEquals(0, todo.getStatusInt());
        todo.mark();
        assertEquals(1, todo.getStatusInt());
    }
}
