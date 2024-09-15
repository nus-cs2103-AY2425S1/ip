package pikappi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import pikappi.exception.PikappiException;
import pikappi.task.DeadlineTask;
import pikappi.task.EventTask;
import pikappi.task.TodoTask;

public class TaskListTest {
    @Test
    public void markTask_validIndex_success() throws PikappiException {
        TaskList tasks = new TaskList();
        tasks.addTask(new TodoTask("test"));
        assertEquals("Pika! I've marked this task as done:\n"
                + "[T][X] test", tasks.markTask(1));
    }

    @Test
    public void markTask_invalidIndex_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.addTask(new TodoTask("test"));
            assertEquals("none", tasks.markTask(3));
            fail();
        } catch (PikappiException e) {
            assertEquals("Pi-ka..?? Task does not exist..", e.getMessage());
        }
    }

    @Test
    public void unmarkTask_validIndex_success() throws PikappiException {
        TaskList tasks = new TaskList();
        tasks.addTask(new TodoTask("test"));
        assertEquals("kaPi! I've unmarked this task as not done yet:\n"
                + "[T][ ] test", tasks.unmarkTask(1));
    }

    @Test
    public void deleteTask_validIndex_success() throws PikappiException {
        TaskList tasks = new TaskList();
        tasks.addTask(new TodoTask("test"));
        assertEquals("Pipi-ka-pi! I've removed this task:\n"
                + " [T][ ] test\n"
                + "Now you have 0 tasks in the list.", tasks.deleteTask(1));
    }

    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.addTask(new TodoTask("test"));
            assertEquals("none", tasks.deleteTask(3));
            fail();
        } catch (PikappiException e) {
            assertEquals("Pi-ka..?? Task does not exist..", e.getMessage());
        }
    }

    @Test
    public void findTask_validKeyword_success() throws PikappiException {
        TaskList tasks = new TaskList();
        tasks.addTask(new TodoTask("test"));
        tasks.addTask(new TodoTask("test2"));
        tasks.addTask(new TodoTask("should not be included"));
        assertEquals("Here are the tasks in your list:\n"
                + "1.[T][ ] test\n"
                + "2.[T][ ] test2\n", tasks.findTask("test").listTasks());
    }

    @Test
    public void findTask_multipleValidKeyword_success() throws PikappiException {
        TaskList tasks = new TaskList();
        tasks.addTask(new TodoTask("test"));
        tasks.addTask(new TodoTask("test2"));
        tasks.addTask(new TodoTask("should not be included"));
        tasks.addTask(new TodoTask("another one should be included"));
        assertEquals("Here are the tasks in your list:\n"
                + "1.[T][ ] test\n"
                + "2.[T][ ] test2\n"
                + "3.[T][ ] another one should be included\n",
                tasks.findTask("test", "another").listTasks());
    }

    @Test
    public void addTask_duplicatedTasks_success() throws PikappiException {
        try {
            TaskList tasks = new TaskList();
            tasks.addTask(new TodoTask("test"));
            assertEquals("none", tasks.addTask(new TodoTask("test")));
            fail();
        } catch (PikappiException e) {
            assertEquals("Pi-ka..?? Task already exists..", e.getMessage());
        }
    }

    @Test
    public void sortTask_byDescription_success() throws PikappiException {
        TaskList tasks = new TaskList();
        tasks.addTask(new TodoTask("test"));
        tasks.addTask(new TodoTask("test2"));
        tasks.addTask(new DeadlineTask("one test", "2021-08-25 1800"));
        tasks.addTask(new EventTask("should be included", "2021-08-25 1800", "2021-08-25 1900"));
        tasks.addTask(new TodoTask("another one should be included"));
        tasks.sortTasks("description");
        assertEquals("Here are the tasks in your list:\n"
                        + "1.[T][ ] another one should be included\n"
                        + "2.[D][ ] one test (by: Aug 25 2021 6:00PM)\n"
                        + "3.[E][ ] should be included (from: Aug 25 2021 6:00PM to: Aug 25 2021 7:00PM)\n"
                        + "4.[T][ ] test\n"
                        + "5.[T][ ] test2\n",
                tasks.listTasks());
    }

    @Test
    public void sortTask_byTaskType_success() throws PikappiException {
        TaskList tasks = new TaskList();
        tasks.addTask(new TodoTask("test"));
        tasks.addTask(new TodoTask("test2"));
        tasks.addTask(new DeadlineTask("one test", "2021-08-25 1800"));
        tasks.addTask(new EventTask("should be included", "2021-08-25 1800", "2021-08-25 1900"));
        tasks.addTask(new TodoTask("another one should be included"));
        tasks.sortTasks("tasktype");
        assertEquals("Here are the tasks in your list:\n"
                        + "1.[D][ ] one test (by: Aug 25 2021 6:00PM)\n"
                        + "2.[E][ ] should be included (from: Aug 25 2021 6:00PM to: Aug 25 2021 7:00PM)\n"
                        + "3.[T][ ] test\n"
                        + "4.[T][ ] test2\n"
                        + "5.[T][ ] another one should be included\n",
                tasks.listTasks());
    }

    @Test
    public void sortTask_invalidSortOption_exceptionThrown() throws PikappiException {
        try {
            TaskList tasks = new TaskList();
            tasks.addTask(new TodoTask("test"));
            tasks.addTask(new TodoTask("test2"));
            tasks.addTask(new DeadlineTask("one test", "2021-08-25 1800"));
            tasks.addTask(new EventTask("should be included", "2021-08-25 1800", "2021-08-25 1900"));
            tasks.addTask(new TodoTask("another one should be included"));
            tasks.sortTasks("invalid");
            assertEquals("none", tasks.listTasks());
            fail();
        } catch (PikappiException e) {
            assertEquals("Pi-ka..?? Invalid sort option..", e.getMessage());
        }
    }
}
