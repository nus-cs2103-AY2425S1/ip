import org.junit.jupiter.api.Test;
import task.TaskList;
import task.ToDos;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {
    @Test
    public void checkTaskList() {
        TaskList list = new TaskList();
        int initial = list.getTasks().size();
        list.addTask(new ToDos("eat"));
        int after = list.getTasks().size();
        assertEquals(after, initial + 1);
    }

}
