package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import commands.Command;
import exceptions.InvalidTaskException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class TaskListTest {
    @Test
    public void addTask_validTask_success() {
        // task type TODO
        Assertions.assertEquals(new Todo("test").toString(),
                new TaskList().addTask(Command.TODO, "test").toString());

        // task type DEADLINE
        Assertions.assertEquals(new Deadline("test", "2024-08-30").toString(),
                new TaskList().addTask(Command.DEADLINE, "test", "2024-08-30").toString());

        // task type EVENT
        Assertions.assertEquals(new Event("test", "2024-08-29", "2024-08-30").toString(),
                new TaskList().addTask(Command.EVENT, "test", "2024-08-29", "2024-08-30").toString());
    }

    @Test
    public void markTask_validTask_success() throws InvalidTaskException {
        // make a new TaskList with one task of each type
        TaskList list = new TaskList();
        list.addTask(new Todo("test"));
        list.addTask(new Deadline("test", "2024-08-30"));
        list.addTask(new Event("test", "2024-08-30", "2024-08-30"));

        assertEquals("X", list.markTask(1, "low").getStatusIcon());
        assertEquals("X", list.markTask(2, "low").getStatusIcon());
        assertEquals("X", list.markTask(3, "low").getStatusIcon());
    }

    @Test
    public void markTask_invalidIndex_exception() {
        try {
            assertEquals("X", new TaskList().markTask(0, "low").getStatusIcon());
            fail();
        } catch (InvalidTaskException e) {
            assertEquals("Task 0 does not exist!", e.toString());
        }
    }

    @Test
    public void unmarkTask_validTask_success() throws InvalidTaskException {
        // make a new TaskList with one task of each type
        TaskList list = new TaskList();
        list.addTask(new Todo("test"));
        list.addTask(new Deadline("test", "2024-08-30"));
        list.addTask(new Event("test", "2024-08-30", "2024-08-30"));

        assertEquals(" ", list.unmarkTask(1, "low").getStatusIcon());
        assertEquals(" ", list.unmarkTask(2, "low").getStatusIcon());
        assertEquals(" ", list.unmarkTask(3, "low").getStatusIcon());
    }

    @Test
    public void unmarkTask_invalidIndex_exception() {
        try {
            assertEquals(" ", new TaskList().unmarkTask(0, "low").getStatusIcon());
            fail();
        } catch (InvalidTaskException e) {
            assertEquals("Task 0 does not exist!", e.toString());
        }
    }

    @Test
    public void deleteTask_validTask_success() throws InvalidTaskException {
        // make a new TaskList with one task of each type
        TaskList list = new TaskList();
        list.addTask(new Todo("test"));
        list.addTask(new Deadline("test", "2024-08-30"));
        list.addTask(new Event("test", "2024-08-30", "2024-08-30"));

        assertEquals(new Todo("test").toString(),
                list.deleteTask(1, "low").toString());
        assertEquals(new Deadline("test", "2024-08-30").toString(),
                list.deleteTask(1, "low").toString());
        assertEquals(new Event("test", "2024-08-30", "2024-08-30").toString(),
                list.deleteTask(1, "low").toString());
    }

    @Test
    public void deleteTask_invalidIndex_exception() {
        try {
            Assertions.assertEquals(new Task("test"), new TaskList().deleteTask(0, "low"));
            fail();
        } catch (InvalidTaskException e) {
            assertEquals("Task 0 does not exist!", e.toString());
        }
    }

    @Test
    public void getSize_valid_success() {
        // TaskList with 0 elements
        assertEquals(0, new TaskList().getSize());

        // TaskList with 2 elements
        TaskList list = new TaskList();
        list.addTask(new Task("test1"));
        list.addTask(new Task("test2"));
        assertEquals(2, list.getSize());
    }

    @Test
    public void listTasks_valid_success() {
        // empty TaskList
        assertEquals("", new TaskList().listTasks(new ArrayList<>()));

        // ArrayList with a Todo task
        TaskList list1 = new TaskList();
        ArrayList<Task> todos = new ArrayList<>();
        todos.add(new Todo("test"));
        assertEquals("1. [T][ ] test\n", list1.listTasks(todos));

        // ArrayList with a Deadline task
        TaskList list2 = new TaskList();
        ArrayList<Task> deadlines = new ArrayList<>();
        deadlines.add(new Deadline("test", "2024-08-30"));
        assertEquals("1. [D][ ] test (by: Aug 30 2024)\n", list2.listTasks(deadlines));

        // ArrayList with an Event task
        TaskList list3 = new TaskList();
        ArrayList<Task> events = new ArrayList<>();
        events.add(new Event("test", "2024-08-30", "2024-08-30"));
        assertEquals("1. [E][ ] test (from: Aug 30 2024 to: Aug 30 2024)\n", list3.listTasks(events));

        // ArrayList with muliple tasks
        TaskList list4 = new TaskList();
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("test"));
        tasks.add(new Deadline("test", "2024-08-30"));
        tasks.add(new Event("test", "2024-08-30", "2024-08-30"));
        assertEquals("1. [T][ ] test\n"
                        + "2. [D][ ] test (by: Aug 30 2024)\n"
                        + "3. [E][ ] test (from: Aug 30 2024 to: Aug 30 2024)\n",
                list4.listTasks(tasks));
    }

    @Test
    public void getTask_validIndex_success() {
        Todo todo = new Todo("test");
        Deadline deadline = new Deadline("test", "2024-08-30");
        Event event = new Event("test", "2024-08-30", "2024-08-30");
        TaskList list = new TaskList();
        list.addTask(todo);
        list.addTask(deadline);
        list.addTask(event);

        assertEquals(todo, list.getTask(0));
        assertEquals(deadline, list.getTask(1));
        assertEquals(event, list.getTask(2));
    }
}
