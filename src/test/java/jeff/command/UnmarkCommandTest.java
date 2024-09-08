package jeff.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.TaskList;

public class UnmarkCommandTest {
    private static final String TASK_NOT_DONE_ERROR = "This task has already been marked as not done yet!";
    private static final String TASK_NUM_DONT_EXIST_ERROR = "This task number does not exist!";
    private static final String WRONG_FORMAT_ERROR =
            "The format is wrong! It should be \"unmark(or u) xx\", where xx is a number.";

    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() throws JeffException {
        new File("data/tasks.txt").delete();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage.loadTaskListFromDatabase());
        new AddToDoCommand("todo borrow book").execute(tasks, storage);
        new MarkCommand("mark 1").execute(tasks, storage);
    }

    @Test
    public void execute_unmarkTask() throws JeffException {
        String response = new UnmarkCommand("unmark 1").execute(tasks, storage);

        assertEquals(" OK, I've marked this task as not done yet:\n" + "    [T][  ] borrow book\n",
                response);
    }

    @Test
    public void execute_unmarkTaskAlias() throws JeffException {
        String response = new UnmarkCommand("u 1").execute(tasks, storage);

        assertEquals(" OK, I've marked this task as not done yet:\n" + "    [T][  ] borrow book\n",
                response);
    }

    @Test
    public void execute_unmarkAlreadyUnmarkedTask_throwException() throws JeffException {
        new UnmarkCommand("unmark 1").execute(tasks, storage);
        Command c = new UnmarkCommand("unmark 1");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(TASK_NOT_DONE_ERROR, exception.toString());
    }

    @Test
    public void execute_unmarkAlreadyUnmarkedTaskAlias_throwException() throws JeffException {
        new UnmarkCommand("unmark 1").execute(tasks, storage);
        Command c = new UnmarkCommand("u 1");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(TASK_NOT_DONE_ERROR, exception.toString());
    }

    @Test
    public void execute_unmarkNonExistentTask_throwException() throws JeffException {
        Command c = new UnmarkCommand("unmark 2");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(TASK_NUM_DONT_EXIST_ERROR, exception.toString());
    }

    @Test
    public void execute_unmarkNonExistentTaskAlias_throwException() throws JeffException {
        Command c = new UnmarkCommand("u 2");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(TASK_NUM_DONT_EXIST_ERROR, exception.toString());
    }

    @Test
    public void execute_emptyUnmarkTask_throwException() throws JeffException {
        Command c = new UnmarkCommand("unmark");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_emptyUnmarkTaskAlias_throwException() throws JeffException {
        Command c = new UnmarkCommand("u");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_wrongFormatUnmarkTask_throwException() throws JeffException {
        Command c = new UnmarkCommand("unmark1");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_wrongFormatUnmarkTaskAlias_throwException() throws JeffException {
        Command c = new UnmarkCommand("u1");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }
}
