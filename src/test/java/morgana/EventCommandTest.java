package morgana;

import static morgana.commands.AddCommand.MESSAGE_SUCCESS;
import static morgana.util.DateTimeUtil.parseDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import morgana.commands.Command;
import morgana.commands.EventCommand;
import morgana.exceptions.MorganaException;
import morgana.storage.Storage;
import morgana.task.Event;
import morgana.task.TaskList;

public class EventCommandTest {
    private TaskList tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() throws MorganaException {
        tasks = new TaskList();
        storage = new Storage("temp/morgana.txt");
    }

    @AfterEach
    public void tearDown() {
        new File("temp/morgana.txt").delete();
        new File("temp").delete();
    }

    @Test
    public void eventCommand_validInput_success() throws MorganaException {
        String description = "project meeting";
        String from = "2019-10-15 1400";
        String to = "2019-10-15 1600";

        Command command = new EventCommand("%s /from %s /to %s".formatted(description, from, to));
        Event event = new Event(description, parseDateTime(from), parseDateTime(to));

        String expected = command.execute(tasks, storage);
        String actual = MESSAGE_SUCCESS.formatted(event, 1, "");
        assertEquals(expected, actual);
    }

    @Test
    public void eventCommand_invalidCommandFormat_exceptionThrown() {
        Command command = new EventCommand("project meeting /from Aug 6th 2pm");
        assertThrows(MorganaException.class, () -> command.execute(tasks, storage));
    }

    @Test
    public void eventCommand_invalidDateTimeFormat_exceptionThrown() {
        Command command = new EventCommand("project meeting /from Aug 6th 2pm /to 4pm");
        assertThrows(MorganaException.class, () -> command.execute(tasks, storage));
    }
}
