package jeff.command;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.DeadlineTask;
import jeff.task.EventTask;
import jeff.task.TaskList;
import jeff.task.ToDoTask;
import jeff.ui.Ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class AddCommandTest {
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
    public void execute_addToDoTask() throws JeffException {
        Command c = new AddCommand("todo read book");
        c.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertInstanceOf(ToDoTask.class, tasks.get(0));
        assertEquals("[T][ ] read book", tasks.get(0).toString());
        assertEquals("_____________________________________________________________________________________\n"
                        + "\t Got it. I've added this task:\n"
                        + "\t   [T][ ] read book\n"
                        + "\t Now you have 1 tasks in the list.\n"
                        + "\t_____________________________________________________________________________________",
                outputStream.toString().trim());
    }

    @Test
    public void execute_addDeadlineTask24HourClock() throws JeffException {
        Command c = new AddCommand("deadline return book /by 2024-08-30 18:00");
        c.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertInstanceOf(DeadlineTask.class, tasks.get(0));
        assertEquals("[D][ ] return book (by: Aug 30 2024 06:00 pm)", tasks.get(0).toString());
        assertEquals("_____________________________________________________________________________________\n"
                        + "\t Got it. I've added this task:\n"
                        + "\t   [D][ ] return book (by: Aug 30 2024 06:00 pm)\n"
                        + "\t Now you have 1 tasks in the list.\n"
                        + "\t_____________________________________________________________________________________",
                outputStream.toString().trim());
    }

    @Test
    public void execute_addDeadlineTask12HourClock() throws JeffException {
        Command c = new AddCommand("deadline return book /by 2024-08-30 06:00 pm");
        c.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertInstanceOf(DeadlineTask.class, tasks.get(0));
        assertEquals("[D][ ] return book (by: Aug 30 2024 06:00 pm)", tasks.get(0).toString());
        assertEquals("_____________________________________________________________________________________\n"
                        + "\t Got it. I've added this task:\n"
                        + "\t   [D][ ] return book (by: Aug 30 2024 06:00 pm)\n"
                        + "\t Now you have 1 tasks in the list.\n"
                        + "\t_____________________________________________________________________________________",
                outputStream.toString().trim());
    }

    @Test
    public void execute_addEventTask24HourClock() throws JeffException {
        Command c = new AddCommand("event project meeting /from 2024-08-27 08:00 /to 2024-08-27 20:00");
        c.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertInstanceOf(EventTask.class, tasks.get(0));
        assertEquals("[E][ ] project meeting (from: Aug 27 2024 08:00 am to: Aug 27 2024 08:00 pm)",
                tasks.get(0).toString());
        assertEquals("_____________________________________________________________________________________\n"
                        + "\t Got it. I've added this task:\n"
                        + "\t   [E][ ] project meeting (from: Aug 27 2024 08:00 am to: Aug 27 2024 08:00 pm)\n"
                        + "\t Now you have 1 tasks in the list.\n"
                        + "\t_____________________________________________________________________________________",
                outputStream.toString().trim());
    }

    @Test
    public void execute_addEventTask12HourClock() throws JeffException {
        Command c = new AddCommand("event project meeting /from 2024-08-27 08:00 am /to 2024-08-27 08:00 pm");
        c.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertInstanceOf(EventTask.class, tasks.get(0));
        assertEquals("[E][ ] project meeting (from: Aug 27 2024 08:00 am to: Aug 27 2024 08:00 pm)",
                tasks.get(0).toString());
        assertEquals("_____________________________________________________________________________________\n"
                        + "\t Got it. I've added this task:\n"
                        + "\t   [E][ ] project meeting (from: Aug 27 2024 08:00 am to: Aug 27 2024 08:00 pm)\n"
                        + "\t Now you have 1 tasks in the list.\n"
                        + "\t_____________________________________________________________________________________",
                outputStream.toString().trim());
    }

    @Test
    public void execute_invalidToDoTask_throwsException() throws JeffException {
        Command c = new AddCommand("todo");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("Sorry, the description of a todo cannot be empty!", exception.toString());
    }

    @Test
    public void execute_invalidDeadlineTask_throwsException() throws JeffException {
        Command c = new AddCommand("deadline");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("Sorry, the description of a deadline cannot be empty!", exception.toString());
    }

    @Test
    public void execute_invalidEventTask_throwsException() throws JeffException {
        Command c = new AddCommand("event");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("Sorry, the description of a event cannot be empty!", exception.toString());
    }

    @Test
    public void execute_wrongFormatDeadlineTask_throwsException() throws JeffException {
        // Needs to be HH:mm and not H:mm
        Command c = new AddCommand("deadline return book /by 2024-08-30 8:00");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals("The format is wrong! It should be \"deadline xx /by yyyy-mm-dd HH:mm or hh:mm AM/PM\"!",
                exception.toString());
    }

    @Test
    public void execute_wrongFormatEventTask_throwsException() throws JeffException {
        // Needs to be am/pm and not AM/PM
        Command c = new AddCommand("event project meeting /from 2024-08-27 08:00 AM /to 2024-08-27 08:00 PM");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, ui, storage));
        assertEquals(
                "The format is wrong! It should be \"event xx /from yyyy-mm-dd HH:mm /to yyyy-mm--dd HH:mm\"!",
                exception.toString());
    }
}
