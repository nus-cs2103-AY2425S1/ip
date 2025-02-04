package dgpt.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void markTask_markToDoTask_success() {
        Task t = new ToDo("test");
        List<Task> lt = new ArrayList<>();
        lt.add(t);
        TaskList tl = new TaskList(lt);
        assertEquals("[T][X] test", tl.markTask(0).toString());
    }
}
