package Citadel;

import Citadel.Commands.handleTodo;
import Citadel.Task.TaskList;
import Citadel.exception.CitadelException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void TestTodo() {
        try {
            String input = "todo sleep";
            TaskList tasks = new TaskList();
            new handleTodo(input, tasks).run();
            assertEquals("[T][ ] sleep", tasks.get(0).toString());
            tasks.get(0).markAsDone();
            assertEquals("[T][X] sleep", tasks.get(0).toString());
        } catch (CitadelException e) {
            assertEquals(true, false);
        }
    }
}
