package dawn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTester {
    @Test
    public void delete_invalidIndex_exceptionThrown() {
        try {
            TaskList tl = new TaskList(); // empty task list which contains 0 tasks
            tl.delete("0");
        } catch (DawnException e) {
            assertEquals("Task specified does not exist!\n", e.getMessage());
        }
    }

}
