package jeff.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.EventTask;
import jeff.task.TaskList;

public class AddEventCommandTest {
    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() throws JeffException {
        new File("data/tasks.txt").delete();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage.loadTaskListFromDatabase());
    }

    @Test
    public void execute_addEventTask24HourClock() throws JeffException {
        Command c = new AddEventCommand(
                "event project meeting /from 2024-08-27 08:00 /to 2024-08-27 20:00"
        );
        String response = c.execute(tasks, storage);

        assertEquals(1, tasks.size());
        assertInstanceOf(EventTask.class, tasks.getTaskByIndex(0));
        assertEquals("[E][  ] project meeting (from: Aug 27 2024 08:00 am to: Aug 27 2024 08:00 pm)",
                tasks.getTaskByIndex(0).toString());
        assertEquals(" Got it. I've added this task:\n"
                        + "    [E][  ] project meeting (from: Aug 27 2024 08:00 am to: Aug 27 2024 08:00 pm)\n"
                        + " Now you have 1 tasks in the list.\n",
                response);
    }

    @Test
    public void execute_addEventTask12HourClock() throws JeffException {
        Command c = new AddEventCommand(
                "event project meeting /from 2024-08-27 08:00 am /to 2024-08-27 08:00 pm"
        );
        String response = c.execute(tasks, storage);

        assertEquals(1, tasks.size());
        assertInstanceOf(EventTask.class, tasks.getTaskByIndex(0));
        assertEquals("[E][  ] project meeting (from: Aug 27 2024 08:00 am to: Aug 27 2024 08:00 pm)",
                tasks.getTaskByIndex(0).toString());
        assertEquals(" Got it. I've added this task:\n"
                        + "    [E][  ] project meeting (from: Aug 27 2024 08:00 am to: Aug 27 2024 08:00 pm)\n"
                        + " Now you have 1 tasks in the list.\n",
                response);
    }

    @Test
    public void execute_invalidEventTask_throwsException() throws JeffException {
        Command c = new AddEventCommand("event");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals("Sorry, the description of an event cannot be empty!", exception.toString());
    }

    @Test
    public void execute_wrongFormatEventTask_throwsException() throws JeffException {
        // Needs to be am/pm and not AM/PM
        Command c = new AddEventCommand(
                "event project meeting /from 2024-08-27 08:00 AM /to 2024-08-27 08:00 PM"
        );

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(
                "The format is wrong! It should be \"event xx /from yyyy-mm-dd HH:mm /to yyyy-mm--dd HH:mm\" "
                        + "or \"event xx /from yyyy-mm-dd hh:mm AM/PM /to yyyy-mm--dd hh:mm AM/PM\"!",
                exception.toString());
    }
}
