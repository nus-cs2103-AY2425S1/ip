package moimoi.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import moimoi.util.task.Task;
import moimoi.util.task.Todo;

public class TaskListTest {

    private TaskList tasks;

    @Test
    public void testAdd() {
        this.tasks = new TaskList(new ArrayList<Task>());
        this.tasks.add(new Todo("dummy1"));
        this.tasks.add(new Todo("dummy2"));
        this.tasks.add(new Todo("dummy3"));

        assertEquals(3, this.tasks.getSize());
    }

    @Test
    public void testDelete() {
        this.tasks = new TaskList(new ArrayList<Task>());
        this.tasks.add(new Todo("dummy1"));
        this.tasks.add(new Todo("dummy2"));
        this.tasks.add(new Todo("dummy3"));

        this.tasks.delete(2);
        assertEquals(2, this.tasks.getSize());

        this.tasks.delete(2);
        assertEquals(1, this.tasks.getSize());
    }

}
