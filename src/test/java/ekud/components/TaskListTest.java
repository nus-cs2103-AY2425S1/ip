package ekud.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ekud.exceptions.EkudException;
import ekud.task.Task;

public class TaskListTest {
    private static class TaskStub extends Task {
        public TaskStub(String description) throws EkudException {
            super(description);
        }

        @Override
        public String getEmptyDescriptionErrorMessage() {
            return "Emtpy Description";
        }
    }

    @Test
    public void addTask() throws EkudException {
        Task t = new TaskStub("1");
        TaskList list = new TaskList();

        list.addTask(t);
        assertEquals(1, list.getCount());
    }

    @Test
    public void removeTask() throws EkudException {
        Task t1 = new TaskStub("1");
        Task t2 = new TaskStub("2");
        TaskList list = new TaskList();

        list.addTask(t1);
        list.addTask(t2);

        Task del = list.removeTask(0);
        // decrease task count
        assertEquals(1, list.getCount());
        // removed 0th item is the same as the first task added
        assertEquals(t1, del);
        // remaining item in 0th index is not the old 0th item
        assertEquals(t2, list.getTask(0));
    }

    @Test
    public void isAllComplete() throws EkudException {
        Task t = new TaskStub("1");
        TaskList list = new TaskList();

        list.addTask(t);
        assertFalse(list::isAllComplete);

        t.markAsDone();
        assertTrue(list::isAllComplete);
    }


}
