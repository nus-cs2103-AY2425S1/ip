package reo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void markTask_validIndex() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Test Task", false));
        TaskList tl = new TaskList(tasks);
        tl.markTask(0);
        String expected = "[T] [X] Test Task";
        assertEquals(tasks.get(0).toString(), expected);
    }

    @Test
    public void markTask_invalidIndex_exceptionThrown() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Test Task", false));
        TaskList tl = new TaskList(tasks);
        IndexOutOfBoundsException thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.markTask(1);
        });
    }
}
