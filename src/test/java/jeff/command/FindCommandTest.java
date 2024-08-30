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

public class FindCommandTest {
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
    public void execute_findWord_success() throws JeffException {
        new FindCommand("find book").execute(tasks, ui, storage);

        assertEquals("_____________________________________________________________________________________\n"
                        + "\t Here are the matching tasks in your list:\n"
                        + "\t 1.[T][ ] borrow book\n"
                        + "\t 2.[D][ ] return book (by: Aug 30 2024 06:00 pm)\n"
                        + "\t_____________________________________________________________________________________",
                outputStream.toString().trim());
    }

    @Test
    public void execute_findPhrase() throws JeffException {
        new FindCommand("find borrow book").execute(tasks, ui, storage);

        assertEquals("_____________________________________________________________________________________\n"
                        + "\t Here are the matching tasks in your list:\n"
                        + "\t 1.[T][ ] borrow book\n"
                        + "\t_____________________________________________________________________________________",
                outputStream.toString().trim());
    }

    @Test
    public void execute_findIncompleteWord() throws JeffException {
        new FindCommand("find boo").execute(tasks, ui, storage);

        assertEquals("_____________________________________________________________________________________\n"
                        + "\t Here are the matching tasks in your list:\n"
                        + "\t 1.[T][ ] borrow book\n"
                        + "\t 2.[D][ ] return book (by: Aug 30 2024 06:00 pm)\n"
                        + "\t_____________________________________________________________________________________",
                outputStream.toString().trim());
    }

    @Test
    public void execute_findByWord_throwsException() throws JeffException {
        Command c = new FindCommand("find by");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("Sorry, no task contains the phrase by.", exception.toString());
    }

    @Test
    public void execute_findWord_failure() throws JeffException {
        Command c = new FindCommand("find food");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("Sorry, no task contains the phrase food.", exception.toString());
    }

    @Test
    public void execute_wrongFormat_throwsException() throws JeffException {
        Command c = new FindCommand("findfood");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("The format is wrong! It should be \"find xx\"!", exception.toString());
    }

    @Test
    public void execute_emptyDescription_throwsException() throws JeffException {
        Command c = new FindCommand("find");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("The format is wrong! It should be \"find xx\"!", exception.toString());
    }
}