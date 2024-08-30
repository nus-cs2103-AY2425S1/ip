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

public class UnmarkCommandTest {
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
        new AddCommand("todo borrow book").execute(tasks, ui, storage);
        new MarkCommand("mark 1").execute(tasks, ui, storage);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void execute_unmarkTask() throws JeffException {
        new UnmarkCommand("unmark 1").execute(tasks, ui, storage);

        assertEquals("_____________________________________________________________________________________\n"
                        + "\t OK, I've marked this task as not done yet:\n"
                        + "\t   [T][ ] borrow book\n"
                        + "\t_____________________________________________________________________________________",
                outputStream.toString().trim());
    }

    @Test
    public void execute_unmarkAlreadyUnmarkedTask_throwException() throws JeffException {
        new UnmarkCommand("unmark 1").execute(tasks, ui, storage);
        Command c = new UnmarkCommand("unmark 1");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("This task has already been marked as not done yet!", exception.toString());
    }

    @Test
    public void execute_unmarkNonExistentTask_throwException() throws JeffException {
        Command c = new UnmarkCommand("unmark 2");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("This task number does not exist!", exception.toString());
    }

    @Test
    public void execute_emptyUnmarkTask_throwException() throws JeffException {
        Command c = new UnmarkCommand("unmark");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("The format is wrong! It should be \"unmark xx\", where xx is a number.",
                exception.toString());
    }

    @Test
    public void execute_wrongFormatUnmarkTask_throwException() throws JeffException {
        Command c = new UnmarkCommand("unmark1");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("The format is wrong! It should be \"unmark xx\", where xx is a number.",
                exception.toString());
    }
}
