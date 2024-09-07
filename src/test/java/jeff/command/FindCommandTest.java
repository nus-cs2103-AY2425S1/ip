package jeff.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.TaskList;

public class FindCommandTest {
    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() throws JeffException {
        new File("data/tasks.txt").delete();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage.loadTaskListFromDatabase());
        new AddToDoCommand("todo borrow book").execute(tasks, storage);
        new AddDeadlineCommand("deadline return book /by 2024-08-30 18:00").execute(tasks, storage);
        new AddEventCommand("event project meeting /from 2024-08-27 08:00 /to 2024-08-27 20:00")
                .execute(tasks, storage);
    }

    @Test
    public void execute_findWord_success() throws JeffException {
        String response = new FindCommand("find book").execute(tasks, storage);

        assertEquals(" Here are the matching tasks in your list:\n"
                        + " 1.[T][  ] borrow book\n"
                        + " 2.[D][  ] return book (by: Aug 30 2024 06:00 pm)\n",
                response);
    }

    @Test
    public void execute_findPhrase() throws JeffException {
        String response = new FindCommand("find borrow book").execute(tasks, storage);

        assertEquals(" Here are the matching tasks in your list:\n" + " 1.[T][  ] borrow book\n",
                response);
    }

    @Test
    public void execute_findIncompleteWord() throws JeffException {
        String response = new FindCommand("find boo").execute(tasks, storage);

        assertEquals(" Here are the matching tasks in your list:\n"
                        + " 1.[T][  ] borrow book\n"
                        + " 2.[D][  ] return book (by: Aug 30 2024 06:00 pm)\n",
                response);
    }

    @Test
    public void execute_findByWord_throwsException() throws JeffException {
        Command c = new FindCommand("find by");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals("Sorry, no task contains the phrase by.", exception.toString());
    }

    @Test
    public void execute_findWord_failure() throws JeffException {
        Command c = new FindCommand("find food");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals("Sorry, no task contains the phrase food.", exception.toString());
    }

    @Test
    public void execute_wrongFormat_throwsException() throws JeffException {
        Command c = new FindCommand("findfood");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals("The format is wrong! It should be \"find xx\"!", exception.toString());
    }

    @Test
    public void execute_emptyDescription_throwsException() throws JeffException {
        Command c = new FindCommand("find");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals("The format is wrong! It should be \"find xx\"!", exception.toString());
    }
}
