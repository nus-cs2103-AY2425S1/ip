package jeff.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.TaskList;

public class MarkCommandTest {
    private static final String TASK_DONE_ERROR = "This task has already been marked as done!";
    private static final String TASK_NUM_DONT_EXIST_ERROR = "This task number does not exist!";
    private static final String WRONG_FORMAT_ERROR =
            "The format is wrong! It should be \"mark(or m) xx\", where xx is a number.";

    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() throws JeffException {
        new File("data/tasks.txt").delete();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage.loadTaskListFromDatabase());
        new AddToDoCommand("todo read book").execute(tasks, storage);
    }

    @Test
    public void execute_markTask() throws JeffException {
        String response = new MarkCommand("mark 1").execute(tasks, storage);

        assertEquals(" OK, I've marked this task as done:\n" + "    [T][X] read book\n",
                response);
    }

    @Test
    public void execute_markTaskAlias() throws JeffException {
        String response = new MarkCommand("m 1").execute(tasks, storage);

        assertEquals(" OK, I've marked this task as done:\n" + "    [T][X] read book\n",
                response);
    }

    @Test
    public void execute_markAlreadyMarkedTask_throwException() throws JeffException {
        new MarkCommand("mark 1").execute(tasks, storage);
        Command c = new MarkCommand("mark 1");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(TASK_DONE_ERROR, exception.toString());
    }

    @Test
    public void execute_markAlreadyMarkedTaskAlias_throwException() throws JeffException {
        new MarkCommand("mark 1").execute(tasks, storage);
        Command c = new MarkCommand("m 1");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(TASK_DONE_ERROR, exception.toString());
    }

    @Test
    public void execute_markNonExistentTask_throwException() throws JeffException {
        Command c = new MarkCommand("mark 2");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(TASK_NUM_DONT_EXIST_ERROR, exception.toString());
    }

    @Test
    public void execute_markNonExistentTaskAlias_throwException() throws JeffException {
        Command c = new MarkCommand("m 2");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(TASK_NUM_DONT_EXIST_ERROR, exception.toString());
    }

    @Test
    public void execute_emptyMarkTask_throwException() throws JeffException {
        Command c = new MarkCommand("mark");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_emptyMarkTaskAlias_throwException() throws JeffException {
        Command c = new MarkCommand("m");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_noSpacingMarkTask_throwException() throws JeffException {
        Command c = new MarkCommand("mark1");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_noSpacingMarkTaskAlias_throwException() throws JeffException {
        Command c = new MarkCommand("m1");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }
}
