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

public class ListCommandTest {
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
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void execute_nonEmptyList() throws JeffException {
        new AddCommand("todo borrow book").execute(tasks, ui, storage);
        new AddCommand("deadline return book /by 2024-08-30 18:00").execute(tasks, ui, storage);
        new AddCommand("event project meeting /from 2024-08-27 08:00 /to 2024-08-27 20:00")
                .execute(tasks, ui, storage);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        new ListCommand("list").execute(tasks, ui, storage);

        assertEquals("_____________________________________________________________________________________\n"
                        + "\t 1.[T][ ] borrow book\n"
                        + "\t 2.[D][ ] return book (by: Aug 30 2024 06:00 pm)\n"
                        + "\t 3.[E][ ] project meeting (from: Aug 27 2024 08:00 am to: Aug 27 2024 08:00 pm)\n"
                        + "\t_____________________________________________________________________________________",
                outputStream.toString().trim());
    }

    @Test
    public void execute_emptyList_throwException() throws JeffException {
        Command c = new ListCommand("list");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("List is empty!", exception.toString());
    }
}
