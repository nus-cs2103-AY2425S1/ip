package popi;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import popi.command.AddCommand;
import popi.command.Command;
import popi.exception.EmptyDescriptionException;
import popi.exception.InvalidTimeFormatException;
import popi.exception.PopiException;
import popi.exception.UnknownCommandException;
import popi.task.TaskList;
import popi.task.TaskManager;
import popi.ui.Ui;

public class CommandClassTest {
    private TaskList tasks;
    private Ui ui;
    private TaskManager taskManager;

    @BeforeEach
    public void setUp() throws PopiException {
        tasks = new TaskList();
        ui = new Ui();
        taskManager = new TaskManager();

        Command command = new AddCommand("todo read book");
        command.execute(tasks, ui, taskManager);
    }

    @Test
    public void testInvalidCommand() {
        assertThrows(UnknownCommandException.class, () -> {
            Command command = new AddCommand("");
            command.execute(tasks, ui, taskManager);
        });

        assertThrows(UnknownCommandException.class, () -> {
            Command command = new AddCommand("set timer");
            command.execute(tasks, ui, taskManager);
        });
    }

    @Test
    public void testEmptyDescription() {
        assertThrows(EmptyDescriptionException.class, () -> {
            Command command = new AddCommand("todo");
            command.execute(tasks, ui, taskManager);
        });

        assertThrows(EmptyDescriptionException.class, () -> {
            Command command = new AddCommand("deadline");
            command.execute(tasks, ui, taskManager);
        });

        assertThrows(EmptyDescriptionException.class, () -> {
            Command command = new AddCommand("event");
            command.execute(tasks, ui, taskManager);
        });
    }

    @Test
    public void testInvalidTimeFormat() {
        assertThrows(InvalidTimeFormatException.class, () -> {
            Command command = new AddCommand("deadline project /by 2021-08-01");
            command.execute(tasks, ui, taskManager);
        });

        assertThrows(InvalidTimeFormatException.class, () -> {
            Command command = new AddCommand("event meeting /from 2021-08-01 14:00 /to 2021-08-01 13:00");
            command.execute(tasks, ui, taskManager);
        });
    }
}
