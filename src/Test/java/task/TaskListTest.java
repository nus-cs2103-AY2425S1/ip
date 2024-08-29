package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    @Test
    public void addToDo_randomInput_expectedOutcome() throws Exception {
        String description = "Test task description";
        TaskList tl = new TaskList();
        Task result = tl.addToDo(description);

        assertNotNull(result);

        assertTrue(result instanceof ToDo);

        assertEquals(description, result.getDescription());
    }
}
