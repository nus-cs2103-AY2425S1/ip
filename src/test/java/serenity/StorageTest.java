package serenity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import serenity.task.Todo;


public class StorageTest {

    @Test
    public void loadTask_validTodo_success() throws SerenityException {
        Storage storage = new Storage("");
        String taskDescription = "T | 0 | read book";
        assertEquals(new Todo("read book").toString(),
                storage.loadTask(taskDescription).toString());
    }

    @Test
    public void loadTask_invalidInput_exceptionThrown() {
        Storage storage = new Storage("");
        String taskDescription = "X | 0 | read book";
        try {
            storage.loadTask("X | 0 | read book");
        } catch (SerenityException e) {
            assertEquals("Error: Task cannot be loaded.",
                    e.getMessage());
        }
    }



}
