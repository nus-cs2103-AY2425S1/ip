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
import morgana.commands.DeadlineCommand;
import morgana.exceptions.MorganaException;
import morgana.storage.Storage;
import morgana.task.Deadline;
import morgana.task.TaskList;

public class DeadlineCommandTest {
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
    public void deadlineCommand_validInput_success() throws MorganaException {
        String description = "return book";
        String by = "2019-10-15 1800";

        Command command = new DeadlineCommand("%s /by %s".formatted(description, by));
        Deadline deadline = new Deadline(description, parseDateTime(by));

        String expected = command.execute(tasks, storage);
        String actual = MESSAGE_SUCCESS.formatted(deadline, 1, "");
        assertEquals(expected, actual);
    }

    @Test
    public void deadlineCommand_invalidCommandFormat_exceptionThrown() {
        Command command = new DeadlineCommand("return book /by");
        assertThrows(MorganaException.class, () -> command.execute(tasks, storage));
    }

    @Test
    public void deadlineCommand_invalidDateTimeFormat_exceptionThrown() {
        Command command = new DeadlineCommand("return book /by June 6th");
        assertThrows(MorganaException.class, () -> command.execute(tasks, storage));
    }
}
