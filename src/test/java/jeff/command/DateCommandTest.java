package jeff.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.TaskList;

public class DateCommandTest {
    private static final String WRONG_FORMAT_ERROR = "The format is wrong! It should be \"date(or dt) yyyy-mm-dd\"!";

    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() throws JeffException {
        new File("data/tasks.txt").delete();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage.loadTaskListFromDatabase());
        new AddToDoCommand("todo borrow book").execute(tasks, storage);
        new AddDeadlineCommand("deadline return book /by 2030-08-30 18:00").execute(tasks, storage);
        new AddEventCommand("event project meeting /from 2030-08-27 08:00 /to 2030-08-27 20:00")
                .execute(tasks, storage);
    }

    @Test
    public void execute_deadlineDate() throws JeffException {
        String response = new DateCommand("date 2030-08-30").execute(tasks, storage);

        assertEquals(" Here are the tasks for 30 Aug 2030:\n"
                        + " 1.[D][  ] return book (by: Aug 30 2030 06:00 pm)\n",
                response);
    }

    @Test
    public void execute_deadlineDateAlias() throws JeffException {
        String response = new DateCommand("dt 2030-08-30").execute(tasks, storage);

        assertEquals(" Here are the tasks for 30 Aug 2030:\n"
                        + " 1.[D][  ] return book (by: Aug 30 2030 06:00 pm)\n",
                response);
    }

    @Test
    public void execute_eventDate() throws JeffException {
        String response = new DateCommand("date 2030-08-27").execute(tasks, storage);

        assertEquals(" Here are the tasks for 27 Aug 2030:\n"
                        + " 1.[E][  ] project meeting (from: Aug 27 2030 08:00 am to: Aug 27 2030 08:00 pm)\n",
                response);
    }

    @Test
    public void execute_eventDateAlias() throws JeffException {
        String response = new DateCommand("dt 2030-08-27").execute(tasks, storage);

        assertEquals(" Here are the tasks for 27 Aug 2030:\n"
                        + " 1.[E][  ] project meeting (from: Aug 27 2030 08:00 am to: Aug 27 2030 08:00 pm)\n",
                response);
    }

    @Test
    public void execute_randomDate_throwException() throws JeffException {
        Command c = new DateCommand("date 2030-08-28");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals("No deadlines/events on 28 Aug 2030!", exception.toString());
    }

    @Test
    public void execute_randomDateAlias_throwException() throws JeffException {
        Command c = new DateCommand("dt 2030-08-28");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals("No deadlines/events on 28 Aug 2030!", exception.toString());
    }

    @Test
    public void execute_empty_throwException() throws JeffException {
        Command c = new DateCommand("date");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_emptyAlias_throwException() throws JeffException {
        Command c = new DateCommand("dt");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_wrongFormat_throwException() throws JeffException {
        Command c = new DateCommand("date 28 Aug");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_wrongFormatAlias_throwException() throws JeffException {
        Command c = new DateCommand("dt 28 Aug");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_noSpace_throwsException() throws JeffException {
        Command c = new DateCommand("date28Aug");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_noSpaceAlias_throwsException() throws JeffException {
        Command c = new DateCommand("dt28Aug");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }
}
