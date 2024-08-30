package BMO.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void saveFormatTest() {
        Task task = new ToDo("debugging 2103 code");
        assertEquals(task.getSavedFormat(),
            "T | 0 | debugging 2103 code\n" );
    }
}
