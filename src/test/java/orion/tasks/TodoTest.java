package orion.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void toStringTest() {
        Todo todo = new Todo("sleep");
        assertEquals(todo.toString(), "[T][ ] sleep");
    }

    @Test
    public void saveStringTest() {
        Todo todo = new Todo("sleep");
        assertEquals(todo.saveString(), "todo,F,sleep");
    }
}
