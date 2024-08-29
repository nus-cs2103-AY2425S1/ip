package morgana;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;

import morgana.commands.DeadlineCommand;
import morgana.exceptions.MorganaException;
import morgana.storage.Storage;
import morgana.task.TaskList;
import morgana.ui.Ui;

public class DeadlineCommandTest {
    @Test
    public void deadlineCommand_validInput_success() throws MorganaException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("temp/morgana.txt");

        assertEquals(0, tasks.size());

        String args = "return book /by 2019-10-15 1800";
        DeadlineCommand command = new DeadlineCommand(args);
        command.execute(tasks, ui, storage);

        String expected = "[D][ ] return book (by: Oct 15 2019, 6:00 PM)";
        assertEquals(1, tasks.size());
        assertEquals(expected, tasks.get(0).toString());

        new File("temp/morgana.txt").delete();
        new File("temp").delete();
    }

    @Test
    public void deadlineCommand_invalidDateTimeFormat_exceptionThrown() throws MorganaException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("temp/morgana.txt");

        assertEquals(0, tasks.size());

        String args = "return book /by June 6th";
        DeadlineCommand command = new DeadlineCommand(args);
        Exception exception = assertThrows(MorganaException.class, () -> command.execute(tasks, ui, storage));

        String expected = "Invalid date/time format. Please use 'yyyy-MM-dd HHmm'.";
        assertEquals(0, tasks.size());
        assertEquals(expected, exception.getMessage());

        new File("temp/morgana.txt").delete();
        new File("temp").delete();
    }
}
