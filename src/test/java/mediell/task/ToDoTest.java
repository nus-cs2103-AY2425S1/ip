package mediell.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ToDoTest {
    @Test
    public void init_success(){
        ToDo todo = new ToDo("task");
        assertEquals("[T][ ] task", todo.toString());
    }

    @Test
    public void isTodoFormat_success() {
        // T means its a ToDo
        assertTrue(ToDo.isToDoFormat("T"));
        // any other string is a false
        assertFalse(ToDo.isToDoFormat("P"));
    }

    @Test
    public void taskToStorageFormat_success() {
        // base case
        ToDo todo = new ToDo("task");
        assertEquals("T|0|task", todo.taskToStorageFormat());

        // a marked task
        todo.markCompleted();
        assertEquals("T|1|task", todo.taskToStorageFormat());
    }
}
