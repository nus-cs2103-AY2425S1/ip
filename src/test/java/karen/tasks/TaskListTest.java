package karen.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TaskListTest {

    @Test
    public void toTaskStrings_emptyTaskList_emptyStringArray() {
        TaskList taskList = new TaskList();
        assertArrayEquals(new String[] {}, taskList.toTaskStrings());
    }

    @Test
    public void toTaskStrings_populatedTaskList_validStringArray() {
        TaskList taskList = new TaskList();
        Todo td = new Todo("todo1");
        Deadline dl = new Deadline("dl1", "2024-10-11 1200");
        Event ev = new Event("event1", "2024-10-11 1000", "2025-11-12 1800");
        taskList.addTask(td);
        taskList.addTask(dl);
        taskList.addTask(ev);

        String[] expected = {
                "[T][ ] todo1",
                "[D][ ] dl1 (by: Oct 11 2024 12:00PM)",
                "[E][ ] event1 (from: Oct 11 2024 10:00AM to: Nov 12 2025 06:00PM)"
        };

        assertArrayEquals(expected, taskList.toTaskStrings());
    }
}
