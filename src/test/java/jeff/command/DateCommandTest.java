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

public class DateCommandTest {
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
        new AddCommand("deadline return book /by 2024-08-30 18:00").execute(tasks, ui, storage);
        new AddCommand("event project meeting /from 2024-08-27 08:00 /to 2024-08-27 20:00")
                .execute(tasks, ui, storage);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void execute_deadlineDate() throws JeffException {
        new DateCommand("task 2024-08-30").execute(tasks, ui, storage);

        assertEquals("_____________________________________________________________________________________\n"
                        + "\t 1.[D][ ] return book (by: Aug 30 2024 06:00 pm)\n"
                        + "\t_____________________________________________________________________________________",
                outputStream.toString().trim());
    }

    @Test
    public void execute_eventDate() throws JeffException {
        new DateCommand("task 2024-08-27").execute(tasks, ui, storage);

        assertEquals("_____________________________________________________________________________________\n"
                        + "\t 1.[E][ ] project meeting (from: Aug 27 2024 08:00 am to: Aug 27 2024 08:00 pm)\n"
                        + "\t_____________________________________________________________________________________",
                outputStream.toString().trim());
    }

    @Test
    public void execute_randomDate_throwException() throws JeffException {
        Command c = new DateCommand("task 2024-08-28");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("No deadlines/events on 2024-08-28!", exception.toString());
    }

    @Test
    public void execute_empty_throwException() throws JeffException {
        Command c = new DateCommand("task");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("The format is wrong! It should be \"task yyyy-mm-dd\"!", exception.toString());
    }

    @Test
    public void execute_wrongFormat_throwException() throws JeffException {
        Command c = new DateCommand("task 28 Aug");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("The format is wrong! It should be \"task yyyy-mm-dd\"!", exception.toString());
    }
}
