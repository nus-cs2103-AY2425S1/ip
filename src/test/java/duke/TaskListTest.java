package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Contains methods to test the TaskList Class.
 */
public class TaskListTest {

    private static final String REPLY_INVALID_TASK_NUMBER = "There is no task with the given task number.";
    private static final String REPLY_UNMARKED = "OK, I've marked this task as not done yet:\n ";

    @Test
    public void add_validInput_correctNumberOfTasks() {
        TaskList list = new TaskList(new Task[100], 0);
        list.add(new Todo("quiz"));
        list.add(new Todo("tutorial"));
        list.add(new Todo("homework"));

        assertEquals(3, list.getNumberOfTasks());
    }

    @Test
    public void delete_validInput_correctNumberOfTasks() {
        TaskList list = new TaskList(new Task[100], 0);

        list.add(new Todo("quiz"));
        list.add(new Todo("tutorial"));
        list.add(new Todo("homework"));
        assertEquals(3, list.getNumberOfTasks());

        list.delete(1);
        assertEquals(2, list.getNumberOfTasks());

        list.add(new Todo("lecture"));
        list.add(new Todo("problem set"));
        assertEquals(4, list.getNumberOfTasks());
    }

    @Test
    public void delete_invalidTaskNumber_unsucessful() {
        TaskList list = new TaskList(new Task[100], 0);

        list.add(new Todo("quiz"));
        list.add(new Todo("tutorial"));
        list.add(new Todo("homework"));

        assertEquals(REPLY_INVALID_TASK_NUMBER, list.delete(5));
    }

    @Test
    public void unmark_validInput_sucessful() {
        TaskList list = new TaskList(new Task[100], 0);

        list.add(new Todo("quiz"));
        list.add(new Todo("tutorial"));
        list.add(new Todo("homework"));

        assertEquals(REPLY_UNMARKED + list.get(0), list.unmark(1));

        list.mark(1);
        list.unmark(1);
        assertEquals(REPLY_UNMARKED + list.get(0), list.unmark(1));
    }

    @Test
    public void mark_validInput_sucessful() {
        TaskList list = new TaskList(new Task[100], 0);

        list.add(new Todo("quiz"));
        list.add(new Todo("tutorial"));
        list.add(new Todo("homework"));

        list.mark(1);
        list.unmark(1);
        assertEquals(REPLY_UNMARKED + list.get(0), list.unmark(1));
    }
}