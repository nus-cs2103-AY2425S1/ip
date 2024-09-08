package jeff.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.DeadlineTask;
import jeff.task.TaskList;

public class AddDeadlineCommandTest {
    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() throws JeffException {
        new File("data/tasks.txt").delete();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage.loadTaskListFromDatabase());
    }

    @Test
    public void execute_addDeadlineTask24HourClock() throws JeffException {
        Command c = new AddDeadlineCommand("deadline return book /by 2024-08-30 18:00");
        String response = c.execute(tasks, storage);

        assertEquals(1, tasks.size());
        assertInstanceOf(DeadlineTask.class, tasks.getTaskByIndex(0));
        assertEquals("[D][  ] return book (by: Aug 30 2024 06:00 pm)", tasks.getTaskByIndex(0).toString());
        assertEquals(" Got it. I've added this task:\n"
                        + "    [D][  ] return book (by: Aug 30 2024 06:00 pm)\n"
                        + " Now you have 1 tasks in the list.\n",
                response);
    }

    @Test
    public void execute_addDeadlineTask12HourClock() throws JeffException {
        Command c = new AddDeadlineCommand("deadline return book /by 2024-08-30 06:00 pm");
        String response = c.execute(tasks, storage);

        assertEquals(1, tasks.size());
        assertInstanceOf(DeadlineTask.class, tasks.getTaskByIndex(0));
        assertEquals("[D][  ] return book (by: Aug 30 2024 06:00 pm)", tasks.getTaskByIndex(0).toString());
        assertEquals(" Got it. I've added this task:\n"
                        + "    [D][  ] return book (by: Aug 30 2024 06:00 pm)\n"
                        + " Now you have 1 tasks in the list.\n",
                response);
    }

    @Test
    public void execute_invalidDeadlineTask_throwsException() throws JeffException {
        Command c = new AddDeadlineCommand("deadline");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals("Sorry, the description of a deadline cannot be empty!", exception.toString());
    }

    @Test
    public void execute_wrongFormatDeadlineTask_throwsException() throws JeffException {
        // Needs to be HH:mm and not H:mm
        Command c = new AddDeadlineCommand("deadline return book /by 2024-08-30 8:00");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals("The format is wrong! It should be \"deadline xx /by yyyy-mm-dd HH:mm or hh:mm AM/PM\"!",
                exception.toString());
    }
}
