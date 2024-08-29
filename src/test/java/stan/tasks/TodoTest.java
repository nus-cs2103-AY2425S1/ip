package stan.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void createValidTodo() {
        Todo todo = new Todo("Buy groceries");
        assertEquals("[T][ ] Buy groceries", todo.toString());
    }

    @Test
    void testTodoToStorageString() {
        Todo todo = new Todo("Buy groceries");
        assertEquals("T | 0 | Buy groceries", todo.toStorageString());
    }
}
