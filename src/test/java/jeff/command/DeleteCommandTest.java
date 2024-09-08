package jeff.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.TaskList;

public class DeleteCommandTest {
    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() throws JeffException {
        new File("data/tasks.txt").delete();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage.loadTaskListFromDatabase());
        Command c = new AddToDoCommand("todo read book");
        c.execute(tasks, storage);
    }

    @Test
    public void execute_deleteExistingTask() throws JeffException {
        Command c = new DeleteCommand("delete 1");
        String response = c.execute(tasks, storage);

        assertEquals(0, tasks.size());
        assertEquals(" Noted. I've removed this task:\n"
                        + "    [T][  ] read book\n"
                        + " Now you have 0 tasks in the list.\n",
                response);
    }

    @Test
    public void execute_nonExistentTaskDelete_throwException() throws JeffException {
        Command c = new DeleteCommand("delete 2");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals("This task number does not exist!", exception.toString());
        assertEquals(1, tasks.size());
    }

    @Test
    public void execute_noInputDelete_throwException() throws JeffException {
        Command c = new DeleteCommand("delete");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals("The format is wrong! It should be \"delete xx\", where xx is a number.",
                exception.toString());
        assertEquals(1, tasks.size());
    }

    @Test
    public void execute_nonNumberInputDelete_throwException() throws JeffException {
        Command c = new DeleteCommand("delete hi");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals("The format is wrong! It should be \"delete xx\", where xx is a number.",
                exception.toString());
        assertEquals(1, tasks.size());
    }

    @Test
    public void execute_negativeNumberDelete_throwException() throws JeffException {
        Command c = new DeleteCommand("delete -1");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals("The format is wrong! It should be \"delete xx\", where xx is a number.",
                exception.toString());
        assertEquals(1, tasks.size());
    }

    @Test
    public void execute_zeroNumberDelete_throwException() throws JeffException {
        Command c = new DeleteCommand("delete 0");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals("This task number does not exist!",
                exception.toString());
        assertEquals(1, tasks.size());
    }
}
