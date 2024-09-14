package jeff.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.EventTask;
import jeff.task.TaskList;

public class AddEventCommandTest {
    private static final String WRONG_FORMAT_ERROR = "The format is wrong! "
            + "It should be \"event(or e) xx /from yyyy-mm-dd HH:mm(or hh:mm am/pm) "
            + "/to yyyy-mm-dd HH:mm(or hh:mm am/pm)\"";
    private static final String INVALID_TIME_ERROR = "Your event cannot start after it ends!";
    private static final String PAST_EVENT_ERROR = "Your event cannot be in the past!";
    private static final String NO_DESCRIPTION_ERROR = "Your event cannot have no description!";
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
                "event project meeting /from 2030-08-27 08:00 /to 2030-08-27 20:00"
        );
        String response = c.execute(tasks, storage);

        assertEquals(1, tasks.size());
        assertInstanceOf(EventTask.class, tasks.getTaskByIndex(0));
        assertEquals("[E][  ] project meeting (from: Aug 27 2030 08:00 am to: Aug 27 2030 08:00 pm)",
                tasks.getTaskByIndex(0).toString());
        assertEquals(" Got it. I've added this task:\n"
                        + "    [E][  ] project meeting (from: Aug 27 2030 08:00 am to: Aug 27 2030 08:00 pm)\n"
                        + " Now you have 1 tasks in the list.\n",
                response);
    }

    @Test
    public void execute_addEventTask24HourClockAlias() throws JeffException {
        Command c = new AddEventCommand(
                "e project meeting /from 2030-08-27 08:00 /to 2030-08-27 20:00"
        );
        String response = c.execute(tasks, storage);

        assertEquals(1, tasks.size());
        assertInstanceOf(EventTask.class, tasks.getTaskByIndex(0));
        assertEquals("[E][  ] project meeting (from: Aug 27 2030 08:00 am to: Aug 27 2030 08:00 pm)",
                tasks.getTaskByIndex(0).toString());
        assertEquals(" Got it. I've added this task:\n"
                        + "    [E][  ] project meeting (from: Aug 27 2030 08:00 am to: Aug 27 2030 08:00 pm)\n"
                        + " Now you have 1 tasks in the list.\n",
                response);
    }

    @Test
    public void execute_addEventTask12HourClock() throws JeffException {
        Command c = new AddEventCommand(
                "event project meeting /from 2030-08-27 08:00 am /to 2030-08-27 08:00 pm"
        );
        String response = c.execute(tasks, storage);

        assertEquals(1, tasks.size());
        assertInstanceOf(EventTask.class, tasks.getTaskByIndex(0));
        assertEquals("[E][  ] project meeting (from: Aug 27 2030 08:00 am to: Aug 27 2030 08:00 pm)",
                tasks.getTaskByIndex(0).toString());
        assertEquals(" Got it. I've added this task:\n"
                        + "    [E][  ] project meeting (from: Aug 27 2030 08:00 am to: Aug 27 2030 08:00 pm)\n"
                        + " Now you have 1 tasks in the list.\n",
                response);
    }

    @Test
    public void execute_addEventTask12HourClockAlias() throws JeffException {
        Command c = new AddEventCommand(
                "e project meeting /from 2030-08-27 08:00 am /to 2030-08-27 08:00 pm"
        );
        String response = c.execute(tasks, storage);

        assertEquals(1, tasks.size());
        assertInstanceOf(EventTask.class, tasks.getTaskByIndex(0));
        assertEquals("[E][  ] project meeting (from: Aug 27 2030 08:00 am to: Aug 27 2030 08:00 pm)",
                tasks.getTaskByIndex(0).toString());
        assertEquals(" Got it. I've added this task:\n"
                        + "    [E][  ] project meeting (from: Aug 27 2030 08:00 am to: Aug 27 2030 08:00 pm)\n"
                        + " Now you have 1 tasks in the list.\n",
                response);
    }

    @Test
    public void execute_noDescriptionEventTask_throwsException() throws JeffException {
        Command c = new AddEventCommand("event     /from 2030-08-27 08:00 am /to 2030-08-27 08:00 pm");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(NO_DESCRIPTION_ERROR, exception.toString());
    }

    @Test
    public void execute_noDescriptionEventTaskAlias_throwsException() throws JeffException {
        Command c = new AddEventCommand("e     /from 2030-08-27 08:00 am /to 2030-08-27 08:00 pm");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(NO_DESCRIPTION_ERROR, exception.toString());
    }

    @Test
    public void execute_onlyCommandEventTask_throwsException() throws JeffException {
        Command c = new AddEventCommand("event");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_onlyCommandEventTaskAlias_throwsException() throws JeffException {
        Command c = new AddEventCommand("e");

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_wrongFormatEventTask_throwsException() throws JeffException {
        // Needs to be am/pm and not AM/PM
        Command c = new AddEventCommand(
                "event project meeting /from 2030-08-27 08:00 AM /to 2030-08-27 08:00 PM"
        );

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_wrongFormatEventTaskAlias_throwsException() throws JeffException {
        // Needs to be am/pm and not AM/PM
        Command c = new AddEventCommand(
                "e project meeting /from 2030-08-27 08:00 AM /to 2030-08-27 08:00 PM"
        );

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_noSpacingEventTask_throwsException() throws JeffException {
        Command c = new AddEventCommand(
                "eventproject meeting /from 2030-08-27 08:00 am /to 2030-08-27 08:00 pm"
        );

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_noSpacingEventTaskAlias_throwsException() throws JeffException {
        Command c = new AddEventCommand(
                "eproject meeting /from 2030-08-27 08:00 am /to 2030-08-27 08:00 pm"
        );

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(WRONG_FORMAT_ERROR, exception.toString());
    }

    @Test
    public void execute_startAfterEndEventTask_throwsException() throws JeffException {
        Command c = new AddEventCommand(
                "event project meeting /from 2030-08-28 08:00 /to 2030-08-27 20:00"
        );

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(INVALID_TIME_ERROR, exception.toString());
    }

    @Test
    public void execute_startAfterEndEventTaskAlias_throwsException() throws JeffException {
        Command c = new AddEventCommand(
                "e project meeting /from 2030-08-28 08:00 /to 2030-08-27 20:00"
        );

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(INVALID_TIME_ERROR, exception.toString());
    }

    @Test
    public void execute_pastEvent_throwsException() throws JeffException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.minusDays(1);
        LocalDateTime theDayBefore = yesterday.minusDays(1);
        String formattedYesterday = yesterday.format(FORMAT);
        String formattedTheDayBefore = theDayBefore.format(FORMAT);
        Command c = new AddEventCommand(
                "event project meeting /from " + formattedTheDayBefore + " /to " + formattedYesterday
        );

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(PAST_EVENT_ERROR, exception.toString());
    }

    @Test
    public void execute_pastEventAlias_throwsException() throws JeffException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.minusDays(1);
        LocalDateTime theDayBefore = yesterday.minusDays(1);
        String formattedYesterday = yesterday.format(FORMAT);
        String formattedTheDayBefore = theDayBefore.format(FORMAT);
        Command c = new AddEventCommand(
                "e project meeting /from " + formattedTheDayBefore + " /to " + formattedYesterday
        );

        JeffException exception = assertThrows(JeffException.class, () -> c.execute(tasks, storage));
        assertEquals(PAST_EVENT_ERROR, exception.toString());
    }
}
