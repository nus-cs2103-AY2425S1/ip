package morgana;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;

import morgana.commands.EventCommand;
import morgana.exceptions.MorganaException;
import morgana.storage.Storage;
import morgana.task.TaskList;
import morgana.ui.Ui;

public class EventCommandTest {
    @Test
    public void eventCommand_validInput_success() throws MorganaException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("temp/morgana.txt");

        assertEquals(0, tasks.size());

        String args = "project meeting /from 2019-10-15 1400 /to 2019-10-15 1600";
        EventCommand command = new EventCommand(args);
        command.execute(tasks, ui, storage);

        String expected = "[E][ ] project meeting (from: Oct 15 2019, 2:00 PM to: Oct 15 2019, 4:00 PM)";
        assertEquals(1, tasks.size());
        assertEquals(expected, tasks.get(0).toString());

        new File("temp/morgana.txt").delete();
        new File("temp").delete();
    }

    @Test
    public void eventCommand_invalidDateTimeFormat_exceptionThrown() throws MorganaException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("temp/morgana.txt");

        assertEquals(0, tasks.size());

        String args = "project meeting /from Aug 6th 2pm /to 4pm";
        EventCommand command = new EventCommand(args);
        Exception exception = assertThrows(MorganaException.class, () -> command.execute(tasks, ui, storage));

        String expected = "Invalid date/time format. Please use 'yyyy-MM-dd HHmm'.";
        assertEquals(0, tasks.size());
        assertEquals(expected, exception.getMessage());

        new File("temp/morgana.txt").delete();
        new File("temp").delete();
    }
}
