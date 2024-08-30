package julie.command;
import julie.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {
    @Test
    public void todo_ListTest() {
        List<Task> taskList = new ArrayList<>();
        TodoCommand c = new TodoCommand("todo help");
        c.run(taskList);
        assertEquals("[[T] [ ] help]", taskList.toString());
    }
}
