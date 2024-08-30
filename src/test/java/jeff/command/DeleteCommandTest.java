package jeff.command;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.TaskList;
import jeff.ui.Ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteCommandTest {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() throws JeffException {
        new File("data/tasks.txt").delete();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage.load());
        ui = new Ui();
        Command c = new AddCommand("todo read book");
        c.execute(tasks, ui, storage);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void execute_deleteExistingTask() throws JeffException {
        Command c = new DeleteCommand("delete 1");
        c.execute(tasks, ui, storage);

        assertEquals(0, tasks.size());
        assertEquals("_____________________________________________________________________________________\n"
                        + "\t Noted. I've removed this task:\n"
                        + "\t   [T][ ] read book\n"
                        + "\t Now you have 0 tasks in the list.\n"
                        + "\t_____________________________________________________________________________________",
                outputStream.toString().trim());
    }

    @Test
    public void execute_nonExistentTaskDelete_throwException() throws JeffException {
        Command c = new DeleteCommand("delete 2");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("This task number does not exist!", exception.toString());
        assertEquals(1, tasks.size());
    }

    @Test
    public void execute_noInputDelete_throwException() throws JeffException {
        Command c = new DeleteCommand("delete");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("The format is wrong! It should be \"delete xx\", where xx is a number.",
                exception.toString());
        assertEquals(1, tasks.size());
    }

    @Test
    public void execute_nonNumberInputDelete_throwException() throws JeffException {
        Command c = new DeleteCommand("delete hi");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("The format is wrong! It should be \"delete xx\", where xx is a number.",
                exception.toString());
        assertEquals(1, tasks.size());
    }

    @Test
    public void execute_negativeNumberDelete_throwException() throws JeffException {
        Command c = new DeleteCommand("delete -1");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("The format is wrong! It should be \"delete xx\", where xx is a number.",
                exception.toString());
        assertEquals(1, tasks.size());
    }

    @Test
    public void execute_zeroNumberDelete_throwException() throws JeffException {
        Command c = new DeleteCommand("delete 0");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("This task number does not exist!",
                exception.toString());
        assertEquals(1, tasks.size());
    }
}
