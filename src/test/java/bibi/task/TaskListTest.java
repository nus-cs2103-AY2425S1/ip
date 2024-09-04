package bibi.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addToTaskList_addTaskSubclass_success() {
        TaskList tasks = new TaskList();

        // Add ToDo Task to tasklist
        tasks.addToTaskList(new ToDo("todo"));
        assertEquals(ToDo.class, tasks.getTask(0).getClass());

        // Add Deadline Task to tasklist
        tasks.addToTaskList(new Deadline("deadline", "2014-01-10"));
        assertEquals(Deadline.class, tasks.getTask(1).getClass());

        // Add Event Task to tasklist
        tasks.addToTaskList(new Event("event", "now", "later"));
        assertEquals(Event.class, tasks.getTask(2).getClass());
    }

    @Test
    public void removeFromTaskList_removeWhenEmptyListOrInvalidIndex_returnNull() {
        TaskList tasks = new TaskList();

        // Remove task from empty tasklist
        assertNull(tasks.removeFromTaskList(1));

        // Remove task with invalid index
        assertNull(tasks.removeFromTaskList(-1));
    }

    @Test
    public void removeFromTaskList_removeValidTask_returnTask() {
        TaskList tasks = new TaskList();

        // Populate tasklist
        Task t1 = new ToDo("task");
        Task t2 = new Deadline("deadline", "2014-01-10");
        Task t3 = new Event("event", "now", "later");
        tasks.addToTaskList(t1);
        tasks.addToTaskList(t2);
        tasks.addToTaskList(t3);

        assertEquals(t1, tasks.removeFromTaskList(1));
        assertEquals(t2, tasks.removeFromTaskList(1));
        assertEquals(t3, tasks.removeFromTaskList(1));
    }
}
